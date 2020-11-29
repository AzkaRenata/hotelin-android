package com.example.hotelin_android.modul.room_list;

import android.util.Log;

import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.model.RoomGroup;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.room_list.RoomListContract;
import com.example.hotelin_android.util.RequestCallback;

import java.util.ArrayList;
import java.util.List;

public class RoomListPresenter implements RoomListContract.Presenter{
    private final RoomListContract.View view;

    public RoomListPresenter(RoomListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void getData(int hotel_id){
        view.searchRoom(hotel_id, new RequestCallback<List<Room>>() {
            @Override
            public void requestSuccess(List<Room> data) {
                ArrayList<RoomGroup> result = new ArrayList<>();
                int i=0;
                for(Room room : data){
                    if(i == 0){
                        result.add(new RoomGroup());
                        result.get(0).setRoom(room);
                        i = 1;
                    } else {
                        if (room.getId() != result.get(result.size() - 1).getRooms().get(0).getId()) {
                            result.add(new RoomGroup());
                            result.get(result.size() - 1).setRoom(room);
                        } else {
                            result.get(result.size() - 1).setRoom(room);
                        }
                    }
                }
                view.setResult(result);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}