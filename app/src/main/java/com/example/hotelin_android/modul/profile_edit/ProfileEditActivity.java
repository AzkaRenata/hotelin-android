package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class ProfileEditActivity extends BaseFragmentHolderActivity {
    TokenSharedUtil tokenSharedUtil;
    ProfileEditFragment ProfileEditFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        ProfileEditFragment = new ProfileEditFragment(tokenSharedUtil);
        setCurrentFragment(ProfileEditFragment, false);

    }

}
