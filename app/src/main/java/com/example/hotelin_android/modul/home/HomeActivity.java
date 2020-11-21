package com.example.hotelin_android.modul.home;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment loginFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        loginFragment = new HomeFragment();
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