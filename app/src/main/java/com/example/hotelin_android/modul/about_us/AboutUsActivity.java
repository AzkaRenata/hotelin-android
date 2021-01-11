package com.example.hotelin_android.modul.about_us;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class AboutUsActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        bottomLayout.setVisibility(View.GONE);
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        setCurrentFragment(aboutUsFragment, false);
    }
}
