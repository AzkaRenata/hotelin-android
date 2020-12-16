package com.example.hotelin_android.modul.login;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.util.RequestCallback;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome();
        void redirectToRegister();
        void saveToken(String token);
        void requestLogin(String email, String password, final RequestCallback<LoginResponse> requestCallback);
        void showFailedMessage(String message);
        void showSuccesMessage();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
