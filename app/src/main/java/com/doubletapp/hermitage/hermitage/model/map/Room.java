package com.doubletapp.hermitage.hermitage.model.map;

import android.support.annotation.Nullable;

import com.doubletapp.hermitage.hermitage.model.Hall;

import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Room {
    private Position mPosition;
    private List<Pass> mPasses;
    @Nullable
    private Hall hall;

    public Position getPosition() {
        return mPosition;
    }

    public List<Pass> getPasses() {
        return mPasses;
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
}
