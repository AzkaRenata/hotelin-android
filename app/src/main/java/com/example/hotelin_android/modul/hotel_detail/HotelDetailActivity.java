package com.example.hotelin_android.modul.hotel_detail;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class HotelDetailActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
        setCurrentFragment(hotelDetailFragment, false);

    }
}
