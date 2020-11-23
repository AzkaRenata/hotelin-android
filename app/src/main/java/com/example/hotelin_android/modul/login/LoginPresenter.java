package com.example.hotelin_android.modul.login;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(){
        view.redirectToHome();
    }
}