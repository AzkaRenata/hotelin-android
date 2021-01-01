package com.example.hotelin_android.modul.home;

import android.util.Log;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();
        Log.d("home", "masuk home");
        homeFragment = new HomeFragment();
        setCurrentFragment(homeFragment, false);
    }
}