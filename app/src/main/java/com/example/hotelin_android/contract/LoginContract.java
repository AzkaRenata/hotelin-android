package com.example.hotelin_android.contract;

import com.example.hotelin_android.api_response.LoginResponse;
import com.example.hotelin_android.callback.RequestCallback;

public interface LoginContract {
    interface View {
        void startLoading();
        void endLoading();
        void loginSuccess();
        void loginFailed(String message);
    }

    interface Presenter {
        void login(String username, String password);
    }

    interface Interactor {
        void requestLogin(String username, String password, RequestCallback<LoginResponse> requestCallback);
        void saveToken(String token);
    }
}
