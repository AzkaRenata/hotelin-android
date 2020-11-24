package com.example.hotelin_android.modul.test;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.UtilProvider;

public class TestActivity extends BaseFragmentHolderActivity {
    TestFragment testFragment;
    private final int UPDATE_REQUEST = 2019;
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        sharedPreferencesUtil = UtilProvider.getSharedPreferencesUtil();
        testFragment = new TestFragment(sharedPreferencesUtil);
        setCurrentFragment(testFragment, false);

    }
}