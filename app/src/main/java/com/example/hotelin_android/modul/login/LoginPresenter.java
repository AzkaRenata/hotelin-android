package com.example.hotelin_android.modul.login;

import android.util.Log;

import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.TokenSharedUtil;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final TokenSharedUtil sessionRepository;

    public LoginPresenter(LoginContract.View view, TokenSharedUtil sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void start() {
        /*
        if(sessionRepository.getToken() != null){
            view.redirectToHome();
        }
         */
    }

    @Override
    public void performLogin(String email, String password){
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                Log.e("1", "tes");
                view.redirectToHome();
                Log.e("2", data.token);
                view.saveToken(data.token);
                Log.e("3", "tes");
                view.showSuccesMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}