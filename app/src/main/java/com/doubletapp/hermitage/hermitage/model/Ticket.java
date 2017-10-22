package com.doubletapp.hermitage.hermitage.model;

/**
 * Created by navi9 on 22.10.2017.
 */

public class Ticket {
    private String date;
    private String time;
    private String code;

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCode() {
        return code;
    }

    public Ticket(String date, String time, String code) {
        this.date = date;
        this.time = time;
        this.code = code;
    }
}
