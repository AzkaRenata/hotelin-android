package com.example.hotelin_android.modul.home;

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(){
        view.redirectToList();
    }

<<<<<<< Updated upstream
    @Override
    public void performMove(HomeContract.View v) {

    }
=======
>>>>>>> Stashed changes
}