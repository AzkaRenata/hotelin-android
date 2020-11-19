package com.example.hotelin_android.interactor;

import com.example.hotelin_android.contract.MainContract;
import com.example.hotelin_android.util.SharedPreferencesUtil;

public class MainInteractor implements MainContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public MainInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public boolean isUserLogin() {
        if(sharedPreferencesUtil.getToken() != null){
            return true;
        }
        else {
            return false;
        }
    }
}
