package com.doubletapp.hermitage.hermitage.model;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Image {
    private static final String DEFAULT_IMAGE_PATH = "";

    private String mPath;

    public static Image createDefault() {
        return new Image(DEFAULT_IMAGE_PATH);
    }

    public Image(String path) {
        mPath = path;
    }

    public String getPath() {
        return mPath;
    }

}
