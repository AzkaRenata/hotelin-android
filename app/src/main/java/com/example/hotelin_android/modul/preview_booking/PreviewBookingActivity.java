package com.example.hotelin_android.modul.preview_booking;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class PreviewBookingActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeFragment() {
        initializeView();
        String check_in = getIntent().getStringExtra("check_in");
        String check_out = getIntent().getStringExtra("check_out");

        PreviewBookingFragment previewBookingFragment = new PreviewBookingFragment(check_in, check_out);
        setCurrentFragment(previewBookingFragment, false);

    }

}