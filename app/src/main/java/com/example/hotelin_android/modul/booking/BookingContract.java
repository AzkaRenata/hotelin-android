package com.example.hotelin_android.modul.booking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void redirectToPreviewBooking();
    }

    interface Presenter extends BasePresenter {
    }
}
