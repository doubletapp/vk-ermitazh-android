package com.doubletapp.hermitage.hermitage.model.map;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Position {
    private double x;
    private double y;

    public Position() {
        this(0, 0);
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Position)) {
            return false;
        }

        Position position = (Position) obj;

        Double eps = 0.000001;

        return  Math.abs(position.getX() - x) < eps && Math.abs(position.getY() - y) < eps;
    }
}
