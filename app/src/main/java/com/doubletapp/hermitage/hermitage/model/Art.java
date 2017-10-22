package com.doubletapp.hermitage.hermitage.model;

import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Art {
    private String mName;
    private String mDescription;
    private int mImage;
    private List<Exhibit> mExhibitions;

    public Art(String mName, String mDescription, int mImage, List<Exhibit> mExhibitions) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mExhibitions = mExhibitions;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public List<Exhibit> getExhibitions() {
        return mExhibitions;
    }

    public void setExhibitions(List<Exhibit> mExhibitions) {
        this.mExhibitions = mExhibitions;
    }
}
