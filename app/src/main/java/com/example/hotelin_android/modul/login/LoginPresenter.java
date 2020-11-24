package com.example.hotelin_android.modul.login;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.hotelin_android.util.RequestCallback;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(String email, String password){
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.redirectToHome();
                Log.e("tes", data.token);
                view.saveToken(data.token);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}