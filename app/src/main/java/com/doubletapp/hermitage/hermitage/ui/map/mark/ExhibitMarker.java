package com.doubletapp.hermitage.hermitage.ui.map.mark;

import android.content.Context;
import android.support.annotation.DrawableRes;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.Exhibit;
import com.doubletapp.hermitage.hermitage.model.map.Position;
import com.qozix.tileview.TileView;

/**
 * Created by navi9 on 22.10.2017.
 */

public class ExhibitMarker extends MapMark {
    private Exhibit exhibit;

    public ExhibitMarker(Context context, Exhibit exhibit) {
        super(context, R.drawable.ic_exhibit_black_20px, 30);

        this.exhibit = exhibit;
    }

    @Override
    protected void onInvalidate(TileView tileView) {
        super.onInvalidate(tileView);
    }

    @Override
    public Position getMarkPosition() {
        return exhibit.getPosition();
    }

    public Exhibit getExhibit() {
        return exhibit;
    }
}
