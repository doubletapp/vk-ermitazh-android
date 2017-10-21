package com.doubletapp.hermitage.hermitage.ui.map;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.map.Pass;
import com.doubletapp.hermitage.hermitage.model.map.Room;
import com.github.chrisbanes.photoview.PhotoView;
import com.qozix.tileview.TileView;

import butterknife.BindView;

public class MapFragment extends Fragment {

    public static final String TAG = "MapFragment";
    Pass[] allPasses;
    Room[] allRooms;

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


        initData();
        addPasses();

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

    private void addPasses() {
        for(Pass pass: allPasses) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageBitmap(Bitmap.createScaledBitmap(getBitmapFromVectorDrawable(R.drawable.ic_user_blue_20px), 30, 30, false));

            tileView.addMarker(imageView, pass.getPosition().getX(), pass.getPosition().getY(), (float) -0.5, (float) -0.5);
        }
    }

    private void initData() {
        Pass point0 = new Pass(739, 274); // Oval
        Pass point1 = new Pass(803, 418); // Oval Copy 11
        Pass point2 = new Pass(867, 418); // Oval Copy 12
        Pass point3 = new Pass(1399, 498); // Oval Copy 16
        Pass point4 = new Pass(1207, 418); // Oval Copy 18
        Pass point5 = new Pass(1271, 498); // Oval Copy 21
        Pass point6 = new Pass(523, 274); // Oval Copy
        Pass point7 = new Pass(387, 274); // Oval Copy 2
        Pass point8 = new Pass(287, 350); // Oval Copy 3
        Pass point9 = new Pass(287, 462); // Oval Copy 4
        Pass point10 = new Pass(287, 570); // Oval Copy 4
        Pass point11 = new Pass(431, 602); // Oval Copy 5
        Pass point12 = new Pass(499, 602); // Oval Copy 6
        Pass point13 = new Pass(519, 638); // Oval Copy 7
        Pass point14 = new Pass(519, 678); // Oval Copy 8
        Pass point15 = new Pass(519, 766); // Oval Copy 9
        Pass point16 = new Pass(519, 854); // Oval Copy 10
        Pass point17 = new Pass(591, 918); // Oval Copy 13
        Pass point18 = new Pass(671, 918); // Oval Copy 14
        Pass point19 = new Pass(703, 970); // Oval Copy 17
        Pass point20 = new Pass(703, 1186); // Oval Copy 35
        Pass point21 = new Pass(735, 1266); // Oval Copy 69
        Pass point22 = new Pass(843, 1182); // Oval Copy 20
        Pass point23 = new Pass(843, 1342); // Oval Copy 22
        Pass point24 = new Pass(843, 1462); // Oval Copy 23
        Pass point25 = new Pass(843, 1522); // Oval Copy 24
        Pass point26 = new Pass(843, 1574); // Oval Copy 25
        Pass point27 = new Pass(843, 1690); // Oval Copy 26
        Pass point28 = new Pass(735, 1726); // Oval Copy 32
        Pass point29 = new Pass(735, 1790); // Oval Copy 36
        Pass point30 = new Pass(811, 1790); // Oval Copy 39
        Pass point31 = new Pass(943, 1790); // Oval Copy 40
        Pass point32 = new Pass(995, 1790); // Oval Copy 41
        Pass point33 = new Pass(1023, 1846); // Oval Copy 42
        Pass point34 = new Pass(1023, 1902); // Oval Copy 43
        Pass point35 = new Pass(1023, 1990); // Oval Copy 44
        Pass point36 = new Pass(1023, 2090); // Oval Copy 45
        Pass point37 = new Pass(1039, 2122); // Oval Copy 47
        Pass point38 = new Pass(1083, 2178); // Oval Copy 49
        Pass point39 = new Pass(1115, 2198); // Oval Copy 51
        Pass point40 = new Pass(1163, 2198); // Oval Copy 52
        Pass point41 = new Pass(1163, 2122); // Oval Copy 50
        Pass point42 = new Pass(971, 2090); // Oval Copy 46
        Pass point43 = new Pass(947, 2126); // Oval Copy 48
        Pass point44 = new Pass(919, 2086); // Oval Copy 53
        Pass point45 = new Pass(723, 2066); // Oval Copy 54
        Pass point46 = new Pass(707, 2162); // Oval Copy 55
        Pass point47 = new Pass(623, 2282); // Oval Copy 56
        Pass point48 = new Pass(551, 2282); // Oval Copy 58
        Pass point49 = new Pass(479, 2282); // Oval Copy 59
        Pass point50 = new Pass(419, 2238); // Oval Copy 60
        Pass point51 = new Pass(303, 2238); // Oval Copy 61
        Pass point52 = new Pass(279, 2166); // Oval Copy 62
        Pass point53 = new Pass(283, 2042); // Oval Copy 63
        Pass point54 = new Pass(287, 1934); // Oval Copy 64
        Pass point55 = new Pass(375, 1922); // Oval Copy 65
        Pass point56 = new Pass(463, 1922); // Oval Copy 66
        Pass point57 = new Pass(555, 1886); // Oval Copy 67
        Pass point58 = new Pass(555, 1850); // Oval Copy 68
        Pass point59 = new Pass(643, 2238); // Oval Copy 57
        Pass point60 = new Pass(675, 1790); // Oval Copy 37
        Pass point61 = new Pass(611, 1790); // Oval Copy 38
        Pass point62 = new Pass(703, 1518); // Oval Copy 33
        Pass point63 = new Pass(703, 1346); // Oval Copy 34
        Pass point64 = new Pass(735, 638); // Oval r1_01
        Pass point65 = new Pass(835, 450); // Oval r1 Copy
        Pass point66 = new Pass(839, 574); // Oval r1 Copy 2
        Pass point67 = new Pass(671, 1018); // Oval Copy 15
        Room room0 = new Room(388, 68, point0, point6); // Rectangle 2
        Room room1 = new Room(526, 114, point0, point1); // Rectangle 2 Copy 4
        Room room2 = new Room(588, 168, point1, point2, point65); // Rectangle 2 Copy 5
        Room room3 = new Room(588, 288, point65, point66); // Rectangle 2 Copy 18
        Room room4 = new Room(642, 406, point64, point66); // Rectangle 2 Copy 21
        Room room5 = new Room(458, 546, point18, point19, point64); // Rectangle 2 Copy 22
        Room room6 = new Room(458, 852, point19, point20, point67); // Rectangle 2 Copy 23
        Room room7 = new Room(458, 1040, point20, point21, point63); // Rectangle 2 Copy 26
        Room room8 = new Room(458, 1326, point28, point62, point63); // Rectangle 2 Copy 32
        Room room9 = new Room(552, 850, point22); // Rectangle 2 Copy 24
        Room room10 = new Room(552, 1040, point21, point22, point23); // Rectangle 2 Copy 25
        Room room11 = new Room(552, 1180, point23, point24); // Rectangle 2 Copy 27
        Room room12 = new Room(552, 1268, point24, point25); // Rectangle 2 Copy 28
        Room room13 = new Room(552, 1324, point25, point26); // Rectangle 2 Copy 29
        Room room14 = new Room(552, 1410, point26, point27); // Rectangle 2 Copy 30
        Room room15 = new Room(552, 1502, point27, point28); // Rectangle 2 Copy 31
        Room room16 = new Room(528, 1602, point29, point30); // Rectangle 2 Copy 33
        Room room17 = new Room(458, 1600, point29, point60); // Rectangle 2 Copy 35
        Room room18 = new Room(396, 1566, point60, point61); // Rectangle 2 Copy 36
        Room room19 = new Room(310, 1580, point58, point61); // Rectangle 2 Copy 37
        Room room20 = new Room(310, 1644, point57, point58); // Rectangle 2 Copy 38
        Room room21 = new Room(282, 1686, point56, point57); // Rectangle 2 Copy 39
        Room room22 = new Room(176, 1688, point55, point56); // Rectangle 2 Copy 41
        Room room23 = new Room(66, 1688, point54, point55); // Rectangle 2 Copy 42
        Room room24 = new Room(46, 1766, point53, point54); // Rectangle 2 Copy 43
        Room room25 = new Room(86, 1880, point52, point53); // Rectangle 2 Copy 44
        Room room26 = new Room(28, 1990, point51, point52); // Rectangle 2 Copy 45
        Room room27 = new Room(114, 1990, point50, point51); // Rectangle 2 Copy 46
        Room room28 = new Room(202, 2042, point49, point50); // Rectangle 2 Copy 47
        Room room29 = new Room(270, 2042, point48, point49); // Rectangle 2 Copy 48
        Room room30 = new Room(342, 2042, point47, point48); // Rectangle 2 Copy 49
        Room room31 = new Room(404, 2054, point47, point59); // Rectangle 2 Copy 50
        Room room32 = new Room(426, 1978, point46, point59); // Rectangle 2 Copy 51
        Room room33 = new Room(454, 1880, point45, point46); // Rectangle 2 Copy 52
        Room room34 = new Room(588, 1840, point44, point45); // Rectangle 2 Copy 53
        Room room35 = new Room(676, 1886, point43, point44); // Rectangle 2 Copy 54
        Room room36 = new Room(854, 1916, point37, point38, point41); // Rectangle 2 Copy 57
        Room room37 = new Room(960, 1938, point40, point41); // Rectangle 2 Copy 60
        Room room38 = new Room(830, 1974, point38, point39); // Rectangle 2 Copy 58
        Room room39 = new Room(892, 1974, point39, point40); // Rectangle 2 Copy 59
        Room room40 = new Room(748, 1952, point36, point37, point42, point43); // Rectangle 2 Copy 55
        Room room41 = new Room(772, 1816, point35, point36); // Rectangle 2 Copy 61
        Room room42 = new Room(772, 1724, point34, point35); // Rectangle 2 Copy 62
        Room room43 = new Room(772, 1652, point33, point34); // Rectangle 2 Copy 63
        Room room44 = new Room(772, 1580, point32, point33); // Rectangle 2 Copy 64
        Room room45 = new Room(724, 1558, point31, point32); // Rectangle 2 Copy 66
        Room room46 = new Room(724, 1722, point42); // Rectangle 2 Copy 65
        Room room47 = new Room(694, 1978); // Rectangle 2 Copy 56
        Room room48 = new Room(258, 1730); // Rectangle 2 Copy 40
        Room room49 = new Room(634, 1602, point30, point31); // Rectangle 2 Copy 34
        Room room50 = new Room(514, 310); // Rectangle 2 Copy 19
        Room room51 = new Room(790, 186, point2, point4); // Rectangle 2 Copy 8
        Room room52 = new Room(994, 226, point4, point5); // Rectangle 2 Copy 9
        Room room53 = new Room(1092, 272, point3, point5); // Rectangle 2 Copy 10
        Room room54 = new Room(210, 68, point6, point7); // Rectangle 2 Copy
        Room room55 = new Room(74, 64, point7, point8); // Rectangle 2 Copy 2
        Room room56 = new Room(72, 182, point8, point9); // Rectangle 2 Copy 3
        Room room57 = new Room(72, 294, point9, point10); // Rectangle 2 Copy 6
        Room room58 = new Room(96, 382, point10, point11); // Rectangle 2 Copy 7
        Room room59 = new Room(222, 382, point11, point12); // Rectangle 2 Copy 11
        Room room60 = new Room(302, 382, point12, point13); // Rectangle 2 Copy 12
        Room room61 = new Room(302, 436, point13, point14); // Rectangle 2 Copy 13
        Room room62 = new Room(302, 500, point14, point15); // Rectangle 2 Copy 14
        Room room63 = new Room(290, 588, point15, point16); // Rectangle 2 Copy 15
        Room room64 = new Room(290, 688, point16, point17); // Rectangle 2 Copy 16
        Room room65 = new Room(388, 688, point17, point18); // Rectangle 2 Copy 17
        Room room66 = new Room(340, 790, point67); // Rectangle 2 Copy 20
        Pass[] allPasses = new Pass[] {point0, point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12, point13, point14, point15, point16, point17, point18, point19, point20, point21, point22, point23, point24, point25, point26, point27, point28, point29, point30, point31, point32, point33, point34, point35, point36, point37, point38, point39, point40, point41, point42, point43, point44, point45, point46, point47, point48, point49, point50, point51, point52, point53, point54, point55, point56, point57, point58, point59, point60, point61, point62, point63, point64, point65, point66, point67};
        Room[] allRooms = new Room[] {room0, room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15, room16, room17, room18, room19, room20, room21, room22, room23, room24, room25, room26, room27, room28, room29, room30, room31, room32, room33, room34, room35, room36, room37, room38, room39, room40, room41, room42, room43, room44, room45, room46, room47, room48, room49, room50, room51, room52, room53, room54, room55, room56, room57, room58, room59, room60, room61, room62, room63, room64, room65, room66};

        this.allPasses = allPasses;
        this.allRooms = allRooms;
    }
}
