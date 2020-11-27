package com.example.hotelin_android.modul.hotel_detail;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseActivity;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.modul.login.LoginFragment;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class HotelDetailActivity extends BaseFragmentHolderActivity {
    SharedPreferencesUtil sharedPreferencesUtil;
    HotelDetailFragment hotelDetailFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        hotelDetailFragment = new HotelDetailFragment(sharedPreferencesUtil);
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
