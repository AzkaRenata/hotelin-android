package com.example.hotelin_android.modul.tes;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class TesActivity extends BaseFragmentHolderActivity {
    TesFragment tesFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        tesFragment = new TesFragment();
        setCurrentFragment(tesFragment, false);

    }
}