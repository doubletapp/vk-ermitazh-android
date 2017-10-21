package com.doubletapp.hermitage.hermitage.ui.map;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        tileView.setScaleLimits(0, 2);
        tileView.setSize(width, height);
//        tileView.setScale(0);

        tileView.addDetailLevel(1f, "map.png", width, height);
//        tileView.addDetailLevel(1f, "tiles/125/tile-%d-%d.png", 256, 256);
//        tileView.addDetailLevel(0.125f, "tiles/125/tile-%d-%d.png");
//        tileView.addDetailLevel(0.250f, "tiles/125/tile-%d-%d.png");
//        tileView.addDetailLevel(0.500f, "tiles/125/tile-%d-%d.png");

//        addUser(100, 100);

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
        imageView.setImageResource(R.drawable.ic_user_blue_20px);
        tileView.addMarker(imageView, x, y, null, null);
    }

    private void addPasses() {
        for(Pass pass: allPasses) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.ic_user_blue_20px);

            tileView.addMarker(imageView, pass.getPosition().getX(), pass.getPosition().getY(), new Float(0.5), new Float(0.5));
        }
    }

    private void initData() {
        Pass point0 = new Pass(184, 68); // Oval
        Pass point1 = new Pass(200, 104); // Oval Copy 11
        Pass point2 = new Pass(216, 104); // Oval Copy 12
        Pass point3 = new Pass(349, 124); // Oval Copy 16
        Pass point4 = new Pass(301, 104); // Oval Copy 18
        Pass point5 = new Pass(317, 124); // Oval Copy 21
        Pass point6 = new Pass(130, 68); // Oval Copy
        Pass point7 = new Pass(96, 68); // Oval Copy 2
        Pass point8 = new Pass(71, 87); // Oval Copy 3
        Pass point9 = new Pass(71, 115); // Oval Copy 4
        Pass point10 = new Pass(71, 142); // Oval Copy 4
        Pass point11 = new Pass(107, 150); // Oval Copy 5
        Pass point12 = new Pass(124, 150); // Oval Copy 6
        Pass point13 = new Pass(129, 159); // Oval Copy 7
        Pass point14 = new Pass(129, 169); // Oval Copy 8
        Pass point15 = new Pass(129, 191); // Oval Copy 9
        Pass point16 = new Pass(129, 213); // Oval Copy 10
        Pass point17 = new Pass(147, 229); // Oval Copy 13
        Pass point18 = new Pass(167, 229); // Oval Copy 14
        Pass point19 = new Pass(175, 242); // Oval Copy 17
        Pass point20 = new Pass(175, 296); // Oval Copy 35
        Pass point21 = new Pass(183, 316); // Oval Copy 69
        Pass point22 = new Pass(210, 295); // Oval Copy 20
        Pass point23 = new Pass(210, 335); // Oval Copy 22
        Pass point24 = new Pass(210, 365); // Oval Copy 23
        Pass point25 = new Pass(210, 380); // Oval Copy 24
        Pass point26 = new Pass(210, 393); // Oval Copy 25
        Pass point27 = new Pass(210, 422); // Oval Copy 26
        Pass point28 = new Pass(183, 431); // Oval Copy 32
        Pass point29 = new Pass(183, 447); // Oval Copy 36
        Pass point30 = new Pass(202, 447); // Oval Copy 39
        Pass point31 = new Pass(235, 447); // Oval Copy 40
        Pass point32 = new Pass(248, 447); // Oval Copy 41
        Pass point33 = new Pass(255, 461); // Oval Copy 42
        Pass point34 = new Pass(255, 475); // Oval Copy 43
        Pass point35 = new Pass(255, 497); // Oval Copy 44
        Pass point36 = new Pass(255, 522); // Oval Copy 45
        Pass point37 = new Pass(259, 530); // Oval Copy 47
        Pass point38 = new Pass(270, 544); // Oval Copy 49
        Pass point39 = new Pass(278, 549); // Oval Copy 51
        Pass point40 = new Pass(290, 549); // Oval Copy 52
        Pass point41 = new Pass(290, 530); // Oval Copy 50
        Pass point42 = new Pass(242, 522); // Oval Copy 46
        Pass point43 = new Pass(236, 531); // Oval Copy 48
        Pass point44 = new Pass(229, 521); // Oval Copy 53
        Pass point45 = new Pass(180, 516); // Oval Copy 54
        Pass point46 = new Pass(176, 540); // Oval Copy 55
        Pass point47 = new Pass(155, 570); // Oval Copy 56
        Pass point48 = new Pass(137, 570); // Oval Copy 58
        Pass point49 = new Pass(119, 570); // Oval Copy 59
        Pass point50 = new Pass(104, 559); // Oval Copy 60
        Pass point51 = new Pass(75, 559); // Oval Copy 61
        Pass point52 = new Pass(69, 541); // Oval Copy 62
        Pass point53 = new Pass(70, 510); // Oval Copy 63
        Pass point54 = new Pass(71, 483); // Oval Copy 64
        Pass point55 = new Pass(93, 480); // Oval Copy 65
        Pass point56 = new Pass(115, 480); // Oval Copy 66
        Pass point57 = new Pass(138, 471); // Oval Copy 67
        Pass point58 = new Pass(138, 462); // Oval Copy 68
        Pass point59 = new Pass(160, 559); // Oval Copy 57
        Pass point60 = new Pass(168, 447); // Oval Copy 37
        Pass point61 = new Pass(152, 447); // Oval Copy 38
        Pass point62 = new Pass(175, 379); // Oval Copy 33
        Pass point63 = new Pass(175, 336); // Oval Copy 34
        Pass point64 = new Pass(183, 159); // Oval r1_01
        Pass point65 = new Pass(208, 112); // Oval r1 Copy
        Pass point66 = new Pass(209, 143); // Oval r1 Copy 2
        Pass point67 = new Pass(167, 254); // Oval Copy 15
        Room room0 = new Room(97, 17, point0, point6); // Rectangle 2
        Room room1 = new Room(131.5, 28.5, point0, point1); // Rectangle 2 Copy 4
        Room room2 = new Room(147, 42, point1, point2, point65); // Rectangle 2 Copy 5
        Room room3 = new Room(147, 72, point65, point66); // Rectangle 2 Copy 18
        Room room4 = new Room(160.5, 101.5, point64, point66); // Rectangle 2 Copy 21
        Room room5 = new Room(114.5, 136.5, point18, point19, point64); // Rectangle 2 Copy 22
        Room room6 = new Room(114.5, 213, point19, point20, point67); // Rectangle 2 Copy 23
        Room room7 = new Room(114.5, 260, point20, point21, point63); // Rectangle 2 Copy 26
        Room room8 = new Room(114.5, 331.5, point28, point62, point63); // Rectangle 2 Copy 32
        Room room9 = new Room(138, 212.5, point22); // Rectangle 2 Copy 24
        Room room10 = new Room(138, 260, point21, point22, point23); // Rectangle 2 Copy 25
        Room room11 = new Room(138, 295, point23, point24); // Rectangle 2 Copy 27
        Room room12 = new Room(138, 317, point24, point25); // Rectangle 2 Copy 28
        Room room13 = new Room(138, 331, point25, point26); // Rectangle 2 Copy 29
        Room room14 = new Room(138, 352.5, point26, point27); // Rectangle 2 Copy 30
        Room room15 = new Room(138, 375.5, point27, point28); // Rectangle 2 Copy 31
        Room room16 = new Room(132, 400.5, point29, point30); // Rectangle 2 Copy 33
        Room room17 = new Room(114.5, 400, point29, point60); // Rectangle 2 Copy 35
        Room room18 = new Room(99, 391.5, point60, point61); // Rectangle 2 Copy 36
        Room room19 = new Room(77.5, 395, point58, point61); // Rectangle 2 Copy 37
        Room room20 = new Room(77.5, 411, point57, point58); // Rectangle 2 Copy 38
        Room room21 = new Room(70.5, 421.5, point56, point57); // Rectangle 2 Copy 39
        Room room22 = new Room(44, 422, point55, point56); // Rectangle 2 Copy 41
        Room room23 = new Room(16.5, 422, point54, point55); // Rectangle 2 Copy 42
        Room room24 = new Room(11.5, 441.5, point53, point54); // Rectangle 2 Copy 43
        Room room25 = new Room(21.5, 470, point52, point53); // Rectangle 2 Copy 44
        Room room26 = new Room(7, 497.5, point51, point52); // Rectangle 2 Copy 45
        Room room27 = new Room(28.5, 497.5, point50, point51); // Rectangle 2 Copy 46
        Room room28 = new Room(50.5, 510.5, point49, point50); // Rectangle 2 Copy 47
        Room room29 = new Room(67.5, 510.5, point48, point49); // Rectangle 2 Copy 48
        Room room30 = new Room(85.5, 510.5, point47, point48); // Rectangle 2 Copy 49
        Room room31 = new Room(101, 513.5, point47, point59); // Rectangle 2 Copy 50
        Room room32 = new Room(106.5, 494.5, point46, point59); // Rectangle 2 Copy 51
        Room room33 = new Room(113.5, 470, point45, point46); // Rectangle 2 Copy 52
        Room room34 = new Room(147, 460, point44, point45); // Rectangle 2 Copy 53
        Room room35 = new Room(169, 471.5, point43, point44); // Rectangle 2 Copy 54
        Room room36 = new Room(213.5, 479, point37, point38, point41); // Rectangle 2 Copy 57
        Room room37 = new Room(240, 484.5, point40, point41); // Rectangle 2 Copy 60
        Room room38 = new Room(207.5, 493.5, point38, point39); // Rectangle 2 Copy 58
        Room room39 = new Room(223, 493.5, point39, point40); // Rectangle 2 Copy 59
        Room room40 = new Room(187, 488, point36, point37, point42, point43); // Rectangle 2 Copy 55
        Room room41 = new Room(193, 454, point35, point36); // Rectangle 2 Copy 61
        Room room42 = new Room(193, 431, point34, point35); // Rectangle 2 Copy 62
        Room room43 = new Room(193, 413, point33, point34); // Rectangle 2 Copy 63
        Room room44 = new Room(193, 395, point32, point33); // Rectangle 2 Copy 64
        Room room45 = new Room(181, 389.5, point31, point32); // Rectangle 2 Copy 66
        Room room46 = new Room(181, 430.5, point42); // Rectangle 2 Copy 65
        Room room47 = new Room(173.5, 494.5); // Rectangle 2 Copy 56
        Room room48 = new Room(64.5, 432.5, point56); // Rectangle 2 Copy 40
        Room room49 = new Room(158.5, 400.5, point30, point31); // Rectangle 2 Copy 34
        Room room50 = new Room(128.5, 77.5); // Rectangle 2 Copy 19
        Room room51 = new Room(197.5, 46.5, point2, point4); // Rectangle 2 Copy 8
        Room room52 = new Room(248.5, 56.5, point4, point5); // Rectangle 2 Copy 9
        Room room53 = new Room(273, 68, point3, point5); // Rectangle 2 Copy 10
        Room room54 = new Room(52.5, 17, point6, point7); // Rectangle 2 Copy
        Room room55 = new Room(18.5, 16, point7, point8); // Rectangle 2 Copy 2
        Room room56 = new Room(18, 45.5, point8, point9); // Rectangle 2 Copy 3
        Room room57 = new Room(18, 73.5, point9, point10); // Rectangle 2 Copy 6
        Room room58 = new Room(24, 95.5, point10, point11); // Rectangle 2 Copy 7
        Room room59 = new Room(55.5, 95.5, point11, point12); // Rectangle 2 Copy 11
        Room room60 = new Room(75.5, 95.5, point12, point13); // Rectangle 2 Copy 12
        Room room61 = new Room(75.5, 109, point13, point14); // Rectangle 2 Copy 13
        Room room62 = new Room(75.5, 125, point14, point15); // Rectangle 2 Copy 14
        Room room63 = new Room(72.5, 147, point15, point16); // Rectangle 2 Copy 15
        Room room64 = new Room(72.5, 172, point16, point17); // Rectangle 2 Copy 16
        Room room65 = new Room(97, 172, point17, point18); // Rectangle 2 Copy 17
        Room room66 = new Room(85, 197.5, point67); // Rectangle 2 Copy 20
        Pass[] allPasses = new Pass[] {point0, point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12, point13, point14, point15, point16, point17, point18, point19, point20, point21, point22, point23, point24, point25, point26, point27, point28, point29, point30, point31, point32, point33, point34, point35, point36, point37, point38, point39, point40, point41, point42, point43, point44, point45, point46, point47, point48, point49, point50, point51, point52, point53, point54, point55, point56, point57, point58, point59, point60, point61, point62, point63, point64, point65, point66, point67};
        Room[] allRooms = new Room[] {room0, room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15, room16, room17, room18, room19, room20, room21, room22, room23, room24, room25, room26, room27, room28, room29, room30, room31, room32, room33, room34, room35, room36, room37, room38, room39, room40, room41, room42, room43, room44, room45, room46, room47, room48, room49, room50, room51, room52, room53, room54, room55, room56, room57, room58, room59, room60, room61, room62, room63, room64, room65, room66};

        this.allPasses = allPasses;
        this.allRooms = allRooms;
    }
}
