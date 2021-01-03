package com.example.hotelin_android.modul.previewBooking;

import android.util.Log;

import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.util.RequestCallback;

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
        view.requestBooking(room_id, checkin, checkout, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                view.setResult(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }

}