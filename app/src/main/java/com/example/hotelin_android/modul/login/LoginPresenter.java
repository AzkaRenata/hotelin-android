package com.example.hotelin_android.modul.login;

import android.view.View;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(){
        view.redirectToList();
    }

    @Override
    public void performMove(LoginContract.View v) {

    }
}