package com.example.hotelin_android.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {
    private String namaPemesan;
    private String email;
    private String telp;
    private int jumlahKamar;
    private int id;
    private int user_id;
    private int room_id;
    private String check_in;
    private String check_out;

    public Booking(String pemesan, String email, String jumlahKamar, int room_id, String check_in, String check_out) {
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public Booking(int room_id, String check_in, String check_out) {
        this.namaPemesan = namaPemesan;
        this.email = email;
        this.telp = telp;
        this.jumlahKamar = jumlahKamar;
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public int getJumlahKamar() {
        return jumlahKamar;
    }

    public void setJumlahKamar(int jumlahKamar) {
        this.jumlahKamar = jumlahKamar;
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

}
