package com.doubletapp.hermitage.hermitage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.map.Pass;
import com.doubletapp.hermitage.hermitage.model.map.Room;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity";
    private Handler mHandler;
    
    public static void start(Context context) {
        Intent starter = new Intent(context, SplashActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TabActivity.start(SplashActivity.this);
            }
        }, 1000);
        setContentView(R.layout.activity_splash);
    }

    private void initData() {
        Pass point0 = new Pass(115.5, 0.5);
        Pass point1 = new Pass(131.5, 36.5);
        Pass point2 = new Pass(147.5, 36.5);
        Pass point3 = new Pass(280.5, 56.5);
        Pass point4 = new Pass(232.5, 36.5);
        Pass point5 = new Pass(248.5, 56.5);
        Pass point6 = new Pass(61.5, 0.5);
        Pass point7 = new Pass(27.5, 0.5);
        Pass point8 = new Pass(2.5, 19.5);
        Pass point9 = new Pass(2.5, 47.5);
        Pass point10 = new Pass(2.5, 74.5);
        Pass point11 = new Pass(38.5, 82.5);
        Pass point12 = new Pass(55.5, 82.5);
        Pass point13 = new Pass(60.5, 91.5);
        Pass point14 = new Pass(60.5, 101.5);
        Pass point15 = new Pass(60.5, 123.5);
        Pass point16 = new Pass(60.5, 145.5);
        Pass point17 = new Pass(78.5, 161.5);
        Pass point18 = new Pass(98.5, 161.5);
        Pass point19 = new Pass(106.5, 174.5);
        Pass point20 = new Pass(106.5, 228.5);
        Pass point21 = new Pass(114.5, 248.5);
        Pass point22 = new Pass(141.5, 227.5);
        Pass point23 = new Pass(141.5, 267.5);
        Pass point24 = new Pass(141.5, 297.5);
        Pass point25 = new Pass(141.5, 312.5);
        Pass point26 = new Pass(141.5, 325.5);
        Pass point27 = new Pass(141.5, 354.5);
        Pass point28 = new Pass(114.5, 363.5);
        Pass point29 = new Pass(114.5, 379.5);
        Pass point30 = new Pass(133.5, 379.5);
        Pass point31 = new Pass(166.5, 379.5);
        Pass point32 = new Pass(179.5, 379.5);
        Pass point33 = new Pass(186.5, 393.5);
        Pass point34 = new Pass(186.5, 407.5);
        Pass point35 = new Pass(186.5, 429.5);
        Pass point36 = new Pass(186.5, 454.5);
        Pass point37 = new Pass(190.5, 462.5);
        Pass point38 = new Pass(201.5, 476.5);
        Pass point39 = new Pass(209.5, 481.5);
        Pass point40 = new Pass(221.5, 481.5);
        Pass point41 = new Pass(221.5, 462.5);
        Pass point42 = new Pass(173.5, 454.5);
        Pass point43 = new Pass(167.5, 463.5);
        Pass point44 = new Pass(160.5, 453.5);
        Pass point45 = new Pass(111.5, 448.5);
        Pass point46 = new Pass(107.5, 472.5);
        Pass point47 = new Pass(86.5, 502.5);
        Pass point48 = new Pass(68.5, 502.5);
        Pass point49 = new Pass(50.5, 502.5);
        Pass point50 = new Pass(35.5, 491.5);
        Pass point51 = new Pass(6.5, 491.5);
        Pass point52 = new Pass(0.5, 473.5);
        Pass point53 = new Pass(1.5, 442.5);
        Pass point54 = new Pass(2.5, 415.5);
        Pass point55 = new Pass(24.5, 412.5);
        Pass point56 = new Pass(46.5, 412.5);
        Pass point57 = new Pass(69.5, 403.5);
        Pass point58 = new Pass(69.5, 394.5);
        Pass point59 = new Pass(91.5, 491.5);
        Pass point60 = new Pass(99.5, 379.5);
        Pass point61 = new Pass(83.5, 379.5);
        Pass point62 = new Pass(107.5, 371.5);
        Pass point63 = new Pass(106.5, 268.5);
        Pass point64 = new Pass(114.5, 91.5);
        Pass point65 = new Pass(139.5, 44.5);
        Pass point66 = new Pass(140.5, 75.5);
        Pass point67 = new Pass(98.5, 186.5);
        Room room0 = new Room(97, 17, point0);
        Room room1 = new Room(131.5, 28.5, point1, point65);
        Room room2 = new Room(147, 42, point2, point65);
        Room room3 = new Room(147, 72, point66);
        Room room5 = new Room(114.5, 136.5, point19, point64);
        Room room6 = new Room(114.5, 213, point20);
        Room room7 = new Room(114.5, 260, point21, point63);
        Room room8 = new Room(114.5, 331.5, point28, point29, point62);
        Room room9 = new Room(138, 212.5, point22);
        Room room10 = new Room(138, 260, point23);
        Room room11 = new Room(138, 295, point24, point25);
        Room room12 = new Room(138, 317, point25, point26);
        Room room13 = new Room(138, 331, point26);
        Room room14 = new Room(138, 352.5, point27);
        Room room15 = new Room(138, 375.5, point30);
        Room room16 = new Room(132, 400.5, point30);
        Room room17 = new Room(114.5, 400, point29);
        Room room18 = new Room(99, 391.5, point60);
        Room room19 = new Room(77.5, 395, point57, point58, point61);
        Room room20 = new Room(74.5, 409, point57);
        Room room22 = new Room(44, 422, point56);
        Room room23 = new Room(16.5, 422, point54, point55);
        Room room24 = new Room(11.5, 441.5, point53);
        Room room25 = new Room(21.5, 470, point52);
        Room room26 = new Room(7, 497.5, point51);
        Room room27 = new Room(28.5, 497.5, point50);
        Room room28 = new Room(50.5, 510.5, point49);
        Room room29 = new Room(67.5, 510.5, point48);
        Room room30 = new Room(85.5, 510.5, point47);
        Room room32 = new Room(106.5, 494.5, point59);
        Room room33 = new Room(113.5, 470, point46);
        Room room34 = new Room(147, 460, point42, point43, point44);
        Room room35 = new Room(169, 471.5, point43);
        Room room36 = new Room(213.5, 479, point38, point39, point40);
        Room room40 = new Room(187, 488, point37, point38);
        Room room41 = new Room(193, 454, point36, point37);
        Room room42 = new Room(193, 431, point35);
        Room room43 = new Room(193, 413, point34);
        Room room44 = new Room(193, 395, point33, point34);
        Room room45 = new Room(181, 389.5, point32, point33);
        Room room46 = new Room(181, 430.5, point33, point34, point35, point36, point37, point42);
        Room room49 = new Room(158.5, 400.5, point31, point32);
        Room room50 = new Room(128.5, 77.5, point64, point66);
        Room room51 = new Room(197.5, 46.5, point4);
        Room room52 = new Room(248.5, 56.5, point5);
        Room room53 = new Room(273, 68, point3);
        Room room54 = new Room(52.5, 17, point6);
        Room room55 = new Room(18.5, 16, point7, point8);
        Room room56 = new Room(18, 45.5, point9);
        Room room57 = new Room(18, 73.5, point10, point11);
        Room room58 = new Room(24, 95.5, point11);
        Room room59 = new Room(55.5, 95.5, point12, point13, point14);
        Room room60 = new Room(75.5, 95.5, point13, point14);
        Room room61 = new Room(75.5, 109, point14);
        Room room62 = new Room(75.5, 125, point15);
        Room room63 = new Room(72.5, 147, point16, point17);
        Room room64 = new Room(72.5, 172, point17);
        Room room65 = new Room(97, 172, point18, point19, point67);
        Room room66 = new Room(85, 197.5, point67);
    }
}
