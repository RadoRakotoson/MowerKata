package com.mowitnow.mower.domain.usecases;

import com.mowitnow.mower.domain.ConfigurationProvider;
import com.mowitnow.mower.domain.Mower;
import com.mowitnow.mower.domain.Position;
import com.mowitnow.mower.infrastructure.FileConfigurationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.mowitnow.mower.domain.Direction.EAST;
import static com.mowitnow.mower.domain.Direction.NORTH;
import static org.assertj.core.api.Assertions.assertThat;

public class LaunchMowerWithFileConfigurationTest {

    private ConfigurationProvider fileConfigurationProvider;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        fileConfigurationProvider = new FileConfigurationProvider("program.cmd");
    }

    @Test
    void two_mowers_must_move_to_specific_position_and_direction() {

        List<Mower> allAvailableMowers = new LaunchMow(fileConfigurationProvider).startMowers();
        Mower firstMower = allAvailableMowers.get(0);
        Mower secondMower = allAvailableMowers.get(1);

        assertThat(firstMower.position()).isEqualTo(new Position(1, 3));
        assertThat(firstMower.direction()).isEqualTo(NORTH);
        assertThat(secondMower.position()).isEqualTo(new Position(5, 1));
        assertThat(secondMower.direction()).isEqualTo(EAST);


    }
}
