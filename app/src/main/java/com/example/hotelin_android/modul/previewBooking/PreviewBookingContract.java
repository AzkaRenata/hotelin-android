package com.example.hotelin_android.modul.previewBooking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.util.RequestCallback;

public interface PreviewBookingContract {
    interface View extends BaseView<Presenter> {
        void redirectToBookingHistory();
        void requestBooking(int room_id, String checkin, String checkout, final RequestCallback<PreviewBookingResponse> requestCallback);
        void showSuccessMessage();
        void showErrorMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void performBooking(int room_id, String checkin, String checkout);
    }
}
