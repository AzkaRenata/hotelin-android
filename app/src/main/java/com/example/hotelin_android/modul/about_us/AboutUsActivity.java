package com.example.hotelin_android.modul.about_us;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class AboutUsActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        setCurrentFragment(aboutUsFragment, false);

    }
}
