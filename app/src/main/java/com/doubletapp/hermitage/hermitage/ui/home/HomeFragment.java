package com.doubletapp.hermitage.hermitage.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.home_recycler)
    RecyclerView mRecycler;
    HomeAdapter mAdapter;
    Unbinder unbinder;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (mAdapter == null) {
            mAdapter = new HomeAdapter();
        }
        mRecycler.setAdapter(mAdapter);
        mAdapter.swap(makeFakeFeed());
        return view;
    }

    private List<HomeItem> makeFakeFeed() {
        List<HomeItem> list = new ArrayList<>();
        list.add(new HomeItem(getString(R.string.item_feed_title_0),
                getString(R.string.item_feed_description_0),
                R.drawable.feed_1));
        list.add(new HomeItem(getString(R.string.item_feed_title_1),
                getString(R.string.item_feed_description_1),
                R.drawable.feed_2));
        list.add(new HomeItem(getString(R.string.item_feed_title_2),
                getString(R.string.item_feed_description_2),
                R.drawable.feed_3));
        list.add(new HomeItem(getString(R.string.item_feed_title_3),
                getString(R.string.item_feed_description_3),
                R.drawable.feed_4));
        list.add(new HomeItem(getString(R.string.item_feed_title_4),
                getString(R.string.item_feed_description_4),
                R.drawable.feed_5));
        list.add(new HomeItem(getString(R.string.item_feed_title_5),
                getString(R.string.item_feed_description_5),
                R.drawable.feed_6));
        list.add(new HomeItem(getString(R.string.item_feed_title_6),
                getString(R.string.item_feed_description_6),
                R.drawable.feed_7));
        list.add(new HomeItem(getString(R.string.item_feed_title_7),
                getString(R.string.item_feed_description_7),
                R.drawable.feed_8));
        list.add(new HomeItem(getString(R.string.item_feed_title_8),
                getString(R.string.item_feed_description_8),
                R.drawable.feed_9));
        list.add(new HomeItem(getString(R.string.item_feed_title_9),
                getString(R.string.item_feed_description_9),
                R.drawable.feed_10));
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
