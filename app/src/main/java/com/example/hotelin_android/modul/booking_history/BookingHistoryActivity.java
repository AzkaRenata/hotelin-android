package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class BookingHistoryActivity extends BaseFragmentHolderActivity {
    private BookingHistoryFragment bookingHistoryFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        bookingHistoryFragment = new BookingHistoryFragment();
        setCurrentFragment(bookingHistoryFragment, false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bookingHistoryFragment = new BookingHistoryFragment();
        setCurrentFragment(bookingHistoryFragment, false);
    }
}
