package com.example.hotelin_android.modul.register;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
        void setItems();
        void requestRegister(User user, final RequestCallback<RegisterResponse> requestCallback);
        void showSuccessMessage();
        void showErrorMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void performRegister(User user);
    }
}
