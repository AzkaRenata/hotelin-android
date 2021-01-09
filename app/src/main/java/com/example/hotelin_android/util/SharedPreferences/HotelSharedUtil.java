package com.example.hotelin_android.util.SharedPreferences;

import android.content.SharedPreferences;

import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HotelSharedUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public HotelSharedUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setHotel(Hotel hotel){
        Gson gson = new Gson();
        String json = gson.toJson(hotel);
        editor.putString("Hotel", json);
        editor.commit();
    }

    public Hotel getHotel(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Hotel", "");
        Type type = new TypeToken<Hotel>(){}.getType();
        Hotel hotel = gson.fromJson(json, type);

        return hotel;
    }

    public void clear(){
        editor.putString("Hotel", null).apply();
    }

}
