package com.mowitnow.mower;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MowerMovementTest {

    @Test
    void should_move_forward_in_north_direction() {
        var garden = new Garden(new Dimension(2, 2));
        var initialPosition = new Position(0, 0);
        var instructions = of(FORWARD);

        var mower = new Mower(initialPosition, NORTH).move(instructions, garden);

        assertThat(mower.position()).isEqualTo(new Position(0, 1));
        assertThat(mower.direction()).isEqualTo(NORTH),
    }
}
