package com.example.hotelin_android.modul.change_password;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.modul.booking.BookingFragment;
import com.example.hotelin_android.modul.login.LoginFragment;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class ChangePasswordActivity extends BaseFragmentHolderActivity {
    ChangePasswordFragment changePasswordFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();

        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        changePasswordFragment = new ChangePasswordFragment(sharedPreferencesUtil);
        setCurrentFragment(changePasswordFragment, false);

    }

}