package com.example.hotelin_android.modul.splash_screen;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class SplashScreenActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        topLayout.setVisibility(View.GONE);
        bottomLayout.setVisibility(View.GONE);
        vMenuBarShadow.setVisibility(View.GONE);
        SplashScreenFragment splashScreenFragment = new SplashScreenFragment();
        setCurrentFragment(splashScreenFragment, false);
    }
}