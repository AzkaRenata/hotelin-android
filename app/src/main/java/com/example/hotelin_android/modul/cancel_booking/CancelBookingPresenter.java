package com.example.hotelin_android.modul.cancel_booking;

import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.util.RequestCallback;

public class CancelBookingPresenter implements CancelBookingContract.Presenter {
    private final CancelBookingContract.View view;

    public CancelBookingPresenter(CancelBookingContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performCancelBooking(int booking_id){
        view.cancelBooking(booking_id, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data, String message) {
                view.setResult(data);
            }

            @Override
            public void requestFailed(String message) {
                view.showFailedMessage(message);
            }
        });
    }
}
