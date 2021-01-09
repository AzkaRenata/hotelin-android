package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class BookingHistoryActivity extends BaseFragmentHolderActivity {
    BookingHistoryFragment bookingHistoryFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        bookingHistoryFragment = new BookingHistoryFragment();
        setCurrentFragment(bookingHistoryFragment, false);

    }
}
