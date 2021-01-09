package com.example.hotelin_android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hotelin_android.util.SharedPreferences.HotelSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.RoomSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.UserSharedUtil;

public class UtilProvider {
    private static TokenSharedUtil tokenSharedUtil;
    private static UserSharedUtil userSharedUtil;
    private static HotelSharedUtil hotelSharedUtil;
    private static RoomSharedUtil roomSharedUtil;

    public static void initialize(Context context){
        initSharedPreferencesUtil(context);
    }

    private static void initSharedPreferencesUtil(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("example", Context.MODE_PRIVATE);
        tokenSharedUtil = new TokenSharedUtil(sharedPreferences);
        userSharedUtil = new UserSharedUtil(sharedPreferences);
        hotelSharedUtil = new HotelSharedUtil(sharedPreferences);
        roomSharedUtil = new RoomSharedUtil(sharedPreferences);
    }

    public static TokenSharedUtil getTokenSharedUtil(){
        return tokenSharedUtil;
    }

    public static UserSharedUtil getUserSharedUtil(){
        return userSharedUtil;
    }

    public static HotelSharedUtil getHotelSharedUtil(){
        return hotelSharedUtil;
    }

    public static RoomSharedUtil getRoomSharedUtil(){
        return roomSharedUtil;
    }
}
