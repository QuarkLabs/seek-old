package com.voidx.seek;

public class User {
    public final int id;
    public final String firstName;
    public final String middleName;
    public final String lastName;
    public final double latitude;
    public final double longitude;
    public final String profilePictureURL;

    public User(int id, String firstName, String middleName, String lastName, double latitude, double longitude, String profilePictureURL) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profilePictureURL = profilePictureURL;
    }
}
