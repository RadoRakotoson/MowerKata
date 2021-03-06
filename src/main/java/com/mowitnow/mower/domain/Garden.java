package com.mowitnow.mower.domain;

public record Garden(Dimension dimension) {

    public boolean isOutOfYourDimension(Position position) {
        int maxX = dimension.maxX();
        int maxY = dimension.maxY();

        return position.x() > maxX || position.y() > maxY;

    }
}
