package com.example.hotelin_android.model;

import java.util.List;

public class Room {
    private int id;
    private String room_code;
    private String room_type;
    private String bed_type;
    private int bed_count;
    private Double room_price;
    private int guest_capacity;
    private String room_picture;
    private boolean is_booked;
    private List<Facility> facility;

    public Room(int id, String room_code, String room_type, String bed_type, int bed_count, Double room_price, int guest_capacity, String room_picture, boolean is_booked, List<Facility> facility) {
        this.id = id;
        this.room_code = room_code;
        this.room_type = room_type;
        this.bed_type = bed_type;
        this.bed_count = bed_count;
        this.room_price = room_price;
        this.guest_capacity = guest_capacity;
        this.room_picture = room_picture;
        this.is_booked = is_booked;
        this.facility = facility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getBed_type() {
        return bed_type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public int getBed_count() {
        return bed_count;
    }

    public void setBed_count(int bed_count) {
        this.bed_count = bed_count;
    }

    public Double getRoom_price() {
        return room_price;
    }

    public void setRoom_price(Double room_price) {
        this.room_price = room_price;
    }

    public int getGuest_capacity() {
        return guest_capacity;
    }

    public void setGuest_capacity(int guest_capacity) {
        this.guest_capacity = guest_capacity;
    }

    public String getRoom_picture() {
        return room_picture;
    }

    public void setRoom_picture(String room_picture) {
        this.room_picture = room_picture;
    }

    public boolean isIs_booked() {
        return is_booked;
    }

    public void setIs_booked(boolean is_booked) {
        this.is_booked = is_booked;
    }

    public List<Facility> getFacility() {
        return facility;
    }

    public void setFacility(List<Facility> facility) {
        this.facility = facility;
    }
}
