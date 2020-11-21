package com.example.hotelin_android.modul.login;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

import java.util.ArrayList;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);

    }

    void performMove(LoginContract.View v){

    }
}