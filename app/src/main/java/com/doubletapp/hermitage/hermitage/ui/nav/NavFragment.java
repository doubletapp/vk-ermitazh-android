package com.doubletapp.hermitage.hermitage.ui.nav;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;

public class NavFragment extends Fragment {

    public static final String TAG = "NavFragment";

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
        return inflater.inflate(R.layout.fragment_nav, container, false);
    }

}
