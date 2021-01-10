package com.example.hotelin_android.modul.cancel_booking;

import com.example.hotelin_android.util.RequestCallback;

public class CancelBookingPresenter implements CancelBookingContract.Presenter {
    private final CancelBookingActivity activity;
    private final CancelBookingContract.View view;

    public CancelBookingPresenter(CancelBookingContract.View view, CancelBookingActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
    }

    @Override
    public void performCancelBooking(){
        activity.startLoading();
        view.cancelBooking(new RequestCallback<CancelBookingResponse>() {
            @Override
            public void requestSuccess(CancelBookingResponse response, String message) {
                activity.stopLoading();
                activity.showMessage(message);
                view.redirectToBookingDetail();
            }

            @Override
            public void requestFailed(String message) {
                activity.stopLoading();
                activity.showMessage(message);
            }
        });
    }
}
