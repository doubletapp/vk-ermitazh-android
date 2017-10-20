package com.doubletapp.hermitage.hermitage.model;

import com.doubletapp.hermitage.hermitage.model.map.Room;

import java.util.Arrays;
import java.util.List;

/**
 * Created by navi9 on 20.10.2017.
 */

public class Hall {
    private String mId;
    private String mSubscription;
    private List<Exhibit> mExhibit;
    private List<Art> mArts;
    private Intensity mIntensity;
    private Image mImage = Image.createDefault();
    private Room mRoom;

    public Hall() {
        mIntensity = Intensity.LOW;
    }

    public String getId() {
        return mId;
    }

    public List<Exhibit> getExhibits() {
        return mExhibit;
    }

    public List<Art> getArts() {
        return mArts;
    }

    public void setIntensity(Intensity intensity) {
        mIntensity = intensity;
    }

    public Intensity getIntensity() {
        return mIntensity;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) {
        mRoom = room;
    }

    public static class Builder {
        private Hall hall;

        public Builder() {
            hall = new Hall();
        }

        public Builder setId(String id) {
            hall.mId = id;
            return this;
        }

        public Builder setSubscription(String subscription) {
            hall.mSubscription = subscription;
            return this;
        }

        public Builder setExhibits(Exhibit... exhibits) {
            hall.mExhibit = Arrays.asList(exhibits);
            return this;
        }

        public Builder setArts(Art... arts) {
            hall.mArts = Arrays.asList(arts);
            return this;
        }

        public Builder setImage(Image image) {
            hall.mImage = image;
            return this;
        }

        public Builder setRoom(Room room) {
            hall.mRoom = room;
            return this;
        }

        public Hall create() {
            return hall;
        }
    }
}
