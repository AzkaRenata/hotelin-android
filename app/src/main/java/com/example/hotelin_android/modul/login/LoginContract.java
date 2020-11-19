package com.example.hotelin_android.modul.login;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
    }

    interface Presenter extends BasePresenter {
        void performLogin();
    }
}