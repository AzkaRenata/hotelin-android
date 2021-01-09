package com.example.hotelin_android.modul.booking_detail;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class BookingDetailActivity extends BaseFragmentHolderActivity {
    BookingDetailFragment bookingDetailFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int booking_id = getIntent().getIntExtra("booking_id",0);
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        bookingDetailFragment = new BookingDetailFragment(tokenSharedUtil, booking_id);
        setCurrentFragment(bookingDetailFragment, false);

    }
}
