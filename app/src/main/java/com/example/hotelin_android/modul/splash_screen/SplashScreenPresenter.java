package com.example.hotelin_android.modul.splash_screen;

public class SplashScreenPresenter implements SplashScreenContract.Presenter{
    private final SplashScreenContract.View view;

    public SplashScreenPresenter(SplashScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setItems();
    }
}