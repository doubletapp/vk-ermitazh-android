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
    private double mStartX;
    private double mStartY;
    private double mWidth;
    private double mHeight;
    @Nullable
    private Hall hall;

    public Room(double startX, double startY, double width, double height, Pass... passes) {
        mStartX = startX;
        mStartY = startY;
        mWidth = width;
        mHeight = height;

        mPosition = new Position(startX + width / 2, startY + height / 2);
        mPasses = Arrays.asList(passes);

        for (Pass pass : passes) {
            pass.addRoom(this);
        }
    }

    public double getStartX() {
        return mStartX;
    }

    public double getStartY() {
        return mStartY;
    }

    public double getWidth() {
        return mWidth;
    }

    public double getHeight() {
        return mHeight;
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

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Hall getHall() {
        return hall;
    }

    public static Builder build(double x, double y, double width, double height) {
        return new Builder(x, y, width, height);
    }

    public static class Builder {
        private Room room;

        private Builder(double x, double y, double width, double height) {
            room = new Room(x, y, width, height);
        }

        public Builder pass(double x, double y) {
            room.mPasses.add(new Pass(x, y));
            return this;
        }

        public Room create() {
            return room;
        }
    }
}
