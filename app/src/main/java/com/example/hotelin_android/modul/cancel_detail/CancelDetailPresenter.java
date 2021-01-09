package com.example.hotelin_android.modul.cancel_detail;

import com.example.hotelin_android.model.Bookinghistory;
import com.example.hotelin_android.util.RequestCallback;

public class CancelDetailPresenter implements CancelDetailContract.Presenter {
    private final CancelDetailContract.View view;

    public CancelDetailPresenter(CancelDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void getData(int booking_id){
        view.searchBooking(booking_id, new RequestCallback<Bookinghistory>() {
            @Override
            public void requestSuccess(Bookinghistory data, String message) {
                view.setResult(data);
            }

            @Override
            public void requestFailed(String message) {
                view.showFailedMessage(message);
            }
        });
    }
}
