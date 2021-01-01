package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class BookingHistoryActivity extends BaseFragmentHolderActivity {
    BookingHistoryFragment bookingHistoryFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        bookingHistoryFragment = new BookingHistoryFragment(tokenSharedUtil);
        setCurrentFragment(bookingHistoryFragment, false);

    }
}
