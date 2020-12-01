package com.example.hotelin_android.modul.cancel_booking;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class CancelBookingActivity extends BaseFragmentHolderActivity{
    CancelBookingFragment cancelBookingFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int booking_id = getIntent().getIntExtra("booking_id",0);
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        cancelBookingFragment = new CancelBookingFragment(sharedPreferencesUtil, booking_id);
        setCurrentFragment(cancelBookingFragment, false);

    }
}
