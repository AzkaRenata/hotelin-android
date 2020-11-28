package com.example.hotelin_android.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {
    private int id;
    private int user_id;
    private int room_id;
    private int booking_status;
    private String check_in;
    private String check_out;
    private Date booking_time;

    public Booking(int id, int user_id, int room_id, int booking_status, String check_in, String check_out, Date booking_time) {
        this.id = id;
        this.user_id = user_id;
        this.room_id = room_id;
        this.booking_status = booking_status;
        this.check_in = check_in;
        this.check_out = check_out;
        this.booking_time = booking_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public Date getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(Date booking_time) {
        this.booking_time = booking_time;
    }
}
