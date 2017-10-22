package com.doubletapp.hermitage.hermitage.ui.ticket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by navi9 on 22.10.2017.
 */

public class TicketFragment extends Fragment {
    public static final String TAG = "ticket_fargment_tag";

    private static final String DATE_BUNDLE = "date_bundle";
    private static final String TIME_BUNDLE = "time_bundle";
    private static final String CODE_BUNDLE = "code_bundle";

    @BindView(R.id.date)
    TextView dateTextView;
    @BindView(R.id.time)
    TextView timeTextView;
    @BindView(R.id.code)
    TextView codeTextView;

    Unbinder unbinder;

    private String date;
    private String time;
    private String code;

    public static TicketFragment newInstance(String date, String time, String code) {
        Bundle args = new Bundle();
        args.putString(DATE_BUNDLE, date);
        args.putString(TIME_BUNDLE, time);
        args.putString(CODE_BUNDLE, code);

        TicketFragment fragment = new TicketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        date = getArguments().getString(DATE_BUNDLE, "");
        time = getArguments().getString(TIME_BUNDLE, "");
        code = getArguments().getString(CODE_BUNDLE, "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_ticket, container, false);
        unbinder = ButterKnife.bind(this, view);

        dateTextView.setText(date);
        timeTextView.setText(time);
        codeTextView.setText(code);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
