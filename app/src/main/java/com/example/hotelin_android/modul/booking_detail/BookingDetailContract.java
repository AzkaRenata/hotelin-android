package com.example.hotelin_android.modul.booking_detail;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.util.RequestCallback;

public interface BookingDetailContract {
    interface View extends BaseView<BookingDetailContract.Presenter> {
        void setItems();
        void redirectToCancelBooking();
        void checkBookingStatus();
        void setResult();
        void setBookingDetail(BookingDetailResponse response);
        void requestBookingDetail(final RequestCallback<BookingDetailResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void getBookingDetail();
        void moveToCancelBooking();
    }
}
