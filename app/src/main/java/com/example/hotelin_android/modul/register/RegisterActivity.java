package com.example.hotelin_android.modul.register;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {
    RegisterFragment registerFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_loginregister_layout);
        flFragmentContainer = findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = findViewById(R.id.rlActivityFragmentHolder);
    }
}