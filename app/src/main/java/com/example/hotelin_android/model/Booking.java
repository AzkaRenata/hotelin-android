package com.example.hotelin_android.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {
    private String hotel_name;
    private String namaPemesan;
    private String email;
    private String telp;
    private int id;
    private int user_id;
    private int room_id;
    private int jumlahMalam;
    private String room_type;
    private String room_price;
    private String check_in;
    private String check_out;
    private String sCheck_in;
    private String sCheck_out;

    public Booking(String hotel_name, String room_type, String room_price, String namaPemesan, String email, String telp, int room_id, String check_in, String check_out) {
        this.hotel_name = hotel_name;
        this.namaPemesan = namaPemesan;
        this.email = email;
        this.telp = telp;
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.room_type = room_type;
        this.room_price = room_price;
    }

    public String getsCheck_in() {
        return sCheck_in;
    }

    public void setsCheck_in(String sCheck_in) {
        this.sCheck_in = sCheck_in;
    }

    public String getsCheck_out() {
        return sCheck_out;
    }

    public void setsCheck_out(String sCheck_out) {
        this.sCheck_out = sCheck_out;
    }

    public String getRoom_type() {
        return room_type;
    }

    public String getRoom_price() {
        return room_price;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public String getEmail() {
        return email;
    }

    public String getTelp() {
        return telp;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getCheck_in() {
        return check_in;
    }

    public String getCheck_out() {
        return check_out;
    }
}
