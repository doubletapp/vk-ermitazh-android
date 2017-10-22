package com.doubletapp.hermitage.hermitage.ui.exhibit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;
import android.view.View;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gangsta on 22/10/2017.
 */

public class ExhibitActivity extends AppCompatActivity {

    @BindView(R.id.exhibit_toolbar)
    Toolbar mToolbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, ExhibitActivity.class);
        ((AppCompatActivity)context).startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit);
        ButterKnife.bind(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        init();
    }

    private void init() {
        ActivityUtils.replaceFragment(
                getSupportFragmentManager(),
                R.id.fragment_container,
                ExhibitFragment.newInstance(),
                ExhibitFragment.TAG,
                false
        );
    }
}

