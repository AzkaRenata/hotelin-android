package com.example.hotelin_android.modul.about_us;

public class AboutUsPresenter implements AboutUsContract.HotelDetailPresenter {
    AboutUsContract.HotelDetailView view;


    public AboutUsPresenter(AboutUsContract.HotelDetailView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setItems();
        view.setPictures();
    }
}
