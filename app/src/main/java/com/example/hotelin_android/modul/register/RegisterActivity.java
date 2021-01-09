package com.example.hotelin_android.modul.register;

import android.widget.RelativeLayout;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        RegisterFragment registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_loginregister_activity);
        flFragmentContainer = findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = findViewById(R.id.rlActivityFragmentHolder);
        loading = (RelativeLayout) findViewById(R.id.loading_screen);
    }
}