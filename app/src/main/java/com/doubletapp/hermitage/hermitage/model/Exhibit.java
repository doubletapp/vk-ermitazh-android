package com.doubletapp.hermitage.hermitage.model;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Exhibit {
    private String mName;
    private String mSubscription;
    private Art mArt;
    private Image mImage = Image.createDefault();

    public Exhibit() {

    }

    public Exhibit(String name, String subscription, Art art) {
        mName = name;
        mSubscription = subscription;
        mArt = art;
    }

    public String getName() {
        return mName;
    }

    public String getSubscription() {
        return mSubscription;
    }

    public Art getArt() {
        return mArt;
    }

    public static class Builder {
        private Exhibit exhibit;

        public Builder() {
            exhibit = new Exhibit();
        }

        public Builder setName(String name) {
            exhibit.mName = name;
            return this;
        }

        public Builder setSubscription(String subscription) {
            exhibit.mSubscription = subscription;
            return this;
        }

        public Builder setArt(Art art) {
            exhibit.mArt = art;
            return this;
        }

        public Builder setImage(Image image) {
            exhibit.mImage = image;
            return this;
        }

        public Exhibit create() {
            return exhibit;
        }
    }
}
