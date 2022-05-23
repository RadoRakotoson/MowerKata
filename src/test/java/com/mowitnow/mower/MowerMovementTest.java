package com.mowitnow.mower;

import org.junit.jupiter.api.Test;

import static com.mowitnow.mower.Direction.NORTH;
import static com.mowitnow.mower.Instruction.FORWARD;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MowerMovementTest {

    @Test
    void should_move_forward_in_north_direction() {
        var garden = new Garden(new Dimension(2, 2));
        var initialPosition = new Position(0, 0);
        var instructions = of(FORWARD);

        var mower = new Mower(initialPosition, NORTH).move(instructions, garden);

        assertThat(mower.position()).isEqualTo(new Position(0, 1));
        assertThat(mower.direction()).isEqualTo(NORTH);
    }

    @Test
    void mower_cant_move_when_no_garden() {
        var garden = new Garden(new Dimension(0, 0));
        var initialPosition = new Position(0, 0);
        var instructions = of(FORWARD);

        assertThrows(IllegalGardenMoveException.class,
                () -> new Mower(initialPosition, NORTH).move(instructions, garden));
    }

}
