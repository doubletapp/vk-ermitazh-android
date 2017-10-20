package com.doubletapp.hermitage.hermitage.ui.state;

import com.doubletapp.hermitage.hermitage.model.map.Map;
import com.doubletapp.hermitage.hermitage.model.map.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class MapState {
    private List<RoomState> mRoomStates;
    private UserState mUserState;
    private PathState mPathState;

    public MapState(Map map) {
        mRoomStates = new ArrayList<>();
        for (Room room : map.getRooms()) {
            mRoomStates.add(new RoomState(room));
        }

        mUserState = new UserState(map.getUser());

    }
}
