package com.example.hotelin_android.modul.login;

import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginActivity activity;
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view, LoginActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
    }

    @Override
    public void performLogin(String email, String password){
        activity.startLoading();
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.saveToken(data.token);
                view.saveUser(data.user);
                activity.stopLoading();
                view.redirectToHome();
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showFailedMessage(errorMessage);
            }
        });
    }
}