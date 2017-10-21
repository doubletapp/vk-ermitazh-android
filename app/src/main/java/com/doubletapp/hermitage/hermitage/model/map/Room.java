package com.doubletapp.hermitage.hermitage.model.map;

import android.support.annotation.Nullable;
import com.doubletapp.hermitage.hermitage.model.Hall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Room {
    private Position mPosition;
    private List<Pass> mPasses;
    @Nullable
    private Hall hall;

    public Room(double x, double y, Pass... passes) {
        mPosition = new Position(x, y);
        mPasses = Arrays.asList(passes);

        for (Pass pass : passes) {
            pass.addRoom(this);
        }
    }

    public Position getPosition() {
        return mPosition;
    }

    public List<Pass> getPasses() {
        return mPasses;
    }

    public List<Room> getNeighbors() {
        Set<Room> rooms = new HashSet<>();

        for (Pass pass : mPasses) {
            rooms.addAll(pass.getRooms());
        }
        rooms.remove(this);

        return new ArrayList<>(rooms);
    }

    public double getIntensityСoefficient() {
        if (hall == null) {
            return 1.0;
        }

        switch(hall.getIntensity()){
            case LOW:
                return 1.0;
            case MEDIUM:
                return 1.5;
            case HIGH:
                return 2.0;
            default:
                return 1.0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Room)) {
            return false;
        }

        Room room = (Room) obj;

        return mPosition.equals(room.getPosition());
    }

    public static Builder build(int x, int y) {
        return new Builder(x, y);
    }

    public static class Builder {
        private Room room;

        private Builder(int x, int y) {
            room = new Room(x, y);
        }

        public Builder pass(int x, int y) {
            room.mPasses.add(new Pass(x, y));
            return this;
        }

        public Room create() {
            return room;
        }
    }
}
