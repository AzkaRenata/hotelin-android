package com.example.hotelin_android.util.SharedPreferences;

import android.content.SharedPreferences;

import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.model.Room;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RoomSharedUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public RoomSharedUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setRoom(Room roo){
        Gson gson = new Gson();
        String json = gson.toJson(roo);
        editor.putString("Room", json);
        editor.commit();
    }

    public Room getRoom(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Room", null);
        Type type = new TypeToken<Room>(){}.getType();
        Room room = gson.fromJson(json, type);

        return room;
    }

    public void clear(){
        editor.putString("Room", null).apply();
    }

}
