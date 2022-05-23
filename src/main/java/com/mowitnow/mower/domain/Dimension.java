package com.mowitnow.mower.domain;

import com.mowitnow.mower.domain.exceptions.NegativeDimensionException;

public record Dimension(int maxX, int maxY) {
    public Dimension {
        if(maxX < 0 || maxY < 0) {
            throw new NegativeDimensionException();
        }
    }
}
