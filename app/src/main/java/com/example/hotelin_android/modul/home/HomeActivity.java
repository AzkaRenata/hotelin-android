package com.example.hotelin_android.modul.home;

import android.util.Log;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {
    private HomeFragment homeFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        homeFragment = new HomeFragment();
        setCurrentFragment(homeFragment, false);
    }
}