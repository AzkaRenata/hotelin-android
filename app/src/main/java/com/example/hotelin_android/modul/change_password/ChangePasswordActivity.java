package com.example.hotelin_android.modul.change_password;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class ChangePasswordActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeFragment() {
        initializeView();
        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        setCurrentFragment(changePasswordFragment, false);

    }

}