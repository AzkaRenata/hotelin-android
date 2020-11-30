package com.example.hotelin_android.modul.room_list;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class RoomListActivity extends BaseFragmentHolderActivity {
    RoomListFragment roomListFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        int hotel_id = getIntent().getIntExtra("hotel_id", 0);
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        roomListFragment = new RoomListFragment(hotel_id, sharedPreferencesUtil);
        setCurrentFragment(roomListFragment, false);

    }
}