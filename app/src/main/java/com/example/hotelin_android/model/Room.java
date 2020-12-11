package com.example.hotelin_android.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private String hotel_name;
    private String room_type;
    private String bed_type;
    private String room_price;
    private String guest_capacity;
    private String room_picture;
    private String facility_name;
    private String facility_icon;

    public Room(int id, String hotel_name, String room_type, String bed_type,
                String room_price, String guest_capacity, String room_picture,
                String facility_name, String facility_icon) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.room_type = room_type;
        this.bed_type = bed_type;
        this.room_price = room_price;
        this.guest_capacity = guest_capacity;
        this.room_picture = room_picture;
        this.facility_name = facility_name;
        this.facility_icon = facility_icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public void setGuest_capacity(String guest_capacity) {
        this.guest_capacity = guest_capacity;
    }

    public void setRoom_picture(String room_picture) {
        this.room_picture = room_picture;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public void setFacility_icon(String facility_icon) {
        this.facility_icon = facility_icon;
    }

    public int getId() {
        return id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getRoom_type() {
        return room_type;
    }

    public String getBed_type() {
        return bed_type;
    }

    public String getRoom_price() {
        return room_price;
    }

    public String getGuest_capacity() {
        return guest_capacity;
    }

    public String getRoom_picture() {
        return room_picture;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public String getFacility_icon() {
        return facility_icon;
    }
}
