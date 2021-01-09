package com.example.hotelin_android.modul.home;

public class HomePresenter implements HomeContract.Presenter{
    private final HomeActivity activity;
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view, HomeActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
    }

    @Override
    public void performSearch(String location){
        activity.startLoading();
        view.redirectToSearchResult(location);
        activity.stopLoading();
    }

}