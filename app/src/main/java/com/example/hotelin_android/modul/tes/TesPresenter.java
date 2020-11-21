package com.example.hotelin_android.modul.tes;

public class TesPresenter implements TesContract.Presenter{
    private final TesContract.View view;

    public TesPresenter(TesContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(){
        view.redirectToList();
    }

}