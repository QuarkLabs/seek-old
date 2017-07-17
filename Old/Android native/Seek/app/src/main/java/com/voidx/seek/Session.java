package com.voidx.seek;

import android.location.Location;

import java.util.HashMap;

public class Session {
    public static Location lastCurrentLocation;
    public static HashMap<Integer, User> userHashMap = new HashMap<>();

    static {
        lastCurrentLocation = new Location("");
        lastCurrentLocation.setLatitude(0.0);
        lastCurrentLocation.setLongitude(0.0);

        User oshaniUser = new User(1, "Oshani", "", "Weerakoon", lastCurrentLocation.getLatitude() + 0.0025, lastCurrentLocation.getLongitude() + 0.0039, "");
        User sumedheUser = new User(2, "Sumedhe", "", "Dissanayake", lastCurrentLocation.getLatitude() + 0.0004, lastCurrentLocation.getLongitude() + 0.0028, "");
        User jinadiUser = new User(3, "Jinadi", "", "Yasiruka", lastCurrentLocation.getLatitude() + 0.0009, lastCurrentLocation.getLongitude() + 0.0050, "");
        User kavindaUser = new User(4, "Kavinda", "", "Niroshan", lastCurrentLocation.getLatitude() + 0.0051, lastCurrentLocation.getLongitude() + 0.0005, "");
        User supulUser = new User(5, "Supul", "", "Dulanka", lastCurrentLocation.getLatitude() - 0.0030, lastCurrentLocation.getLongitude() + 0.0004, "");

        userHashMap.put(oshaniUser.id, oshaniUser);
        userHashMap.put(sumedheUser.id, sumedheUser);
        userHashMap.put(jinadiUser.id, jinadiUser);
        userHashMap.put(kavindaUser.id, kavindaUser);
        userHashMap.put(supulUser.id, supulUser);
    }
}
