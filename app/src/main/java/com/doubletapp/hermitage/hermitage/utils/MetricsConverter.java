package com.doubletapp.hermitage.hermitage.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by navi9 on 21.10.2017.
 */

public class MetricsConverter {

    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }

    public static int convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) dp;
    }
}
