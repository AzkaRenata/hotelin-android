package com.example.hotelin_android.modul.test;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;

public interface TestContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome();
        void redirectToRegister();
        void saveToken(String token);
        void setProfile(UserTemp data);
        void requestProfile(final RequestCallback<UserTemp> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void showData();
    }
}
