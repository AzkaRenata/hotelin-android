package com.example.hotelin_android.model;

public class BookingHistory {
    private int id;
    private String hotel_name;
    private String hotel_location;
    private String hotel_picture;
    private String room_code;
    private String room_type;
    private String check_in;
    private String check_out;
    private double total_price;

    public BookingHistory(int id, String hotel_name, String hotel_location, String hotel_picture, String room_code, String room_type, String check_in, String check_out, double total_price) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.hotel_location = hotel_location;
        this.hotel_picture = hotel_picture;
        this.room_code = room_code;
        this.room_type = room_type;
        this.check_in = check_in;
        this.check_out = check_out;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public String getHotel_picture() {
        return hotel_picture;
    }

    public void setHotel_picture(String hotel_picture) {
        this.hotel_picture = hotel_picture;
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

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
