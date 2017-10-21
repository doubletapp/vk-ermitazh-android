package com.doubletapp.hermitage.hermitage.model.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Pass {
    private Position mPosition;
    private List<Room> mRooms;

    public Pass(double x, double y) {
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
        double xDistance = mPosition.getX() - anotherPath.getPosition().getX();
        double yDistance = mPosition.getY() - anotherPath.getPosition().getY();

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
    public Room getCommonRoom(Pass anotherPass) {
        Set<Room> ownRooms = new HashSet<>(getRooms());
        Set<Room> anotherRooms = new HashSet<>(anotherPass.getRooms());

        ownRooms.retainAll(anotherRooms);

        if (ownRooms.isEmpty()) {
            return null;
        } else {
            List<Room> rooms = new ArrayList<>();
            rooms.addAll(ownRooms);
            return rooms.get(0);
        }
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
