package com.example.hotelin_android.modul.change_password;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class ChangePasswordActivity extends BaseFragmentHolderActivity {
    ChangePasswordFragment changePasswordFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        changePasswordFragment = new ChangePasswordFragment(tokenSharedUtil);
        setCurrentFragment(changePasswordFragment, false);

    }

}