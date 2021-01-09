package com.example.hotelin_android.modul.booking;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class BookingActivity extends BaseFragmentHolderActivity {
    BookingFragment bookingFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        String check_in = getIntent().getStringExtra("check_in");
        String check_out = getIntent().getStringExtra("check_out");
        bookingFragment = new BookingFragment(check_in, check_out);
        setCurrentFragment(bookingFragment, false);

    }

}