package com.example.hotelin_android.modul.room_list;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class RoomListActivity extends BaseFragmentHolderActivity {
    RoomListFragment roomListFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        roomListFragment = new RoomListFragment();
        setCurrentFragment(roomListFragment, false);

    }
}