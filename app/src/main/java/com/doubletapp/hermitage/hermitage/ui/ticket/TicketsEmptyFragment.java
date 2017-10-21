package com.doubletapp.hermitage.hermitage.ui.ticket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TicketsEmptyFragment extends Fragment {

    public static final String TAG = "TicketsEmptyFragment";
    Unbinder unbinder;

    public static TicketsEmptyFragment newInstance() {

        Bundle args = new Bundle();

        TicketsEmptyFragment fragment = new TicketsEmptyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tickets_empty, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tickets_empty_button)
    public void onViewClicked() {
        BuyTicketActivity.start(getContext());
    }
}
