package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class ProfileEditActivity extends BaseFragmentHolderActivity {
    SharedPreferencesUtil sharedPreferencesUtil;
    ProfileEditFragment profileEditFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        profileEditFragment = new ProfileEditFragment(sharedPreferencesUtil);
        setCurrentFragment(profileEditFragment, false);

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
//        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
//        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
//        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
