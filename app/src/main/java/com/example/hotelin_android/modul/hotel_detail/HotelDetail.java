package com.example.hotelin_android.modul.hotel_detail;

public class HotelDetail {
    private int id;
    private String hotel_name;
    private String hotel_location;
    private String hotel_desc;
    private String hotel_picture;
    private int user_id;

    public HotelDetail(int id, String hotel_name, String hotel_location, String hotel_desc, String hotel_picture, int user_id) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.hotel_location = hotel_location;
        this.hotel_desc = hotel_desc;
        this.hotel_picture = hotel_picture;
        this.user_id = user_id;
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

    public String getHotel_desc() {
        return hotel_desc;
    }

    public void setHotel_desc(String hotel_desc) {
        this.hotel_desc = hotel_desc;
    }

    public String getHotel_picture() {
        return hotel_picture;
    }

    public void setHotel_picture(String hotel_picture) {
        this.hotel_picture = hotel_picture;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}