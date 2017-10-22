package com.doubletapp.hermitage.hermitage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.ui.exhibit.ExhibitFragment;
import com.doubletapp.hermitage.hermitage.ui.home.HomeFragment;
import com.doubletapp.hermitage.hermitage.ui.map.MapFragment;
import com.doubletapp.hermitage.hermitage.ui.nav.NavFragment;
import com.doubletapp.hermitage.hermitage.ui.ticket.TicketsEmptyFragment;
import com.doubletapp.hermitage.hermitage.utils.ActivityUtils;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity implements OnTabSelectListener {

    @BindView(R.id.title_logo)
    ImageView mToolbarLogo;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, TabActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        init();

//        test();
    }

    private void init() {
        mBottomBar.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                if (getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG) == null) {
                    ActivityUtils.replaceFragment(getSupportFragmentManager(),
                            R.id.fragment_container,
                            HomeFragment.newInstance(),
                            HomeFragment.TAG,
                            false);
                }
                break;
            case R.id.tab_exhibit:
                if (getSupportFragmentManager().findFragmentByTag(ExhibitFragment.TAG) == null) {
                    ActivityUtils.replaceFragment(getSupportFragmentManager(),
                            R.id.fragment_container,
                            ExhibitFragment.newInstance(),
                            ExhibitFragment.TAG,
                            false);
                }
                break;
            case R.id.tab_map:
                if (getSupportFragmentManager().findFragmentByTag(MapFragment.TAG) == null) {
                    ActivityUtils.replaceFragment(getSupportFragmentManager(),
                            R.id.fragment_container,
                            MapFragment.newInstance(),
                            MapFragment.TAG,
                            false);
                }
                break;
            case R.id.tab_nav:
                if (getSupportFragmentManager().findFragmentByTag(NavFragment.TAG) == null) {
                    ActivityUtils.replaceFragment(getSupportFragmentManager(),
                            R.id.fragment_container,
                            NavFragment.newInstance(),
                            NavFragment.TAG,
                            false);
                }
                break;
            case R.id.tab_ticket:
                if (getSupportFragmentManager().findFragmentByTag(TicketsEmptyFragment.TAG) == null) {
                    ActivityUtils.replaceFragment(getSupportFragmentManager(),
                            R.id.fragment_container,
                            TicketsEmptyFragment.newInstance(),
                            TicketsEmptyFragment.TAG,
                            false);
                }
                break;
        }
    }
}
