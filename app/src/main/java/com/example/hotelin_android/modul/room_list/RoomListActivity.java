package com.example.hotelin_android.modul.room_list;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class RoomListActivity extends BaseFragmentHolderActivity {
    RoomListFragment roomListFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int hotel_id = getIntent().getIntExtra("hotel_id", 0);
        String hotel_name = getIntent().getStringExtra("hotel_name");
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        roomListFragment = new RoomListFragment(hotel_id, hotel_name, tokenSharedUtil);
        setCurrentFragment(roomListFragment, false);

    }
}