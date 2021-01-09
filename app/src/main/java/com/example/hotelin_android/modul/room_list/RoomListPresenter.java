package com.example.hotelin_android.modul.room_list;

import com.example.hotelin_android.util.RequestCallback;

public class RoomListPresenter implements RoomListContract.Presenter {
    private final RoomListActivity activity;
    private final RoomListContract.View view;

    public RoomListPresenter(RoomListContract.View view, RoomListActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
        view.initCalendar();
    }

    @Override
    public void performRoomSearch() {
        activity.startLoading();
        view.requestAvailableRoom(new RequestCallback<RoomListResponse>() {
            @Override
            public void requestSuccess(RoomListResponse data, String message) {
                view.setResult(data.roomList);
                view.checkResult();
                activity.stopLoading();
            }

            @Override
            public void requestFailed(String message) {
                activity.stopLoading();
                activity.showMessage(message);
            }
        });
    }

    @Override
    public void getRoomDetail(int id) {
        activity.startLoading();
        view.requestRoomDetail(id, new RequestCallback<RoomListResponse>() {
            @Override
            public void requestSuccess(RoomListResponse data, String message) {
                view.saveRoom(data.room);
                activity.stopLoading();
                view.redirectToBooking();
            }

            @Override
            public void requestFailed(String message) {
                activity.stopLoading();
                activity.showMessage(message);
            }
        });
    }
}