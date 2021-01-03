package com.example.hotelin_android.modul.room_list;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.model.RoomTemp;
import com.example.hotelin_android.model.RoomGroup;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public interface RoomListContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void redirectToHome();
        void setResult(List<Room> data);
        void requestAvailableRoom(final RequestCallback<RoomListResponse> requestCallback);
//        void searchRoom(int hotel_id, final RequestCallback<List<Room>> requestCallback);
        void validateRoom(int hotel_id, final RequestCallback<List<RoomTemp>> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(int hotel_id);
        void getAvailableRoom();
//        void validateTime(int hotel_id);
    }
}
