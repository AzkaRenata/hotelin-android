package com.example.hotelin_android.modul.home;

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    public void search(String location){
        view.redirectToSearchResult(location);
    }

}