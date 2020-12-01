package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Bookinghistory;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public interface BookingHistoryContract {
    interface View extends BaseView<BookingHistoryContract.Presenter> {
        void redirectToHome();
        void redirectToCancelDetail(int id, int booking_status);
        void setResult(List<Bookinghistory> data);
        void searchBooking(String status_id, final RequestCallback<List<Bookinghistory>> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String status_id);
    }
}
