package com.mowitnow.mower.domain.usecases;

import com.mowitnow.mower.domain.InMemoryConfigurationProvider;
import com.mowitnow.mower.domain.Mower;
import com.mowitnow.mower.domain.Position;
import com.mowitnow.mower.domain.ConfigurationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mowitnow.mower.domain.Instruction.FORWARD;
import static java.util.List.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LaunchMowerWithFakeConfigurationTest {

    private ConfigurationProvider instructionsProvider;

    @BeforeEach
    void setUp() {
        instructionsProvider = new InMemoryConfigurationProvider();
    }

    @Test
    void mower_must_follow_instructions_given() {
        List<Mower> allAvailableMowers = new LaunchMow(instructionsProvider).startMowers();
        Mower mower = allAvailableMowers.get(0);

        assertThat(instructionsProvider.getAllInstructionFor(0))
                .isEqualTo(of(FORWARD));

        assertThat(mower.position())
                .isEqualTo(new Position(0, 1));
    }
}
