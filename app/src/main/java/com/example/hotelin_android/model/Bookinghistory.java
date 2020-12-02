package com.example.hotelin_android.model;

public class Bookinghistory {
    private int id;
    private int booking_status;
    private String booking_time;
    private String hotel_name;
    private String hotel_picture;
    private String hotel_location;
    private String room_type;
    private String room_price;
    //untuk cancel booking
    private String name;
    private String email;
    private String telp;
    private String check_in;
    private String check_out;

    public Bookinghistory(int id, String hotel_name, String hotel_picture,
                          String hotel_location, String booking_time,
                          String room_type, String room_price, String name,
                          String email, String telp, String check_in, String check_out) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.hotel_picture = hotel_picture;
        this.hotel_location = hotel_location;
        this.booking_time = booking_time;
        this.room_type = room_type;
        this.room_price = room_price;
        this.name = name;
        this.email = email;
        this.telp = telp;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public Bookinghistory(int id, String hotel_name, String hotel_picture,
                          String hotel_location, String booking_time,
                          String room_type, String room_price) {
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

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public void setBooking_status(int booking_status) {
        this.booking_status = booking_status;
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

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public int getId() {
        return id;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public int getBooking_status() {
        return booking_status;
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

    public String getRoom_type() {
        return room_type;
    }

    public String getRoom_price() {
        return room_price;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelp() {
        return telp;
    }

    public String getCheck_in() {
        return check_in;
    }

    public String getCheck_out() {
        return check_out;
    }
}
