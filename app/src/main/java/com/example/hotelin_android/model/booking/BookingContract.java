package com.example.hotelin_android.model.booking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.booking.BookingResponse;
import com.example.hotelin_android.util.RequestCallback;

public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void redirectToPreviewBooking();
//        void requestRegister(User newUser, final RequestCallback<BookingResponse> requestCallback);
        void requestBooking(Booking newBooking, final RequestCallback<BookingResponse> requestCallback);
        void showSuccessMessage();
        void showErrorMessage(String message);
    }

    interface Presenter extends BasePresenter {
//        void performRegister(User newBooking);
        void performBooking(Booking newBooking);
    }
}
