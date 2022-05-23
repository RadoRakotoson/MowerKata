package com.mowitnow.mower.domain.usecases;

import com.mowitnow.mower.domain.Garden;
import com.mowitnow.mower.domain.Instruction;
import com.mowitnow.mower.domain.Mower;
import com.mowitnow.mower.infrastructure.ConfigurationProvider;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class LaunchMow {

    private final ConfigurationProvider instructionsProvider;

    public LaunchMow(ConfigurationProvider instructionsProvider) {
        this.instructionsProvider = instructionsProvider;
    }

    public List<Mower> startMowers() {

        List<Mower> mowers = instructionsProvider.getAllAvailableMowers();
        Garden garden = instructionsProvider.getGardenInformation();

        return range(0, mowers.size())
                .mapToObj(mowerIndex -> startMowerProgram(garden, mowers, mowerIndex))
                .collect(Collectors.toList());

    }

    private Mower startMowerProgram(Garden garden, List<Mower> mowers, int mowerIndex) {
        List<Instruction> instructions = instructionsProvider.getAllInstructionFor(mowerIndex);
        Mower mower = mowers.get(mowerIndex);
        return mower.move(instructions, garden);
    }
}
