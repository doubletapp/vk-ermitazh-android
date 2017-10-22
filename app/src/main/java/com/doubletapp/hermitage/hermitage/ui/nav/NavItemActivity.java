package com.doubletapp.hermitage.hermitage.ui.nav;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavItemActivity extends AppCompatActivity {

    public static int CHANGE_TO_MAP_RESULT_CODE;

    private static final String ARGS_NAV_ITEM = "NAV_ITEM";
    @BindView(R.id.nav_item_image)
    ImageView mImage;
    @BindView(R.id.nav_item_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_item_collapsing)
    CollapsingToolbarLayout mCollapsing;
    @BindView(R.id.nav_item_appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.nav_item_description)
    TextView mDescription;
    @BindView(R.id.nav_item_long_description)
    TextView mLongDescription;
    @BindView(R.id.nav_item_navigation_button)
    FloatingActionButton navigationActionButton;

    String mHallId;

    public static void start(Context context, @NonNull NavItem item) {
        Intent starter = new Intent(context, NavItemActivity.class);
        starter.putExtra(ARGS_NAV_ITEM, item);
        ((AppCompatActivity)context).startActivityForResult(starter, CHANGE_TO_MAP_RESULT_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_item);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        final ActionBar appBar = getSupportActionBar();
        appBar.setDisplayHomeAsUpEnabled(true);
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() > -145) //for me 1dp = 2.625px
                {
                    appBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
                } else {
                    appBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
                }
            }
        });
        setData();

        navigationActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("hallId", mHallId);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setData() {
        NavItem item = (NavItem) getIntent().getSerializableExtra(ARGS_NAV_ITEM);
        if (item != null) {
            mCollapsing.setTitle(item.getTitle());
            mDescription.setText(item.getDescription());
            mLongDescription.setText(item.getmLongDescription());
            mImage.setImageResource(item.getImage());
            mHallId = item.mHallId;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}
