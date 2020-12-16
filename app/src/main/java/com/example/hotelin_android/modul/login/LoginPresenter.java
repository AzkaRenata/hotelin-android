package com.example.hotelin_android.modul.login;

import android.util.Log;

import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SharedPreferencesUtil sessionRepository;                                              //new

    public LoginPresenter(LoginContract.View view, SharedPreferencesUtil sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;                                                 //new
    }

    @Override
    public void start() {
        if(sessionRepository.getToken() != null){                                             //new
            view.redirectToHome();                                                               //jika sudah login langsung masuk profile
        }
    }

    @Override
    public void performLogin(String email, String password){
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.redirectToHome();
                Log.e("tes", data.token);
                view.saveToken(data.token);
                view.showSuccesMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}