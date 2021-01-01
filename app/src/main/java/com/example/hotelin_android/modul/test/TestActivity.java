package com.example.hotelin_android.modul.test;

import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.util.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class TestActivity extends BaseFragmentHolderActivity {
    TestFragment testFragment;
    private final int UPDATE_REQUEST = 2019;
    TokenSharedUtil tokenSharedUtil;

    @Override
    protected void initializeFragment() {
        initializeView();
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        testFragment = new TestFragment(tokenSharedUtil);
        setCurrentFragment(testFragment, false);

    }
}