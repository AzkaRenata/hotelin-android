package com.example.hotelin_android.modul.update_password;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class UpdatePasswordActivity extends BaseFragmentHolderActivity {
    SharedPreferencesUtil sharedPreferencesUtil;
    UpdatePasswordFragment updatePasswordFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        updatePasswordFragment = new UpdatePasswordFragment(sharedPreferencesUtil);
        setCurrentFragment(updatePasswordFragment, false);

    }

}
