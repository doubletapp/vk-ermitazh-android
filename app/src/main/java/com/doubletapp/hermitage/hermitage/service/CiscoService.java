package com.doubletapp.hermitage.hermitage.service;

import com.doubletapp.hermitage.hermitage.model.map.Room;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by navi9 on 20.10.2017.
 */

public class CiscoService {

    private Random mRandom;
    private PublishSubject<Room> mUserRoomPublishSubject;

    public CiscoService()
    {
        mUserRoomPublishSubject = PublishSubject.create();
        mRandom = new Random();
    }

    public void getNext(Room room) {
        List<Room> rooms = room.getNeighbors();
        int randomPosition = mRandom.nextInt(rooms.size());
        mUserRoomPublishSubject.onNext(rooms.get(randomPosition));
    }

    public Observable<Room> getUserRoomObservable() {
        return mUserRoomPublishSubject;
    }
}
