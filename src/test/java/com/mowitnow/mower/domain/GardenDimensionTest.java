package com.mowitnow.mower.domain;

import com.mowitnow.mower.domain.exceptions.NegativeDimensionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GardenDimensionTest {

    @Test
    void garden_dimension_cant_have_X_negative() throws NegativeDimensionException {

        assertThrows(NegativeDimensionException.class, () -> new Dimension(-1, 0));
    }

}
