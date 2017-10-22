package com.doubletapp.hermitage.hermitage.ui.ticket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by navi9 on 22.10.2017.
 */

public class TicketsFragment extends Fragment {
    public static final String TAG =    "tickets_fragment_tag";

    @BindView(R.id.pager)
    ViewPager viewPager;

    Unbinder unbinder;

    public static TicketsFragment newInstance() {
        Bundle args = new Bundle();

        TicketsFragment fragment = new TicketsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_tickets, container, false);
        unbinder = ButterKnife.bind(this, view);

        TicketFragmentAdapter adapter = new TicketFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @OnClick(R.id.one_more)
    public void onOneMoreClicked() {
        BuyTicketActivity.start(getContext());
    }
}
