package com.doubletapp.hermitage.hermitage.ui.map;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        int width = 1127;
        int height = 542;

        tileView.setSize(width, height);
        tileView.setScaleLimits(0, 12);
        tileView.setScale(0);
        tileView.setShouldRenderWhilePanning(true);

        tileView.addDetailLevel(1f, "tiles/1000/tile-%d-%d.png", 32, 32);
        tileView.addDetailLevel(0.500f, "tiles/500/tile-%d-%d.png", 64, 64);
        tileView.addDetailLevel(0.250f, "tiles/250/tile-%d-%d.png", 128, 128);
        tileView.addDetailLevel(0.125f, "tiles/125/tile-%d-%d.png");


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
        imageView.setImageResource(R.drawable.ic_user_blue_20px);
        tileView.addMarker(imageView, x, y, null, null);

    }
}
