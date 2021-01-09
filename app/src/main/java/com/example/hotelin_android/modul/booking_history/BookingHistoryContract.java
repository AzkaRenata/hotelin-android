package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.BookingHistory;
import com.example.hotelin_android.model.BookinghHstorytemp;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public interface BookingHistoryContract {
    interface View extends BaseView<BookingHistoryContract.Presenter> {
        void setItems();
        void redirectToCancelDetail(int id);
        void setResult(List<BookingHistory> data);
        void searchBooking(String status_id, final RequestCallback<BookingHistoryResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void getData(String status_id);
    }
}
