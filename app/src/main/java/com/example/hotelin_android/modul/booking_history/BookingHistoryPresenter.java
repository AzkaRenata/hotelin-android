package com.example.hotelin_android.modul.booking_history;

import com.example.hotelin_android.model.BookingHistory;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.model.RoomGroup;
import com.example.hotelin_android.modul.room_list.RoomListContract;
import com.example.hotelin_android.util.RequestCallback;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryPresenter implements BookingHistoryContract.Presenter{
    private final BookingHistoryContract.View view;

    public BookingHistoryPresenter(BookingHistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void getData(String status_id){
        view.searchBooking(status_id, new RequestCallback<List<BookingHistory>>() {
            @Override
            public void requestSuccess(List<BookingHistory> data) {
                view.setResult(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}
