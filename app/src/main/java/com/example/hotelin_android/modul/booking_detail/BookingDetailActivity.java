package com.example.hotelin_android.modul.booking_detail;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class BookingDetailActivity extends BaseFragmentHolderActivity {
    private BookingDetailFragment bookingDetailFragment;
    private int booking_id;

    @Override
    protected void initializeFragment() {
        initializeView();
        booking_id = getIntent().getIntExtra("booking_id",0);
        bookingDetailFragment = new BookingDetailFragment(booking_id);
        setCurrentFragment(bookingDetailFragment, false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bookingDetailFragment = new BookingDetailFragment(booking_id);
        setCurrentFragment(bookingDetailFragment, false);
    }
}
