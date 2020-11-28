package com.example.hotelin_android.modul.previewBooking;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.modul.booking.BookingFragment;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class PreviewBookingActivity extends BaseFragmentHolderActivity {
    com.example.hotelin_android.modul.booking.BookingFragment bookingFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        bookingFragment = new BookingFragment(sharedPreferencesUtil);
        setCurrentFragment(bookingFragment, false);

    }

}