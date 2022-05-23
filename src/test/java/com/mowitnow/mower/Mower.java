package com.mowitnow.mower;

import java.util.List;

import static com.mowitnow.mower.Direction.*;

public record Mower(Position position, Direction direction) {
    public Mower move(List<Instruction> instructions, Garden garden) {
        return new Mower(new Position(0, 1 ), NORTH);
    }
}
