package com.example.hotelin_android.modul.booking_detail;

import com.example.hotelin_android.model.BookinghHstorytemp;
import com.example.hotelin_android.util.RequestCallback;

public class BookingDetailPresenter implements BookingDetailContract.Presenter {
    private final BookingDetailContract.View view;

    public BookingDetailPresenter(BookingDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void getData(int booking_id){
        view.searchBooking(booking_id, new RequestCallback<BookinghHstorytemp>() {
            @Override
            public void requestSuccess(BookinghHstorytemp data, String message) {
                view.setResult(data);
            }

            @Override
            public void requestFailed(String message) {
                view.showFailedMessage(message);
            }
        });
    }
}
