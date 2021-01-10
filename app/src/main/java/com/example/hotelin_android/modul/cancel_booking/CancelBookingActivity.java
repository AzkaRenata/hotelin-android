package com.example.hotelin_android.modul.cancel_booking;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class CancelBookingActivity extends BaseFragmentHolderActivity{
    @Override
    protected void initializeFragment() {
        initializeView();
        int booking_id = getIntent().getIntExtra("booking_id",0);
        CancelBookingFragment cancelBookingFragment = new CancelBookingFragment(booking_id);
        setCurrentFragment(cancelBookingFragment, false);
    }
}
