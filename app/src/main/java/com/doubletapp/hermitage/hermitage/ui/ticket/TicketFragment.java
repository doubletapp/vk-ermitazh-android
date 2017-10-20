package com.doubletapp.hermitage.hermitage.ui.ticket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TicketFragment extends Fragment {

    public static final String TAG = "TicketFragment";
    @BindView(R.id.buy)
    TextView mBuyButton;
    Unbinder unbinder;

    public static TicketFragment newInstance() {

        Bundle args = new Bundle();

        TicketFragment fragment = new TicketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.buy)
    public void onViewClicked() {
        BuyTicketActivity.start(getContext());
    }
}
