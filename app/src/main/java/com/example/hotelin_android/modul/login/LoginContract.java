package com.example.hotelin_android.modul.login;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void redirectToHome();
        void saveToken(String token);
        void saveUser(User user);
        void requestLogin(String email, String password, final RequestCallback<LoginResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
