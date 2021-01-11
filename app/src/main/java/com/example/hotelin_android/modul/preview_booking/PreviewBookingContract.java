package com.example.hotelin_android.modul.preview_booking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.util.RequestCallback;

public interface PreviewBookingContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void setBookingDetails();
        void redirectToBookingHistory();
        void requestBooking(final RequestCallback<PreviewBookingResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performBooking();
    }
}
