package com.doubletapp.hermitage.hermitage.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.doubletapp.hermitage.hermitage.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimePicker extends ConstraintLayout {

    private static final int MODE_DAYS = 0;

    @Nullable
    TimePickerData mData;
    int mPickerMode;
    @BindView(R.id.day0)
    TextView day0;
    @BindView(R.id.timeStart0)
    TextView timeStart0;
    @BindView(R.id.timeEnd0)
    TextView timeEnd0;
    @BindView(R.id.chunk0_fill)
    View chunk0Fill;
    @BindView(R.id.day1)
    TextView day1;
    @BindView(R.id.timeStart1)
    TextView timeStart1;
    @BindView(R.id.timeEnd1)
    TextView timeEnd1;
    @BindView(R.id.chunk1_fill)
    View chunk1Fill;
    @BindView(R.id.day2)
    TextView day2;
    @BindView(R.id.timeStart2)
    TextView timeStart2;
    @BindView(R.id.timeEnd2)
    TextView timeEnd2;
    @BindView(R.id.chunk2_fill)
    View chunk2Fill;
    @BindView(R.id.day3)
    TextView day3;
    @BindView(R.id.timeStart3)
    TextView timeStart3;
    @BindView(R.id.timeEnd3)
    TextView timeEnd3;
    @BindView(R.id.chunk3_fill)
    View chunk3Fill;
    @BindView(R.id.day4)
    TextView day4;
    @BindView(R.id.timeStart4)
    TextView timeStart4;
    @BindView(R.id.timeEnd4)
    TextView timeEnd4;
    @BindView(R.id.chunk4_fill)
    View chunk4Fill;
    @BindView(R.id.day5)
    TextView day5;
    @BindView(R.id.timeStart5)
    TextView timeStart5;
    @BindView(R.id.timeEnd5)
    TextView timeEnd5;
    @BindView(R.id.chunk5_fill)
    View chunk5Fill;
    @BindColor(R.color.column_blue)
    int mBlueColor;
    @BindColor(R.color.column_gray)
    int mGrayColor;

    public TimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(@NonNull Context context,
                      @Nullable AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TimePicker,
                0, 0);
        try {
            mPickerMode = a.getInteger(R.styleable.TimePicker_pickerMode, 0);
        } finally {
            a.recycle();
        }
        inflate(getContext(), R.layout.time_picker, this);
        ButterKnife.bind(this, getRootView());
        if (mPickerMode == MODE_DAYS) {
            fillDaysData();
        } else {
            fillTimeData();
        }
    }

    private void refresh() {
        if (mPickerMode == MODE_DAYS) {
            fillDaysData();
        } else {
            fillTimeData();
        }
    }

    private void fillDaysData() {
        if (mData == null) return;

        List<String> days = mData.getDays();
        List<Float> percentages = mData.getPercentages();
        float absoleteHeight = getResources().getDimension(R.dimen.time_picker_height);

        day0.setText(days.get(0));
        timeEnd0.setVisibility(GONE);
        timeStart0.setVisibility(GONE);
        LayoutParams params0 = (LayoutParams) chunk0Fill.getLayoutParams();
        params0.height = (int)(absoleteHeight * percentages.get(0));

        day1.setText(days.get(1));
        timeEnd1.setVisibility(GONE);
        timeStart1.setVisibility(GONE);
        LayoutParams params1 = (LayoutParams) chunk1Fill.getLayoutParams();
        params1.height = (int)(absoleteHeight * percentages.get(1));

        day2.setText(days.get(2));
        timeEnd2.setVisibility(GONE);
        timeStart2.setVisibility(GONE);
        LayoutParams params2 = (LayoutParams) chunk2Fill.getLayoutParams();
        params2.height = (int)(absoleteHeight * percentages.get(2));

        day3.setText(days.get(3));
        timeEnd3.setVisibility(GONE);
        timeStart3.setVisibility(GONE);
        LayoutParams params3 = (LayoutParams) chunk3Fill.getLayoutParams();
        params3.height = (int)(absoleteHeight * percentages.get(3));

        day4.setText(days.get(4));
        timeEnd4.setVisibility(GONE);
        timeStart4.setVisibility(GONE);
        LayoutParams params4 = (LayoutParams) chunk4Fill.getLayoutParams();
        params4.height = (int)(absoleteHeight * percentages.get(4));

        day5.setText(days.get(5));
        timeEnd5.setVisibility(GONE);
        timeStart5.setVisibility(GONE);
        LayoutParams params5 = (LayoutParams) chunk5Fill.getLayoutParams();
        params5.height = (int)(absoleteHeight * percentages.get(5));

        selectMin();
    }

    private void fillTimeData() {
        if (mData == null) return;

        List<String> startTimes = mData.getStartTimes();
        List<String> endTimes = mData.getEndTimes();
        List<Float> percentages = mData.getPercentages();
        float absoleteHeight = getResources().getDimension(R.dimen.time_picker_height);

        day0.setVisibility(GONE);
        timeEnd0.setText(endTimes.get(0));
        timeStart0.setText(startTimes.get(0));
        LayoutParams params0 = (LayoutParams) chunk0Fill.getLayoutParams();
        params0.height = (int)(absoleteHeight * percentages.get(0));

        day1.setVisibility(GONE);
        timeEnd1.setText(endTimes.get(1));
        timeStart1.setText(startTimes.get(1));
        LayoutParams params1 = (LayoutParams) chunk1Fill.getLayoutParams();
        params1.height = (int)(absoleteHeight * percentages.get(1));

        day2.setVisibility(GONE);
        timeEnd2.setText(endTimes.get(2));
        timeStart2.setText(startTimes.get(2));
        LayoutParams params2 = (LayoutParams) chunk2Fill.getLayoutParams();
        params2.height = (int)(absoleteHeight * percentages.get(2));

        day3.setVisibility(GONE);
        timeEnd3.setText(endTimes.get(3));
        timeStart3.setText(startTimes.get(3));
        LayoutParams params3 = (LayoutParams) chunk3Fill.getLayoutParams();
        params3.height = (int)(absoleteHeight * percentages.get(3));

        day4.setVisibility(GONE);
        timeEnd4.setText(endTimes.get(4));
        timeStart4.setText(startTimes.get(4));
        LayoutParams params4 = (LayoutParams) chunk4Fill.getLayoutParams();
        params0.height = (int)(absoleteHeight * percentages.get(0));

        day5.setVisibility(GONE);
        timeEnd5.setText(endTimes.get(5));
        timeStart5.setText(startTimes.get(5));
        LayoutParams params5 = (LayoutParams) chunk5Fill.getLayoutParams();
        params5.height = (int)(absoleteHeight * percentages.get(5));

        selectMin();
    }

    private void selectMin() {
        Float min = Collections.min(mData.getPercentages());
        int minIndex = mData.getPercentages().indexOf(min);
        switch (minIndex) {
            case 0:
                chunk0Fill.setBackgroundColor(mBlueColor);
                break;
            case 1:
                chunk1Fill.setBackgroundColor(mBlueColor);
                break;
            case 2:
                chunk2Fill.setBackgroundColor(mBlueColor);
                break;
            case 3:
                chunk3Fill.setBackgroundColor(mBlueColor);
                break;
            case 4:
                chunk4Fill.setBackgroundColor(mBlueColor);
                break;
            case 5:
                chunk5Fill.setBackgroundColor(mBlueColor);
                break;
        }
    }

    public void swapData(@NonNull TimePickerData data) {
        mData = data;
        refresh();
    }

    @OnClick(R.id.chunk0)
    public void onChunk0Clicked() {
    }

    @OnClick(R.id.chunk1)
    public void onChunk1Clicked() {
    }

    @OnClick(R.id.chunk2)
    public void onChunk2Clicked() {
    }

    @OnClick(R.id.chunk3)
    public void onChunk3Clicked() {
    }

    @OnClick(R.id.chunk4)
    public void onChunk4Clicked() {
    }

    @OnClick(R.id.chunk5)
    public void onChunk5Clicked() {
    }
}
