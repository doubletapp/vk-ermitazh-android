package com.doubletapp.hermitage.hermitage.model.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Map {
    private List<Room> mRooms;
    private List<Pass> mPasses;
    private User mUser;

    public Map() {
        mRooms = new ArrayList<>();
        mPasses = new ArrayList<>();
        mUser = new User();
    }

    public void setUserRoom(Room room) {
        mUser.setRoom(room);
    }

    public User getUser() {
        return mUser;
    }

    public Map addRoom(Room room) {
        if (mRooms.contains(room)) {
            mRooms.add(room);
        }

        return this;
    }

    public Map addPass(Pass pass) {
        if (mPasses.contains(pass)) {
            mPasses.add(pass);
        }

        return this;
    }

    public List<Room> getRooms() {
        return mRooms;
    }

    public List<Pass> getPasses() {
        return mPasses;
    }
}
