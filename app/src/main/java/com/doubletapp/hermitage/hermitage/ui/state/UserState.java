package com.doubletapp.hermitage.hermitage.ui.state;

import com.doubletapp.hermitage.hermitage.model.map.Room;
import com.doubletapp.hermitage.hermitage.model.map.User;

/**
 * Created by navi9 on 20.10.2017.
 */

public class UserState {
    private User mUser;

    public UserState(User user) {
        mUser = user;
    }

    public Room getRoom() {
        return mUser.getRoom();
    }
}
