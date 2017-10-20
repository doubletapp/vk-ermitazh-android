package com.doubletapp.hermitage.hermitage.model.map;

import java.util.Arrays;
import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Room {
    private Position mPosition;
    private List<Pass> mPasses;

    public Room(int x, int y, Pass... passes) {
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
