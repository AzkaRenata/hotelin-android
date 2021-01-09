package com.example.hotelin_android.model;

public class Booking {
    private int id;
    private int room_id;
    private int booking_status;
    private String check_in;
    private String check_out;
    private String booking_time;
    private int days_count;
    private double total_price;

    public Booking(int id, int room_id, int booking_status, String check_in, String check_out, String booking_time, int days_count, double total_price) {
        this.id = id;
        this.room_id = room_id;
        this.booking_status = booking_status;
        this.check_in = check_in;
        this.check_out = check_out;
        this.booking_time = booking_time;
        this.days_count = days_count;
        this.total_price = total_price;
    }

    public Booking(int room_id, String check_in, String check_out) {
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(int booking_status) {
        this.booking_status = booking_status;
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

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public int getDays_count() {
        return days_count;
    }

    public void setDays_count(int days_count) {
        this.days_count = days_count;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
