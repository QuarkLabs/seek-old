package com.voidx.seek;

public class ProfilePictureHandler {
    public static int getProfilePictureResourceId(int userId){
        switch (userId){
            case 1:
                return R.drawable.oshani;
            case 2:
                return R.drawable.sumedhe;
            case 3:
                return R.drawable.jinadi;
            case 4:
                return R.drawable.kavinda;
            case 5:
                return R.drawable.supul;
            default:
                return R.drawable.oshan;
        }
    }
}
