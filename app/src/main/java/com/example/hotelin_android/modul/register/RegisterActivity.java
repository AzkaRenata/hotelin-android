package com.example.hotelin_android.modul.register;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {
    RegisterFragment registerFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);

    }
}