package com.example.hotelin_android.util.SharedPreferences;

import android.content.SharedPreferences;

import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.model.Hotel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class BookingSharedUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public BookingSharedUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setBooking(Booking booking){
        Gson gson = new Gson();
        String json = gson.toJson(booking);
        editor.putString("Booking", json);
        editor.commit();
    }

    public Booking getBooking(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Booking", null);
        Type type = new TypeToken<Booking>(){}.getType();
        Booking booking = gson.fromJson(json, type);

        return booking;
    }

    public void clear(){
        editor.putString("Booking", null).apply();
    }

}
