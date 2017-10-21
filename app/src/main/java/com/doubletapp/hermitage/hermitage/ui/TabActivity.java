package com.doubletapp.hermitage.hermitage.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.Hall;
import com.doubletapp.hermitage.hermitage.model.PathBuilder;
import com.doubletapp.hermitage.hermitage.model.map.Pass;
import com.doubletapp.hermitage.hermitage.model.map.Path;
import com.doubletapp.hermitage.hermitage.model.map.Room;

import java.util.Arrays;

public class TabActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TabActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        test();
    }

    private void test() {
        Pass p1 = new Pass(2, 1);
        Pass p2 = new Pass(1, 2);
        Pass p3 = new Pass(4, 1);
        Pass p4 = new Pass(6, 1);
        Pass p5 = new Pass(8, 1);
        Pass p6 = new Pass(10, 1);
        Pass p7 = new Pass(11, 2);
        Pass p9 = new Pass(4, 3);
        Pass p8 = new Pass(9, 2);
        Pass p10 = new Pass(6, 5);
        Pass p11 = new Pass(7, 6);
        Pass p12 = new Pass(11, 6);
        Pass p13 = new Pass(10, 7);

        Room r1 = new Room(1, 1, p1, p2);
        Room r2 = new Room(3, 1, p1, p3);
        Room r3 = new Room(5, 1, p3, p4);
        Room r4 = new Room(7, 1, p4, p5);
        Room r5 = new Room(9, 1, p5, p6, p8);
        Room r6 = new Room(11, 1, p6, p7);
        Room r7 = new Room(2, 3, p2, p9);
        Room r8 = new Room(5, 4, p9, p10);
        Room r9 = new Room(8, 4, p10, p11);
        Room r10 = new Room(11, 4, p12, p7);
        Room r11 = new Room(8, 7, p11, p13);
        Room r12 = new Room(11, 7, p13, p12);

        PathBuilder builder = new PathBuilder(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12);

        Hall h1 = new Hall();
        h1.setRoom(r1);

        Hall h2 = new Hall();
        h2.setRoom(r12);

        Hall[] halls = new Hall[]{h2};

        Path path = builder.getPath(h1, Arrays.asList(halls), null);
    }
}
