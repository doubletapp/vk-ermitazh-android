package com.doubletapp.hermitage.hermitage.ui.ticket;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;
import com.doubletapp.hermitage.hermitage.model.Ticket;
import com.doubletapp.hermitage.hermitage.ui.views.TimePicker;
import com.doubletapp.hermitage.hermitage.ui.views.TimePickerData;
import com.doubletapp.hermitage.hermitage.utils.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BuyTicketFragment extends Fragment implements TimePicker.OnDayChanged, TimePicker.OnTimeChanged {

    public static final String TAG = "BuyTicketFragment";
    @BindView(R.id.day_picker)
    TimePicker mDayPicker;
    Unbinder unbinder;
    @BindView(R.id.time_picker)
    TimePicker mTimePicker;
    @BindView(R.id.buy_ticket_plus)
    ImageView mPlus;
    @BindView(R.id.buy_ticket_count)
    TextView mCount;
    @BindView(R.id.buy_ticket_minus)
    ImageView mMinus;
    @BindView(R.id.buy_ticket_calendar)
    CheckBox mCalendar;
    @BindView(R.id.buy_ticket_price)
    TextView mPrice;

    int count = 1;

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
        count = 1;
        mCount.setText("1");
        mCalendar.setChecked(true);
        ArrayList<String> days = new ArrayList<>();
        days.add("ВТ");
        days.add("СР");
        days.add("ЧТ");
        days.add("ПТ");
        days.add("СБ");
        days.add("ВС");
        ArrayList<Float> percentages = new ArrayList<>();
        Random rnd = new Random();
        percentages.add(rnd.nextFloat());
        percentages.add(rnd.nextFloat());
        percentages.add(rnd.nextFloat());
        percentages.add(rnd.nextFloat());
        percentages.add(rnd.nextFloat());
        percentages.add(rnd.nextFloat());
        mDayPicker.swapData(new TimePickerData(days, percentages));
        mDayPicker.setmOnDayChangedListener(this);
        setTimeData();
        mTimePicker.setmOnTimeChangedListener(this);
    }

    private void setTimeData() {
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
        Random rnd = new Random();
        percentages2.add(rnd.nextFloat());
        percentages2.add(rnd.nextFloat());
        percentages2.add(rnd.nextFloat());
        percentages2.add(rnd.nextFloat());
        percentages2.add(rnd.nextFloat());
        percentages2.add(rnd.nextFloat());
        mTimePicker.swapData(new TimePickerData(timesStart, timesEnd, percentages2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.buy_ticket_button)
    public void onViewClicked() {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2017, 9, 24, 10, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2017, 9, 24, 12, 0);
        switch (mDayPicker.getSelectedPosition()) {
            case 2:
                beginTime.add(Calendar.DAY_OF_WEEK, 1);
                endTime.add(Calendar.DAY_OF_WEEK, 1);
                break;
            case 3:
                beginTime.add(Calendar.DAY_OF_WEEK, 2);
                endTime.add(Calendar.DAY_OF_WEEK, 2);
                break;
            case 4:
                beginTime.add(Calendar.DAY_OF_WEEK, 3);
                endTime.add(Calendar.DAY_OF_WEEK, 3);
                break;
            case 5:
                beginTime.add(Calendar.DAY_OF_WEEK, 4);
                endTime.add(Calendar.DAY_OF_WEEK, 4);
                break;
            case 6:
                beginTime.add(Calendar.DAY_OF_WEEK, 5);
                endTime.add(Calendar.DAY_OF_WEEK, 5);
                break;
        }
        switch (mTimePicker.getSelectedPosition()) {
            case 2:
                beginTime.add(Calendar.HOUR, 2);
                endTime.add(Calendar.HOUR, 2);
                break;
            case 3:
                beginTime.add(Calendar.HOUR, 4);
                endTime.add(Calendar.HOUR, 4);
                break;
            case 4:
                beginTime.add(Calendar.HOUR, 6);
                endTime.add(Calendar.HOUR, 6);
                break;
            case 5:
                beginTime.add(Calendar.HOUR, 8);
                endTime.add(Calendar.HOUR, 8);
                break;
            case 6:
                beginTime.add(Calendar.HOUR, 10);
                endTime.add(Calendar.HOUR, 10);
                break;
        }

        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm", Locale.getDefault());
        for (int i = 0; i < count; i++) {
            Data.tickets.add(new Ticket(format.format(beginTime.getTime()),
                    formatTime.format(beginTime.getTime()) + " - " + formatTime.format(endTime.getTime()),
                    "H32SM21TV"));
        }

        if (mCalendar.isChecked()) {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("title", "Посещение Эрмитажа");
            intent.putExtra("beginTime", beginTime.getTimeInMillis());
            intent.putExtra("endTime", endTime.getTimeInMillis());
            startActivity(intent);
        }
    }

    @OnClick(R.id.buy_ticket_plus)
    public void onPlusClicked() {
        if (count < 9) {
            count++;
            mCount.setText(String.valueOf(count));
        }
    }

    @OnClick(R.id.buy_ticket_minus)
    public void onMinusClicked() {
        if (count > 1) {
            count--;
            mCount.setText(String.valueOf(count));
        }
    }

    @Override
    public void onDayChange(int day) {
        setTimeData();
    }

    @Override
    public void onTimeChange(int time) {

    }
}
