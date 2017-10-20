package com.doubletapp.hermitage.hermitage.ui.state;

import com.doubletapp.hermitage.hermitage.model.Hall;

/**
 * Created by navi9 on 20.10.2017.
 */

public class HallState {
    private Hall mHall;

    public HallState(Hall hall) {
        mHall = hall;
    }

    public Hall getHall() {
        return mHall;
    }
}
