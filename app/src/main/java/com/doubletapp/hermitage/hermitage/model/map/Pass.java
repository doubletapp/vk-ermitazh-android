package com.doubletapp.hermitage.hermitage.model.map;

import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Pass {
    private Position mPosition;
    private List<Room> mRooms;

    public Position getPosition() {
        return mPosition;
    }

    public List<Room> getRooms() {
        return mRooms;
    }

    public double getDistanceTo(Pass anotherPath) {
        int xDistance = mPosition.getX() - anotherPath.getPosition().getX();
        int yDistance = mPosition.getY() - anotherPath.getPosition().getY();

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
}
