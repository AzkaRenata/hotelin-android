package com.example.hotelin_android.modul.booking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Booking;

public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void saveBooking(Booking booking);
        void redirectToPreviewBooking();
    }

    interface Presenter extends BasePresenter {
        void moveToPreviewBooking();
    }
}
