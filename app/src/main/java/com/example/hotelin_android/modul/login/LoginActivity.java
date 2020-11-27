package com.example.hotelin_android.modul.login;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        loginFragment = new LoginFragment(sharedPreferencesUtil);
        setCurrentFragment(loginFragment, false);

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_loginregister_layout);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
    }
}