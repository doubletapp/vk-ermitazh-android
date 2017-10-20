package com.doubletapp.hermitage.hermitage.model.map;

import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Room {
    private Position mPosition;
    private List<Pass> mPasses;

    public Position getPosition() {
        return mPosition;
    }

    public List<Pass> getPasses() {
        return mPasses;
    }
}
