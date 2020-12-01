package com.example.hotelin_android.modul.cancel_booking;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.util.RequestCallback;

public interface CancelBookingContract {
    interface View extends BaseView<CancelBookingContract.Presenter> {
        void redirectToCancelDetail(boolean isSuccess);
        void setResult(SuccessMessage data);
        void cancelBooking(int booking_id, final RequestCallback<SuccessMessage> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void performCancelBooking(int booking_id);
    }
}
