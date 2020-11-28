package com.example.hotelin_android.model.booking;

import android.util.Log;

import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.booking.BookingContract;
import com.example.hotelin_android.modul.booking.BookingResponse;
import com.example.hotelin_android.util.RequestCallback;

public class BookingPresenter implements com.example.hotelin_android.modul.booking.BookingContract.Presenter{
    private final com.example.hotelin_android.modul.booking.BookingContract.View view;

    public BookingPresenter(BookingContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performBooking(Booking newBoking){
        Log.e("tes", "tes4");
        view.requestBooking(newBoking, new RequestCallback<com.example.hotelin_android.modul.booking.BookingResponse>() {
            @Override
            public void requestSuccess(BookingResponse data) {
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

}