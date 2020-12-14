package com.example.hotelin_android.util;

import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesUtil {
    private final SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreferencesUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setToken(String token){
        Log.e("testing", token);
        sharedPreferences.edit().putString("token", token).apply();
    }

    public String getToken(){
        return sharedPreferences.getString("token", null);
    }

    public void clear(){
        editor.putString("token", null).apply();
    }
}
