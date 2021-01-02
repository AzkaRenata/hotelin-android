package com.example.hotelin_android.modul.cancel_booking;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class CancelBookingActivity extends BaseFragmentHolderActivity{
    CancelBookingFragment cancelBookingFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int booking_id = getIntent().getIntExtra("booking_id",0);
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        cancelBookingFragment = new CancelBookingFragment(tokenSharedUtil, booking_id);
        setCurrentFragment(cancelBookingFragment, false);

    }
}
