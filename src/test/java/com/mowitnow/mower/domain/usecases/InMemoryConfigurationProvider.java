package com.mowitnow.mower.domain.usecases;

import com.mowitnow.mower.domain.*;
import com.mowitnow.mower.infrastructure.ConfigurationProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mowitnow.mower.domain.Direction.NORTH;
import static com.mowitnow.mower.domain.Instruction.FORWARD;

public class InMemoryConfigurationProvider implements ConfigurationProvider {

    private final List<Mower> mowers;
    private final Garden garden;

    public InMemoryConfigurationProvider() {
        mowers = initAvailableMowers();
        garden = initGardenInformation();
    }

    @Override
    public Garden getGardenInformation() {
        return garden;
    }

    public Garden initGardenInformation() {
        return new Garden(new Dimension(2, 2));
    }

    @Override
    public List<Mower> getAllAvailableMowers() {
        return mowers;
    }

    public List<Mower> initAvailableMowers() {
        Position initialPosition = new Position(0, 0);
        List<Mower> mowers = new ArrayList<>();
        mowers.add(new Mower(initialPosition, NORTH));
        return mowers;
    }

    @Override
    public List<Instruction> getAllInstructionFor(int mowerIndex) {
        return List.of(FORWARD);
    }

}
