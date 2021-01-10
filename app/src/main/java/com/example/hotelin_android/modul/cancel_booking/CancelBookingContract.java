package com.example.hotelin_android.modul.cancel_booking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.util.RequestCallback;

public interface CancelBookingContract {
    interface View extends BaseView<CancelBookingContract.Presenter> {
        void setItems();
        void redirectToBookingDetail();
        void cancelBooking(final RequestCallback<CancelBookingResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performCancelBooking();
    }
}
