package com.mowitnow.mower.infrastructure;

import com.mowitnow.mower.domain.Dimension;
import com.mowitnow.mower.domain.Garden;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileConfigurationProviderTest {
    private FileConfigurationProvider configurationProvider;

    @Test
    void garden_should_be_well_parsed_from_configuration_file() {
        Garden garden = configurationProvider.getGardenInformation();

        assertThat(garden).isEqualTo(new Garden(new Dimension(5, 5)));
    }
}
