package com.doubletapp.hermitage.hermitage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.ui.exhibit.ExhibitFragment;
import com.doubletapp.hermitage.hermitage.ui.home.HomeFragment;
import com.doubletapp.hermitage.hermitage.ui.map.MapFragment;
import com.doubletapp.hermitage.hermitage.ui.nav.NavFragment;
import com.doubletapp.hermitage.hermitage.ui.ticket.TicketsEmptyFragment;
import com.doubletapp.hermitage.hermitage.ui.ticket.TicketsFragment;
import com.doubletapp.hermitage.hermitage.utils.ActivityUtils;
import com.doubletapp.hermitage.hermitage.utils.Data;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.doubletapp.hermitage.hermitage.R.id.tab_map;
import static com.doubletapp.hermitage.hermitage.ui.nav.NavItemActivity.CHANGE_TO_MAP_RESULT_CODE;

public class TabActivity extends AppCompatActivity implements OnTabSelectListener {

    @BindView(R.id.title_logo)
    ImageView mToolbarLogo;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;

    int tabToOpen = 0;

    private Map<Integer, Fragment> mFragmentMap;

    public static void start(Context context) {
        Intent starter = new Intent(context, TabActivity.class);
        context.startActivity(starter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mBottomBar.getCurrentTabId() == tab_map
                || mBottomBar.getCurrentTabId() == R.id.tab_exhibit) {

            getMenuInflater().inflate(R.menu.search_menu, menu);

            MenuItem item = menu.findItem(R.id.action_search);
            mSearchView.setMenuItem(item);

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);

        mFragmentMap = new HashMap<>();
        addFragmentToMap(R.id.tab_home);
        addFragmentToMap(R.id.tab_exhibit);
        addFragmentToMap(tab_map);
        addFragmentToMap(R.id.tab_nav);
        addFragmentToMap(R.id.tab_ticket);

        init();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if(tabToOpen != 0) {
            onTabSelected(tabToOpen);
            if (tabToOpen == tab_map) {
                mBottomBar.selectTabAtPosition(2);
            }
        }
    }

    private void init() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mBottomBar.setOnTabSelectListener(this);

    }

    private void addFragmentToMap(int id) {
        mFragmentMap.put(id, createFragmentById(id));
    }

    private Fragment createFragmentById(int id) {
        switch (id) {
            case R.id.tab_home:
                return HomeFragment.newInstance();
            case R.id.tab_exhibit:
                return ExhibitFragment.newInstance();
            case tab_map:
                return MapFragment.newInstance();
            case R.id.tab_nav:
                return NavFragment.newInstance();
            case R.id.tab_ticket:
                if (Data.tickets.isEmpty()) {
                    return TicketsEmptyFragment.newInstance();
                } else {
                    return TicketsFragment.newInstance();
                }
            default:
                return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHANGE_TO_MAP_RESULT_CODE) {
            String hallId = data.getStringExtra("hallId");

            Fragment fragment = createFragmentById(tab_map);
            Bundle bundle = fragment.getArguments();
            bundle.putString("hallId", hallId);
            mFragmentMap.put(tab_map, fragment);
            tabToOpen = tab_map;
        }
    }



    private String getFragmentTagtById(int id) {
        switch (id) {
            case R.id.tab_home:
                return HomeFragment.TAG;
            case R.id.tab_exhibit:
                return ExhibitFragment.TAG;
            case tab_map:
                return MapFragment.TAG;
            case R.id.tab_nav:
                return NavFragment.TAG;
            case R.id.tab_ticket:
                return TicketsEmptyFragment.TAG;
            default:
                return null;
        }
    }


    @Override
    public void onTabSelected(@IdRes int tabId) {
        ActivityUtils.replaceFragment(getSupportFragmentManager(),
                R.id.fragment_container,
                createFragmentById(tabId),
                getFragmentTagtById(tabId),
                false);
        invalidateOptionsMenu();
    }
}
