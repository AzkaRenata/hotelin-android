package com.example.hotelin_android.modul.change_password;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class ChangePasswordActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeFragment() {
        initializeView();
        bottomLayout.setVisibility(View.GONE);
        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        setCurrentFragment(changePasswordFragment, false);

    }

}