package com.example.hotelin_android.modul.preview_booking;

import com.example.hotelin_android.util.RequestCallback;

public class PreviewBookingPresenter implements PreviewBookingContract.Presenter{
    private final PreviewBookingActivity activity;
    private final PreviewBookingContract.View view;

    public PreviewBookingPresenter(PreviewBookingContract.View view, PreviewBookingActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
        view.setBookingDetails();
    }

    @Override
    public void performBooking(){
        activity.startLoading();
        view.requestBooking(new RequestCallback<PreviewBookingResponse>() {
            @Override
            public void requestSuccess(PreviewBookingResponse data, String message) {
                view.clearSharedPreferences();
                activity.stopLoading();
                activity.showMessage(message);
                view.redirectToBookingHistory();
            }

            @Override
            public void requestFailed(String message) {
                activity.stopLoading();
                activity.showMessage(message);
            }
        });
    }
}