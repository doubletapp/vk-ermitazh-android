package com.doubletapp.hermitage.hermitage.utils;

import com.doubletapp.hermitage.hermitage.model.Art;
import com.doubletapp.hermitage.hermitage.model.Exhibit;
import com.doubletapp.hermitage.hermitage.model.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by onthecrow on 22.10.2017.
 */

public final class Data {
    public static List<Art> arts;
    public static List<Exhibit> exhibits;
    public static List<Ticket> tickets = new ArrayList<>(Arrays.asList(new Ticket("20-10-2017", "12:00 - 14:00" , "H32SM21TV"), new Ticket("2q-10-2017", "19:00 - 20:00" , "H22SM10TV")));
}
