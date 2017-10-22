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
import com.qozix.tileview.markers.MarkerLayout;

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
    private float scale;

    public MapMark(Context context, @DrawableRes int res, int size) {
        mContext = context;
        mRes = res;
        mSize = size;
    }

    public View createView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(Bitmap.createScaledBitmap(getBitmapFromVectorDrawable(mRes),
                getScaledSize(),
                getScaledSize(),
                false));

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

    public void invalidate(TileView tileView) {
        scale = tileView.getScale();
        if (isAttached) {
            onInvalidate(tileView);
        }
    }

    protected abstract void onInvalidate(TileView tileView);

    public void attachMark(TileView tileView) {
        scale = tileView.getScale();
        isAttached = true;
        view = createView();
        tileView.addMarker(view, getMarkPosition().getX(), getMarkPosition().getY(), -0.5f, -0.5f);
    }

    public void detachMark(TileView tileView) {
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

    public
    @DrawableRes
    int getRes() {
        return mRes;
    }

    public int getSize() {
        return mSize;
    }

    public int getScaledSize() {
        return (int) (scale * mSize);
    }

    public void setSize(int size) {
        mSize = size;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public View getView() {
        return view;
    }
}
