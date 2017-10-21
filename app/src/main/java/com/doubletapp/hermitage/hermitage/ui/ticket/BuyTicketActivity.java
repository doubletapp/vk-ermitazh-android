package com.doubletapp.hermitage.hermitage.ui.ticket;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.utils.ActivityUtils;

public class BuyTicketActivity extends AppCompatActivity {

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, BuyTicketActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        ActivityUtils.replaceFragment(getSupportFragmentManager(),
                R.id.fragment_container,
                BuyTicketFragment.newInstance(),
                BuyTicketFragment.TAG,
                false);
    }
}
