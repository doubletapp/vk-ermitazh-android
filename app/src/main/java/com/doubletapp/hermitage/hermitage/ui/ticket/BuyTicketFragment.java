package com.doubletapp.hermitage.hermitage.ui.ticket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;

public class BuyTicketFragment extends Fragment {

    public static final String TAG = "BuyTicketFragment";

    public static BuyTicketFragment newInstance() {

        Bundle args = new Bundle();

        BuyTicketFragment fragment = new BuyTicketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_ticket, container, false);
    }

}
