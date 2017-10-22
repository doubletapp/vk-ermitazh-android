package com.doubletapp.hermitage.hermitage.ui.home;

import java.io.Serializable;

/**
 * Created by onthecrow on 21.10.2017.
 */

public class HomeItem implements Serializable {
    String mTitle;
    String mDescription;
    String mLongDescription;
    int mImage;

    public HomeItem(String mTitle, String mDescription, String mLongDescription, int mImage) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mLongDescription = mLongDescription;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmLongDescription() {
        return mLongDescription;
    }
}
