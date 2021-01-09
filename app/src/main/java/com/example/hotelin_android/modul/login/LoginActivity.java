package com.example.hotelin_android.modul.login;

import android.content.Intent;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        LoginFragment loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_loginregister_activity);
        flFragmentContainer = findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = findViewById(R.id.rlActivityFragmentHolder);
        loading = findViewById(R.id.loading_screen);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}