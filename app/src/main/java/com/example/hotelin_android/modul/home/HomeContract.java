package com.example.hotelin_android.modul.home;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
        void redirectToRegister();
    }

    interface Presenter extends BasePresenter {
        void performLogin();
        void performMove(View v);
    }
}
