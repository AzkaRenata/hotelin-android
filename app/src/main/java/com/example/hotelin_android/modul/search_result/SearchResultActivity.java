package com.example.hotelin_android.modul.search_result;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class SearchResultActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        String hotel_location = getIntent().getStringExtra("hotel_location");
        SearchResultFragment fragment = new SearchResultFragment(hotel_location);
        setCurrentFragment(fragment, false);

    }
}