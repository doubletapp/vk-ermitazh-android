package com.doubletapp.hermitage.hermitage.ui.nav;

/**
 * Created by onthecrow on 21.10.2017.
 */

public class NavItem {
    String mTitle;
    String mDescription;
    int mImage;

    public NavItem(String mTitle, String mDescription, int mImage) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImage = mImage;
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
}
