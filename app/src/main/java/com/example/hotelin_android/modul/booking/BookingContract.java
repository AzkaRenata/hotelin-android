package com.example.hotelin_android.modul.booking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void redirectToPreviewBooking();
    }

    interface Presenter extends BasePresenter {
    }
}
