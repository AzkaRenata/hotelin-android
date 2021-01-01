package com.example.hotelin_android.modul.login;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_loginregister_layout);
        flFragmentContainer = findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = findViewById(R.id.rlActivityFragmentHolder);
    }
}