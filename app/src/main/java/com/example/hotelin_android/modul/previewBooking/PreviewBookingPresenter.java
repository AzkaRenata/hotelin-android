package com.example.hotelin_android.modul.previewBooking;

import android.util.Log;

import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.model.RoomGroup;
import com.example.hotelin_android.util.RequestCallback;

import java.util.ArrayList;
import java.util.List;

public class PreviewBookingPresenter implements PreviewBookingContract.Presenter{
    private final PreviewBookingContract.View view;

    public PreviewBookingPresenter(PreviewBookingContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performBooking(int room_id, String checkin, String checkout){
        Log.e("tes", "tes4");
        view.requestBooking(room_id, checkin, checkout, new RequestCallback<PreviewBookingResponse>() {
            @Override
            public void requestSuccess(PreviewBookingResponse data) {
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

}