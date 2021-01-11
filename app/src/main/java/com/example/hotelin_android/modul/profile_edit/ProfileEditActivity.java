package com.example.hotelin_android.modul.profile_edit;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class ProfileEditActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        bottomLayout.setVisibility(View.GONE);
        com.example.hotelin_android.modul.profile_edit.ProfileEditFragment profileEditFragment = new ProfileEditFragment();
        setCurrentFragment(profileEditFragment, false);
    }

}
