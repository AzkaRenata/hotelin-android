package com.example.hotelin_android.modul.search_result;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class SearchResultActivity extends BaseFragmentHolderActivity {
    SearchResultFragment testFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        String hotel_location = getIntent().getStringExtra("hotel_location");
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        testFragment = new SearchResultFragment(hotel_location, sharedPreferencesUtil);
        setCurrentFragment(testFragment, false);

    }
}