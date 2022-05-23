package com.mowitnow.mower.domain;

import com.mowitnow.mower.domain.Garden;
import com.mowitnow.mower.domain.Instruction;
import com.mowitnow.mower.domain.Mower;

import java.util.List;

public interface ConfigurationProvider {
    Garden getGardenInformation();
    List<Mower> getAllAvailableMowers();
    List<Instruction> getAllInstructionFor(int mowerIndex);
}
