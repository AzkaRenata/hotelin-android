package com.example.hotelin_android.util.SharedPreferences;

import android.content.SharedPreferences;

import com.example.hotelin_android.model.User;
import com.google.gson.Gson;

public class UserSharedUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public UserSharedUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setUser(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("User", json);
        editor.commit();
    }

    public User getUser(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User", "");
        User user = gson.fromJson(json, User.class);

        return user;
    }

    public void clear(){
        editor.putString("User", null).apply();
    }

}
