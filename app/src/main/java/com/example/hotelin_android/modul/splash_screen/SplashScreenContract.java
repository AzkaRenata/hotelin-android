package com.example.hotelin_android.modul.splash_screen;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public interface SplashScreenContract {
    interface View extends BaseView<Presenter> {
        void setItems();
    }

    interface Presenter extends BasePresenter {
    }
}
