package com.example.hotelin_android.model;

import java.util.ArrayList;
import java.util.List;

public class RoomGroup {
    private List<RoomTemp> roomTemps;

    public RoomGroup() {
        roomTemps = new ArrayList<>();
    }

    public void setRoomTemps(List<RoomTemp> roomTemps) {
        this.roomTemps = roomTemps;
    }

    public void setRoom(RoomTemp roomTemp) {
        this.roomTemps.add(roomTemp);
    }

    public List<RoomTemp> getRoomTemps() {
        return roomTemps;
    }
}
