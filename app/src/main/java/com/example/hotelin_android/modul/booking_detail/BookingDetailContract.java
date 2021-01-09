package com.example.hotelin_android.modul.booking_detail;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.BookinghHstorytemp;
import com.example.hotelin_android.util.RequestCallback;

public interface BookingDetailContract {
    interface View extends BaseView<BookingDetailContract.Presenter> {
        void redirectToHome();
        void setResult(BookinghHstorytemp data);
        void searchBooking(int booking_id, final RequestCallback<BookinghHstorytemp> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(int booking_id);
    }
}
