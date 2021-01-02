package com.example.hotelin_android.modul.search_result;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class SearchResultActivity extends BaseFragmentHolderActivity {
    SearchResultFragment testFragment;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        String hotel_location = getIntent().getStringExtra("hotel_location");
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        testFragment = new SearchResultFragment(hotel_location, tokenSharedUtil);
        setCurrentFragment(testFragment, false);

    }
}