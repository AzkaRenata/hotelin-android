package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class ProfileEditActivity extends BaseFragmentHolderActivity {
    SharedPreferencesUtil sharedPreferencesUtil;
    ProfileEditFragment ProfileEditFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        ProfileEditFragment = new ProfileEditFragment(sharedPreferencesUtil);
        setCurrentFragment(ProfileEditFragment, false);

    }

}
