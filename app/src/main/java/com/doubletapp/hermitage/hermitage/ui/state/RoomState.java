package com.doubletapp.hermitage.hermitage.ui.state;

import com.doubletapp.hermitage.hermitage.model.map.Room;

/**
 * Created by navi9 on 20.10.2017.
 */

public class RoomState {
    private Room mRoom;

    public RoomState(Room room) {
        mRoom = room;
    }

    public Room getRoom() {
        return mRoom;
    }
}
