package com.mowitnow.mower;

import com.mowitnow.mower.domain.Dimension;
import com.mowitnow.mower.domain.Garden;
import com.mowitnow.mower.domain.Instruction;
import com.mowitnow.mower.domain.Mower;
import com.mowitnow.mower.infrastructure.FileConfigurationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.mowitnow.mower.domain.Instruction.FORWARD;
import static com.mowitnow.mower.domain.Instruction.LEFT;
import static org.assertj.core.api.Assertions.assertThat;

public class FileConfigurationProviderTest {
    private FileConfigurationProvider configurationProvider;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        configurationProvider = new FileConfigurationProvider("program.cmd");
    }

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

    @Test
    void instructions_should_be_retrieved_from_first_mower() {
        List<Instruction> instructions = configurationProvider.getAllInstructionFor(0);

        assertThat(instructions).isEqualTo(List.of(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD));
    }
}
