package com.example.hotelin_android.modul.profile;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class ProfileActivity extends BaseFragmentHolderActivity {
    TokenSharedUtil tokenSharedUtil;
    ProfileFragment profileFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        profileFragment = new ProfileFragment(tokenSharedUtil);
        setCurrentFragment(profileFragment, false);

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
