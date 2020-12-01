package com.example.hotelin_android.modul.cancel_detail;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.modul.search_result.SearchResultFragment;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class CancelDetailActivity extends BaseFragmentHolderActivity {
    CancelDetailFragment cancelDetailFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int booking_id = getIntent().getIntExtra("booking_id",0);
        int booking_status = getIntent().getIntExtra("booking_status",0);
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        cancelDetailFragment = new CancelDetailFragment(sharedPreferencesUtil, booking_id, booking_status);
        setCurrentFragment(cancelDetailFragment, false);

    }
}
