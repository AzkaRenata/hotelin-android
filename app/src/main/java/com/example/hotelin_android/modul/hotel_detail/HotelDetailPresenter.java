package com.example.hotelin_android.modul.hotel_detail;

public class HotelDetailPresenter implements HotelDetailContract.HotelDetailPresenter {
    HotelDetailContract.HotelDetailView view;


    public HotelDetailPresenter(HotelDetailContract.HotelDetailView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setItems();
        view.setHotelDetail();
    }

    @Override
    public void moveToRoomList() {
        view.redirectToRoomList();
    }
}
