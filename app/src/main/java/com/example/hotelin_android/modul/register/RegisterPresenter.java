package com.example.hotelin_android.modul.register;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performRegister(){
        view.redirectToHome();
    }

}