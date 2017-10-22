package com.doubletapp.hermitage.hermitage.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeItemActivity extends AppCompatActivity {

    private static final String TAG = "HomeItemActivity";

    private static final String ARGS_HOME_ITEM = "HOME_ITEM";
    @BindView(R.id.home_item_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mCollapsing;
    @BindView(R.id.main_appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.home_item_image)
    ImageView mImage;
    @BindView(R.id.home_item_description)
    TextView mDescription;
    @BindView(R.id.home_item_long_description)
    TextView mLongDescription;

    public static void start(Context context, @NonNull HomeItem item) {
        Intent starter = new Intent(context, HomeItemActivity.class);
        starter.putExtra(ARGS_HOME_ITEM, item);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item);
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
    }

    private void setData() {
        HomeItem item = (HomeItem) getIntent().getSerializableExtra(ARGS_HOME_ITEM);
        if (item != null) {
            mCollapsing.setTitle(item.mTitle);
            mDescription.setText(item.mDescription);
            mLongDescription.setText(item.mLongDescription);
            mImage.setImageResource(item.mImage);
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
