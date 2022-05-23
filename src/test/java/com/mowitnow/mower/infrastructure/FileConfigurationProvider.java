package com.mowitnow.mower.infrastructure;

import com.mowitnow.mower.domain.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.mowitnow.mower.domain.Direction.*;
import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readString;
import static java.util.Objects.requireNonNull;

public class FileConfigurationProvider implements ConfigurationProvider {

    private final Garden garden;
    private final List<Mower> mowers;

    public FileConfigurationProvider(String configurationFile) throws URISyntaxException, IOException {
        URI fileLocation = requireNonNull(getClass().getClassLoader().getResource(configurationFile)).toURI();
        String[] configurations = readString(Paths.get(fileLocation)).split("\n");

        garden = getGardenInformationFromConfiguration(configurations[0]);
        mowers = getAllAvailableMowersFromConfiguration(configurations);
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
        return new ArrayList<>();
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
