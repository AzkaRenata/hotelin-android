package com.example.hotelin_android.modul.itemDetails;

import android.view.View;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class ItemDetailsActivity extends BaseFragmentHolderActivity {
    ItemDetailsFragment itemDetailsFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);

        itemDetailsFragment = new ItemDetailsFragment();
        setCurrentFragment(itemDetailsFragment, false);

    }
}