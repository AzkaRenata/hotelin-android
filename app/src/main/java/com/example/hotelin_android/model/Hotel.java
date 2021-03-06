package com.example.hotelin_android.model;

import java.util.List;

public class Hotel {
    private int id;
    private String hotel_name;
    private String hotel_location;
    private String hotel_desc;
    private String hotel_picture;
    private double hotel_price;
    private List<Facility> facility;

    public Hotel(int id, String hotel_name, String hotel_location, String hotel_desc, String  hotel_picture, double hotel_price, List<Facility> facility) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.hotel_location = hotel_location;
        this.hotel_desc = hotel_desc;
        this.hotel_picture = hotel_picture;
        this.hotel_price = hotel_price;
        this.facility = facility;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public void setHotel_desc(String hotel_desc) {
        this.hotel_desc = hotel_desc;
    }

    public void setHotel_picture(String hotel_picture) {
        this.hotel_picture = hotel_picture;
    }

    public void setHotel_price(double hotel_price) {
        this.hotel_price = hotel_price;
    }

    public int getId() {
        return id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public String getHotel_desc() {
        return hotel_desc;
    }

    public String getHotel_picture() {
        return hotel_picture;
    }

    public double getHotel_price() {
        return hotel_price;
    }

    public List<Facility> getFacility() {
        return facility;
    }

    public void setFacility(List<Facility> facility) {
        this.facility = facility;
    }
}
