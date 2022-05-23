package com.mowitnow.mower;

import com.mowitnow.mower.domain.Dimension;
import com.mowitnow.mower.domain.Garden;
import com.mowitnow.mower.domain.Mower;
import com.mowitnow.mower.infrastructure.FileConfigurationProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileConfigurationProviderTest {
    private FileConfigurationProvider configurationProvider;

    @Test
    void garden_should_be_well_parsed_from_configuration_file() {
        Garden garden = configurationProvider.getGardenInformation();

        assertThat(garden).isEqualTo(new Garden(new Dimension(5, 5)));
    }

    @Test
    void mowers_should_be_parsed_from_configuration_file() {
        List<Mower> mowers = configurationProvider.getAllAvailableMowers();

        assertThat(mowers.size()).isEqualTo(2);
    }
}
