package com.doubletapp.hermitage.hermitage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doubletapp.hermitage.hermitage.ui.TabActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        TabActivity.start(this);
    }
}
