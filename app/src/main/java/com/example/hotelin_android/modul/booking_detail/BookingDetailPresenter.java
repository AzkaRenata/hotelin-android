package com.example.hotelin_android.modul.booking_detail;

import com.example.hotelin_android.util.RequestCallback;

public class BookingDetailPresenter implements BookingDetailContract.Presenter {
    private final BookingDetailActivity activity;
    private final BookingDetailContract.View view;

    public BookingDetailPresenter(BookingDetailContract.View view, BookingDetailActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
        getBookingDetail();
    }

    @Override
    public void moveToCancelBooking() {
        view.redirectToCancelBooking();
    }

    @Override
    public void getBookingDetail(){
        activity.startLoading();
        view.requestBookingDetail(new RequestCallback<BookingDetailResponse>() {
            @Override
            public void requestSuccess(BookingDetailResponse response, String message) {
                view.setBookingDetail(response);
                view.setResult();
                view.checkBookingStatus();
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
