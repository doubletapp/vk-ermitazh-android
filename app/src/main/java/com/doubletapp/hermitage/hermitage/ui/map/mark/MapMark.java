package com.doubletapp.hermitage.hermitage.ui.map.mark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.doubletapp.hermitage.hermitage.model.map.Position;
import com.qozix.tileview.TileView;

/**
 * Created by navi9 on 21.10.2017.
 */

public abstract class MapMark {
    private Context mContext;
    private
    @DrawableRes
    int mRes;
    private int mSize;
    private View view;
    private boolean isAttached = false;

    public MapMark(Context context, @DrawableRes int res, int size) {
        mContext = context;
        mRes = res;
        mSize = size;
    }

    public View getView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(Bitmap.createScaledBitmap(getBitmapFromVectorDrawable(mRes), mSize, mSize, false));

        return imageView;
    }

    private Bitmap getBitmapFromVectorDrawable(int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public void invalidate() {
        if (isAttached) {
            onInvalidate();
        }
    }

    protected abstract void onInvalidate();

    public synchronized void attachMark(TileView tileView) {
        isAttached = true;
        view = getView();
        tileView.addMarker(view, getMarkPosition().getX(), getMarkPosition().getY(), -0.5f, -0.5f);
    }

    public synchronized void detachMark(TileView tileView) {
        isAttached = false;
        tileView.removeMarker(view);
        view = null;
    }

    public boolean isAttached() {
        return isAttached;
    }

    public abstract Position getMarkPosition();

    public Context getContext() {
        return mContext;
    }

    public @DrawableRes int getRes() {
        return mRes;
    }

    public int getSize() {
        return mSize;
    }
}
