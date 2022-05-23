package com.mowitnow.mower.infrastructure;

import com.mowitnow.mower.domain.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mowitnow.mower.domain.Direction.*;
import static com.mowitnow.mower.domain.Instruction.*;
import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readString;
import static java.util.Objects.requireNonNull;

public class FileConfigurationProvider implements ConfigurationProvider {

    private final Garden garden;
    private final List<Mower> mowers;
    private final List<String> instructions;

    public FileConfigurationProvider(String configurationFile) throws URISyntaxException, IOException {
        URI fileLocation = requireNonNull(getClass().getClassLoader().getResource(configurationFile)).toURI();
        String fileContent = readString(Paths.get(fileLocation)).replace("\r", "");
        String[] configurations = fileContent.split("\n");

        garden = getGardenInformationFromConfiguration(configurations[0]);
        mowers = getAllAvailableMowersFromConfiguration(configurations);
        instructions = getAllInstructionFromConfiguration(configurations);
    }

    @Override
    public Garden getGardenInformation() {
        return garden;
    }

    @Override
    public List<Mower> getAllAvailableMowers() {
        return mowers;
    }

    @Override
    public List<Instruction> getAllInstructionFor(int mowerIndex) {
        return toInstructions(instructions.get(mowerIndex));
    }


    private List<String> getAllInstructionFromConfiguration(String[] configurations) {
        List<String> instructions = new ArrayList<>();
        for (int i = 2; i < configurations.length; i += 2) {
            instructions.add(configurations[i]);
        }

        return instructions;
    }

    private List<Instruction> toInstructions(String commandRaw) {
        return commandRaw.chars()
                .mapToObj(Character::toString)
                .map(this::toInstruction)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Instruction> toInstruction(String instructionRaw) {
        switch (instructionRaw) {
            case "G" -> {
                return Optional.of(LEFT);
            }
            case "D" -> {
                return Optional.of(RIGHT);
            }
            case "A" -> {
                return Optional.of(FORWARD);
            }
        }
        return Optional.empty();
    }

    private Garden getGardenInformationFromConfiguration(String gardenConfiguration) {
        int limitX = parseInt(gardenConfiguration.split(" ")[0]);
        int limitY = parseInt(gardenConfiguration.split(" ")[1]);
        Dimension gardenDimension = new Dimension(limitX, limitY);
        return new Garden(gardenDimension);
    }

    private List<Mower> getAllAvailableMowersFromConfiguration(String[] configurations) {
        List<Mower> mowers = new ArrayList<>();
        for (int i = 1; i < configurations.length; i += 2) {
            mowers.add(new Mower(toPosition(configurations[i]), toDirection(configurations[i])));
        }

        return mowers;
    }

    private Position toPosition(String positionRaw) {
        String[] originArraySpitted = positionRaw.split(" ");
        int x = parseInt(originArraySpitted[0]);
        int y = parseInt(originArraySpitted[1]);
        return new Position(x, y);
    }

    private Direction toDirection(String directionRaw) {
        String[] originArraySpitted = directionRaw.split(" ");
        switch (originArraySpitted[2]) {
            case "N" -> {
                return NORTH;
            }
            case "E" -> {
                return EAST;
            }
            case "S" -> {
                return SOUTH;
            }
            default -> {
                return WEST;
            }
        }
    }
}
