package com.example.hotelin_android.modul.home;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        HomeFragment homeFragment = new HomeFragment();
        setCurrentFragment(homeFragment, false);
    }
}