package com.example.hotelin_android.modul.home;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.modul.login.LoginFragment;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

import android.view.View;

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