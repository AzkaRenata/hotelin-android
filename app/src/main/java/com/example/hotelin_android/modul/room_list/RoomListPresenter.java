package com.example.hotelin_android.modul.room_list;

import android.util.Log;

import com.example.hotelin_android.model.RoomTemp;
import com.example.hotelin_android.model.RoomGroup;
import com.example.hotelin_android.util.RequestCallback;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public void getAvailableRoom() {
        activity.startLoading();
        view.requestAvailableRoom(new RequestCallback<RoomListResponse>() {
            @Override
            public void requestSuccess(RoomListResponse data) {
                Log.e("tes", "tessssssasdadad");
                view.setResult(data.roomList);
                activity.stopLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                Log.e("tes", "tesss ooooyyyy");
                activity.stopLoading();
            }
        });
    }

    @Override
    public void getData(int hotel_id) {
        view.validateRoom(hotel_id, new RequestCallback<List<RoomTemp>>() {
            @Override
            public void requestSuccess(List<RoomTemp> data) {
                ArrayList<RoomGroup> result = new ArrayList<>();
                int i = 0;
                for (RoomTemp roomTemp : data) {
                    if (i == 0) {
                        result.add(new RoomGroup());
                        result.get(0).setRoom(roomTemp);
                        i = 1;
                    } else {
                        if (roomTemp.getId() != result.get(result.size() - 1).getRoomTemps().get(0).getId()) {
                            result.add(new RoomGroup());
                            result.get(result.size() - 1).setRoom(roomTemp);
                        } else {
                            result.get(result.size() - 1).setRoom(roomTemp);
                        }
                    }
                }
                //view.setResult(result);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }

}