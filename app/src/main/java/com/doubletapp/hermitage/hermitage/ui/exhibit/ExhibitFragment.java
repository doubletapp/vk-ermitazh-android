package com.doubletapp.hermitage.hermitage.ui.exhibit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.Exhibit;
import com.doubletapp.hermitage.hermitage.utils.Data;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExhibitFragment extends Fragment {

    public static final String TAG = "ExhibitFragment";
    @BindView(R.id.exhibit_recycler)
    RecyclerView mRecycler;
    ExhibitAdapter mAdapter;
    Unbinder unbinder;

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
        View view = inflater.inflate(R.layout.fragment_exhibit, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (mAdapter == null) {
            mAdapter = new ExhibitAdapter();
        }
        mRecycler.setAdapter(mAdapter);
        mAdapter.swap(Data.exhibits);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
