package com.doubletapp.hermitage.hermitage.ui.exhibit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;

public class ExhibitFragment extends Fragment {

    public static final String TAG = "ExhibitFragment";

    public static ExhibitFragment newInstance() {

        Bundle args = new Bundle();

        ExhibitFragment fragment = new ExhibitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exhibit, container, false);
    }

}
