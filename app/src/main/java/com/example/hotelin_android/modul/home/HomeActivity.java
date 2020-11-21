package com.example.hotelin_android.modul.home;

<<<<<<< Updated upstream
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment loginFragment;
=======
import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
>>>>>>> Stashed changes
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

<<<<<<< Updated upstream
        loginFragment = new HomeFragment();
        setCurrentFragment(loginFragment, false);

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_loginregister_layout);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
=======
        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        homeFragment = new HomeFragment();
        setCurrentFragment(homeFragment, false);

>>>>>>> Stashed changes
    }
}