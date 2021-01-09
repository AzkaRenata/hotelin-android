package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.model.Bookinghistory;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public class BookingHistoryPresenter implements BookingHistoryContract.Presenter{
    private final BookingHistoryContract.View view;

    public BookingHistoryPresenter(BookingHistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void getData(String status_id){
        view.searchBooking(status_id, new RequestCallback<List<Bookinghistory>>() {
            @Override
            public void requestSuccess(List<Bookinghistory> data, String message) {
                view.setResult(data);
            }

            @Override
            public void requestFailed(String message) {
                view.showFailedMessage(message);
            }
        });
    }
}
