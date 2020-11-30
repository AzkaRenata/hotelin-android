package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.modul.room_list.RoomListFragment;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class BookingHistoryActivity extends BaseFragmentHolderActivity {
    BookingHistoryFragment bookingHistoryFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        bookingHistoryFragment = new BookingHistoryFragment(sharedPreferencesUtil);
        setCurrentFragment(bookingHistoryFragment, false);

    }
}
