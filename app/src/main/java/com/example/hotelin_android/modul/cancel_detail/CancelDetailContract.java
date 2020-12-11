package com.example.hotelin_android.modul.cancel_detail;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Bookinghistory;
import com.example.hotelin_android.util.RequestCallback;

public interface CancelDetailContract {
    interface View extends BaseView<CancelDetailContract.Presenter> {
        void redirectToHome();
        void setResult(Bookinghistory data);
        void searchBooking(int booking_id, final RequestCallback<Bookinghistory> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(int booking_id);
    }
}
