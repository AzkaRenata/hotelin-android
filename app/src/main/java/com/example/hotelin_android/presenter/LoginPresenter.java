package com.example.hotelin_android.presenter;

import com.example.hotelin_android.api_response.LoginResponse;
import com.example.hotelin_android.callback.RequestCallback;
import com.example.hotelin_android.contract.LoginContract;
import com.example.hotelin_android.databinding.LoginActivityBinding;
import com.example.hotelin_android.view.BookingActivity;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    LoginActivityBinding loginActivityBinding;

    public LoginPresenter(LoginContract.View view, LoginContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String username, String password) {
//        view.startLoading();
        interactor.requestLogin(username, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.endLoading();
                view.loginSuccess();
                interactor.saveToken(data.token);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.loginFailed(errorMessage);
            }
        });
    }
}
