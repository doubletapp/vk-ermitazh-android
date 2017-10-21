package com.doubletapp.hermitage.hermitage.ui.ticket;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.ui.views.TimePicker;
import com.doubletapp.hermitage.hermitage.ui.views.TimePickerData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BuyTicketFragment extends Fragment {

    public static final String TAG = "BuyTicketFragment";
    @BindView(R.id.day_picker)
    TimePicker mDayPicker;
    Unbinder unbinder;
    @BindView(R.id.time_picker)
    TimePicker mTimePicker;

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
        View view = inflater.inflate(R.layout.fragment_buy_ticket, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        ArrayList<String> days = new ArrayList<>();
        days.add("ВТ");
        days.add("СР");
        days.add("ЧТ");
        days.add("ПТ");
        days.add("СБ");
        days.add("ВС");
        ArrayList<Float> percentages = new ArrayList<>();
        percentages.add(0.23f);
        percentages.add(0.47f);
        percentages.add(0.51f);
        percentages.add(0.11f);
        percentages.add(0.85f);
        percentages.add(0.66f);
        mDayPicker.swapData(new TimePickerData(days, percentages));
        ArrayList<String> timesStart = new ArrayList<>();
        timesStart.add("10:30");
        timesStart.add("12:00");
        timesStart.add("14:00");
        timesStart.add("16:00");
        timesStart.add("18:00");
        timesStart.add("20:00");
        ArrayList<String> timesEnd = new ArrayList<>();
        timesEnd.add("12:00");
        timesEnd.add("14:00");
        timesEnd.add("16:00");
        timesEnd.add("18:00");
        timesEnd.add("20:00");
        timesEnd.add("21:00");
        ArrayList<Float> percentages2 = new ArrayList<>();
        percentages2.add(0.53f);
        percentages2.add(0.88f);
        percentages2.add(0.10f);
        percentages2.add(0.38f);
        percentages2.add(0.21f);
        percentages2.add(0.50f);
        mTimePicker.swapData(new TimePickerData(timesStart, timesEnd, percentages2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
