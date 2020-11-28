package com.example.hotelin_android.model;

import java.util.ArrayList;
import java.util.List;

public class RoomGroup {
    private List<Room> rooms;

    public RoomGroup() {
        rooms = new ArrayList<>();
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setRoom(Room room) {
        this.rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
