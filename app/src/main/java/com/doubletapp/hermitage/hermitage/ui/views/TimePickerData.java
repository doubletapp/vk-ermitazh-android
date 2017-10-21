package com.doubletapp.hermitage.hermitage.ui.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by onthecrow on 21.10.2017.
 */

public class TimePickerData {
    @Nullable
    private ArrayList<String> mDays;
    @Nullable
    private ArrayList<String> mStartTimes;
    @Nullable
    private ArrayList<String> mEndTimes;
    @NonNull
    private ArrayList<Float> mPercentages;

    public TimePickerData(@Nullable ArrayList<String> mDays,
                          @NonNull ArrayList<Float> mPercentages) {
        this.mDays = mDays;
        this.mPercentages = mPercentages;
    }

    public TimePickerData(@Nullable ArrayList<String> mStartTimes,
                          @Nullable ArrayList<String> mEndTimes,
                          @NonNull ArrayList<Float> mPercentages) {
        this.mStartTimes = mStartTimes;
        this.mEndTimes = mEndTimes;
        this.mPercentages = mPercentages;
    }

    @Nullable
    public ArrayList<String> getDays() {
        return mDays;
    }

    @Nullable
    public ArrayList<String> getStartTimes() {
        return mStartTimes;
    }

    @Nullable
    public ArrayList<String> getEndTimes() {
        return mEndTimes;
    }

    @NonNull
    public ArrayList<Float> getPercentages() {
        return mPercentages;
    }
}

