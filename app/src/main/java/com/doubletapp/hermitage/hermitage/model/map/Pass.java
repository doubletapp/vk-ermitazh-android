package com.doubletapp.hermitage.hermitage.model.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Pass {
    private Position mPosition;
    private List<Room> mRooms;

    public Pass(int x, int y) {
        mPosition = new Position(x, y);
        mRooms = new ArrayList<>();
    }

    public Position getPosition() {
        return mPosition;
    }

    public void addRoom(Room room) {
        mRooms.add(room);
    }

    public List<Room> getRooms() {
        return mRooms;
    }


    public double getDistanceTo(Pass anotherPath) {
        int xDistance = mPosition.getX() - anotherPath.getPosition().getX();
        int yDistance = mPosition.getY() - anotherPath.getPosition().getY();

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Pass)) {
            return false;
        }

        Pass pass = (Pass) obj;

        return pass.getPosition().equals(mPosition);
    }
}
