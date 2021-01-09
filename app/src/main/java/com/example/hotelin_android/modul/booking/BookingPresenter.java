package com.example.hotelin_android.modul.booking;

import com.example.hotelin_android.modul.login.LoginActivity;
import com.example.hotelin_android.modul.login.LoginContract;
import com.example.hotelin_android.util.RequestCallback;

public class BookingPresenter implements BookingContract.Presenter{
    private final BookingActivity activity;
    private final BookingContract.View view;

    public BookingPresenter(BookingContract.View view, BookingActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
    }

    @Override
    public void moveToPreviewBooking() {
        view.redirectToPreviewBooking();
    }
}