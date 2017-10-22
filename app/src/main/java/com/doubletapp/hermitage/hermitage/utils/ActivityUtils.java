package com.doubletapp.hermitage.hermitage.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by onthecrow on 20.10.2017.
 */

public class ActivityUtils {
    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @IdRes int containerId,
                                       @NonNull Fragment fragment,
                                       @NonNull String tag,
                                       boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId,
                fragment,
                tag);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }
}
