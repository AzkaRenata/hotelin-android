package com.example.hotelin_android.modul.room_list;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public interface RoomListContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void initCalendar();
        void redirectToBooking();
        void checkResult();
        void setResult(List<Room> data);
        void saveRoom(Room room);
        void requestAvailableRoom(final RequestCallback<RoomListResponse> requestCallback);
        void requestRoomDetail(final int id, final RequestCallback<RoomListResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performRoomSearch();
        void getRoomDetail(int id);
    }
}
