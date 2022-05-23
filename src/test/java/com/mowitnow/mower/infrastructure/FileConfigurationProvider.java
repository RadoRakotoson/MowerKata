package com.mowitnow.mower.infrastructure;

import com.mowitnow.mower.domain.Dimension;
import com.mowitnow.mower.domain.Garden;
import com.mowitnow.mower.domain.Instruction;
import com.mowitnow.mower.domain.Mower;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readString;
import static java.util.Objects.requireNonNull;

public class FileConfigurationProvider implements ConfigurationProvider {

    public FileConfigurationProvider(String configurationFile) throws URISyntaxException, IOException {
        URI fileLocation = requireNonNull(getClass().getClassLoader().getResource(configurationFile)).toURI();
        String[] configurations = readString(Paths.get(fileLocation)).split("\n");
    }

    @Override
    public Garden getGardenInformation() {
        return new Garden(new Dimension(5, 5));
    }

    @Override
    public List<Mower> getAllAvailableMowers() {
        return null;
    }

    @Override
    public List<Instruction> getAllInstructionFor(int mowerIndex) {
        return null;
    }
}
