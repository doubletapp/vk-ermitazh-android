package com.doubletapp.hermitage.hermitage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doubletapp.hermitage.hermitage.R;

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
}
