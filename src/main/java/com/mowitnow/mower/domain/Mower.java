package com.mowitnow.mower.domain;

import com.mowitnow.mower.domain.exceptions.IllegalGardenMoveException;

import java.util.List;
import java.util.Optional;

public record Mower(Position position, Direction direction) {
    public Mower move(List<Instruction> instructions, Garden garden) {

        Mower mower = this;
        for (Instruction instruction: instructions) {
            Position oldMowerPosition = mower.position();
            Direction oldMowerDimension = mower.direction();
            mower = moveMower(garden, instruction, oldMowerPosition, oldMowerDimension);
        }

        return Optional.of(mower).orElseThrow();
    }

    private Mower moveMower(Garden garden, Instruction instruction, Position position, Direction direction) {
        Direction nextDirection = direction;
        Position nextPosition = position;

        switch (instruction) {
            case LEFT -> nextDirection = direction.leftRotation();
            case RIGHT -> nextDirection = direction.rightRotation();
            case FORWARD -> nextPosition = changePositionFromDirection(position, direction);
        }

        if (garden.isOutOfYourDimension(nextPosition)) {
            throw new IllegalGardenMoveException();
        }

        return new Mower(nextPosition, nextDirection);
    }

    private Position changePositionFromDirection(Position position, Direction direction) {
        int xPosition = position.x();
        int yPosition = position.y();

        switch (direction) {
            case NORTH -> yPosition += 1;
            case EAST -> xPosition += 1;
            case SOUTH -> yPosition -= 1;
            default -> xPosition -= 1;
        }

        return new Position(xPosition, yPosition);
    }
}
