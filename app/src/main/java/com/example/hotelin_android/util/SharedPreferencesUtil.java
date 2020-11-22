package com.example.hotelin_android.util;

import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    private SharedPreferences sharedPreferences;

    public SharedPreferencesUtil() {
        this.sharedPreferences = sharedPreferences;
    }

    public void setToken(String token){
        sharedPreferences.edit().putString("token", token).commit();
    }

    public String getToken(){
        return sharedPreferences.getString("token", null);
    }

    public void clear(){
        sharedPreferences.edit().clear().commit();
    }
}
