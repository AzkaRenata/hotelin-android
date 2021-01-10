package com.example.hotelin_android.modul.profile;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {
    private ProfileFragment profileFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);
    }
}
