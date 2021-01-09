package com.example.hotelin_android.modul.room_list;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;

public class RoomListActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        RoomListFragment roomListFragment = new RoomListFragment();
        setCurrentFragment(roomListFragment, false);

    }
}