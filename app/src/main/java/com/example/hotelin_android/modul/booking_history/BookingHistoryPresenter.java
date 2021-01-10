package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.util.RequestCallback;

public class BookingHistoryPresenter implements BookingHistoryContract.Presenter{
    private final BookingHistoryActivity activity;
    private final BookingHistoryContract.View view;

    public BookingHistoryPresenter(BookingHistoryContract.View view, BookingHistoryActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
        getData("1");
    }

    @Override
    public void getData(String status_id){
        activity.startLoading();
        view.searchBooking(status_id, new RequestCallback<BookingHistoryResponse>() {
            @Override
            public void requestSuccess(BookingHistoryResponse data, String message) {
                view.setResult(data.booking);
                activity.stopLoading();
            }

            @Override
            public void requestFailed(String message) {
                activity.stopLoading();
                activity.showMessage(message);
            }
        });
    }
}
