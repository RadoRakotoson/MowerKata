package com.mowitnow.mower.domain;

import com.mowitnow.mower.domain.*;
import com.mowitnow.mower.domain.exceptions.IllegalGardenMoveException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.mowitnow.mower.domain.Direction.EAST;
import static com.mowitnow.mower.domain.Direction.NORTH;
import static com.mowitnow.mower.domain.Instruction.FORWARD;
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

    @Test
    void mower_cant_move_outside_of_the_garden_par_le_north() {
        var garden = new Garden(new Dimension(2, 2));
        var initialPosition = new Position(2, 2);
        var instructions = of(FORWARD);

        assertThrows(IllegalGardenMoveException.class,
                () -> new Mower(initialPosition, NORTH).move(instructions, garden));
    }

    @Test
    void mower_cant_move_outside_of_the_garden_limit() {
        var garden = new Garden(new Dimension(1, 1));
        var initialPosition = new Position(0, 1);
        var instructions = of(FORWARD);

        assertThrows(IllegalGardenMoveException.class,
                () -> new Mower(initialPosition, NORTH).move(instructions, garden));
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 2", "3, 3"})
    void mower_cant_move_outside_of_the_garden_limit(int maxX, int maxY) {
        var garden = new Garden(new Dimension(maxX, maxY));
        var initialPosition = new Position(maxX - 1, 1);
        var instructions = of(FORWARD);

        var mower = new Mower(initialPosition, EAST).move(instructions, garden);

        assertThat(mower.position()).isEqualTo(new Position(maxX, 1));
        assertThat(mower.direction()).isEqualTo(EAST);
    }

}
