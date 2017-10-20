package com.doubletapp.hermitage.hermitage.model.map;

/**
 * Created by navi9 on 20.10.2017.
 */

public class User {
    private Position mPosition;

    public User() {
        mPosition = new Position(0, 0);
    }

    public void setPosition(Position position) {
        mPosition = position;
    }

    public Position getPosition() {
        return mPosition;
    }
}
