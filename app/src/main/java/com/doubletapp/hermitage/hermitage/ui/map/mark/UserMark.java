package com.doubletapp.hermitage.hermitage.ui.map.mark;

import android.content.Context;
import android.support.annotation.DrawableRes;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.map.Position;
import com.doubletapp.hermitage.hermitage.model.map.User;
import com.qozix.tileview.TileView;

/**
 * Created by navi9 on 22.10.2017.
 */

public class UserMark extends MapMark {
    private User user;

    public UserMark(Context context, User user) {
        super(context, R.drawable.ic_user_blue_20px, 40);
        this.user = user;
    }

    @Override
    protected void onInvalidate(TileView tileView) {
        super.onInvalidate(tileView);
    }

    @Override
    public Position getMarkPosition() {
        return user.getRoom().getPosition();
    }

    public User getUser() {
        return user;
    }

    @Override
    public float getMinimumScaleForShow() {
        return 0f;
    }
}
