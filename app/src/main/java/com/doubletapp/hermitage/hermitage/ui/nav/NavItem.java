package com.doubletapp.hermitage.hermitage.ui.nav;

import java.io.Serializable;

/**
 * Created by onthecrow on 21.10.2017.
 */

public class NavItem implements Serializable {
    String mTitle;
    String mDescription;
    String mLongDescription;
    int mImage;

    public NavItem(String mTitle, String mDescription, String mLongDescription, int mImage) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mLongDescription = mLongDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getImage() {
        return mImage;
    }

    public String getmLongDescription() {
        return mLongDescription;
    }
}
