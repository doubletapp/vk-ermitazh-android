package com.doubletapp.hermitage.hermitage.ui.nav;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NavFragment extends Fragment implements NavAdapter.OnItemClick {

    public static final String TAG = "NavFragment";
    @BindView(R.id.nav_recycler)
    RecyclerView mRecycler;
    NavAdapter mAdapter;
    Unbinder unbinder;

    public static NavFragment newInstance() {

        Bundle args = new Bundle();

        NavFragment fragment = new NavFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nav, container, false);
        unbinder = ButterKnife.bind(this, v);
        if (mAdapter == null) {
            mAdapter = new NavAdapter(this);
        }
        mRecycler.setAdapter(mAdapter);
        mAdapter.swap(makeFakeData());
        return v;
    }

    private List<NavItem> makeFakeData() {
        List<NavItem> list = new ArrayList<>();
        list.add(new NavItem(getString(R.string.item_nav_title_1),
                getString(R.string.item_nav_description_1),
                R.drawable.item_nav_1));
        list.add(new NavItem(getString(R.string.item_nav_title_2),
                getString(R.string.item_nav_description_2),
                R.drawable.item_nav_2));
        list.add(new NavItem(getString(R.string.item_nav_title_3),
                getString(R.string.item_nav_description_3),
                R.drawable.item_nav_3));
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(@NonNull NavItem item) {
        Log.d(TAG, "onClick: " + item.getTitle());
    }
}
