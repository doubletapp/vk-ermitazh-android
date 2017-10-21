package com.doubletapp.hermitage.hermitage.ui.map;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.utils.MetricsConverter;
import com.github.chrisbanes.photoview.PhotoView;
import com.qozix.tileview.TileView;
import com.qozix.tileview.widgets.ZoomPanLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends Fragment {

    public static final String TAG = "MapFragment";

    private static final int USER_SIZE_PX = 20;

    @BindView(R.id.map)
    PhotoView mMapView;

    private TileView tileView;

    public static MapFragment newInstance() {

        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tileView = new TileView(getActivity());
//        int width = MetricsConverter.convertDpToPixel(1127, getActivity());
//        int height = MetricsConverter.convertDpToPixel(542, getActivity());

        int width = 4972;
        int height = 2568;

        tileView.setSize(width, height);
        tileView.setScaleLimits(0, 4);
        tileView.setScale(0);
        tileView.setShouldRenderWhilePanning(true);

        tileView.addDetailLevel(1f, "tiles/125/tile-%d-%d.png");


        return tileView;
    }

    @Override
    public void onResume() {
        super.onResume();

        tileView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        tileView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        tileView.destroy();
        tileView = null;
    }

    private void addUser(double x, double y) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageBitmap(Bitmap.createScaledBitmap(getBitmapFromVectorDrawable(R.drawable.ic_user_blue_20px), 20, 20, false));
        tileView.addMarker(imageView, x, y, null, null);
    }

    private Bitmap getBitmapFromVectorDrawable( int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(getActivity(), drawableId);

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
