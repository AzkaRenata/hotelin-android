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
    private int booking_status;
    private String check_in;
    private String check_out;
    private String user_id;
    private boolean is_booked;

    public boolean isIs_booked() {
        return is_booked;
    }

    public void setIs_booked(boolean is_booked) {
        this.is_booked = is_booked;
    }

    public Room(int id, String hotel_name, String room_type, String bed_type, String room_price, String guest_capacity, String room_picture, String facility_name, String facility_icon, int booking_status, String check_in, String check_out, String user_id, boolean is_booked) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.room_type = room_type;
        this.bed_type = bed_type;
        this.room_price = room_price;
        this.guest_capacity = guest_capacity;
        this.room_picture = room_picture;
        this.facility_name = facility_name;
        this.facility_icon = facility_icon;
        this.booking_status = booking_status;
        this.check_in = check_in;
        this.check_out = check_out;
        this.user_id = user_id;
        this.is_booked = is_booked;
    }

    public Room(int id, String hotel_name, String room_type, String bed_type,
                String room_price, String guest_capacity, String room_picture,
                String facility_name, String facility_icon, int booking_status,
                String check_in, String check_out, String user_id) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.room_type = room_type;
        this.bed_type = bed_type;
        this.room_price = room_price;
        this.guest_capacity = guest_capacity;
        this.room_picture = room_picture;
        this.facility_name = facility_name;
        this.facility_icon = facility_icon;
        this.booking_status = booking_status;
        this.check_in = check_in;
        this.check_out = check_out;
        this.user_id = user_id;
    }

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

    public int getBooking_status() {
        return booking_status;
    }

    public String getCheck_in() {
        return check_in;
    }

    public String getCheck_out() {
        return check_out;
    }
}
