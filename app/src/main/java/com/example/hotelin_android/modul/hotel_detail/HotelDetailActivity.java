package com.example.hotelin_android.modul.hotel_detail;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class HotelDetailActivity extends BaseFragmentHolderActivity {
    TokenSharedUtil tokenSharedUtil;
    HotelDetailFragment hotelDetailFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        hotelDetailFragment = new HotelDetailFragment(tokenSharedUtil);
        setCurrentFragment(hotelDetailFragment, false);

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
//        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
//        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
//        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
