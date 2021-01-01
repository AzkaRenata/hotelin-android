package com.example.hotelin_android.modul.booking;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class BookingActivity extends BaseFragmentHolderActivity {
    BookingFragment bookingFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int room_id = getIntent().getIntExtra("room_id", 1);
        String hotel_name = getIntent().getStringExtra("hotel_name");
        String room_type = getIntent().getStringExtra("room_type");
        String room_price = getIntent().getStringExtra("room_price");
        String check_in = getIntent().getStringExtra("check_in");
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        bookingFragment = new BookingFragment(sharedPreferencesUtil, hotel_name, room_id, room_type, room_price, check_in);
        setCurrentFragment(bookingFragment, false);

    }

}