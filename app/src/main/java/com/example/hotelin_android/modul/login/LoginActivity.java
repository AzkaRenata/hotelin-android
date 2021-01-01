package com.example.hotelin_android.modul.login;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        loginFragment = new LoginFragment(tokenSharedUtil);
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