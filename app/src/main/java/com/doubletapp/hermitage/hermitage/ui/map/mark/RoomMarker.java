package com.doubletapp.hermitage.hermitage.ui.map.mark;

import android.content.Context;
import android.view.View;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.map.Position;
import com.doubletapp.hermitage.hermitage.model.map.Room;

/**
 * Created by navi9 on 22.10.2017.
 */

public class RoomMarker extends MapMark {
    private View view;
    private Room room;

    public RoomMarker(Context context, Room room) {
        super(context, R.drawable.ic_user_blue_20px, 20);

        this.room = room;
    }

    @Override
    protected void onInvalidate() {

    }

    @Override
    public Position getMarkPosition() {
        return room.getPosition();
    }

    public Room getRoom() {
        return room;
    }
}
