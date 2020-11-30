package com.example.hotelin_android.model;

public class BookingHistory {
    private int id;
    private String hotel_name;
    private String hotel_picture;
    private String hotel_location;
    private String booking_time;
    private String room_type;
    private String room_price;

    public BookingHistory(int id, String hotel_name, String hotel_picture, String hotel_location, String booking_time, String room_type, String room_price) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.hotel_picture = hotel_picture;
        this.hotel_location = hotel_location;
        this.booking_time = booking_time;
        this.room_type = room_type;
        this.room_price = room_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setHotel_picture(String hotel_picture) {
        this.hotel_picture = hotel_picture;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public int getId() {
        return id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getHotel_picture() {
        return hotel_picture;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public String getRoom_type() {
        return room_type;
    }

    public String getRoom_price() {
        return room_price;
    }
}
