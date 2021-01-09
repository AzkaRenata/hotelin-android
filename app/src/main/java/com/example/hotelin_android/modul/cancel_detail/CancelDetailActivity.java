package com.example.hotelin_android.modul.cancel_detail;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class CancelDetailActivity extends BaseFragmentHolderActivity {
    CancelDetailFragment cancelDetailFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int booking_id = getIntent().getIntExtra("booking_id",0);
        int booking_status = getIntent().getIntExtra("booking_status",0);
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        cancelDetailFragment = new CancelDetailFragment(tokenSharedUtil, booking_id, booking_status);
        setCurrentFragment(cancelDetailFragment, false);

    }
}
