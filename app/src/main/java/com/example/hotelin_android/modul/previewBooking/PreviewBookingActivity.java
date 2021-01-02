package com.example.hotelin_android.modul.previewBooking;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class PreviewBookingActivity extends BaseFragmentHolderActivity {
    PreviewBookingFragment previewBookingFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int room_id = getIntent().getIntExtra("room_id", 1);
        String email = getIntent().getStringExtra("email");
        String hotel_name = getIntent().getStringExtra("hotel_name");
        String pemesan = getIntent().getStringExtra("pemesan");
        String check_in = getIntent().getStringExtra("check_in");
        String check_out = getIntent().getStringExtra("check_out");
        String telp = "+62" + getIntent().getStringExtra("telp");
        String room_type = getIntent().getStringExtra("room_type");
        String room_price = getIntent().getStringExtra("room_price");
//        String sCheckin = getIntent().getStringExtra("sCheckin");
//        String sCheckout = getIntent().getStringExtra("sCheckout");


        Booking booking = new Booking(hotel_name, room_type, room_price, pemesan, email, telp, room_id, check_in, check_out);
        booking.setsCheck_in(getIntent().getStringExtra("sCheckin"));
        booking.setsCheck_out(getIntent().getStringExtra("sCheckout"));

        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        previewBookingFragment = new PreviewBookingFragment(tokenSharedUtil, booking);
        setCurrentFragment(previewBookingFragment, false);

    }

}