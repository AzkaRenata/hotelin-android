package com.example.hotelin_android.model;

public class User {
    private int id;
    private String username;
    private String name;
    private String email;
    private String gender;
    private String telp;
    private String address;
    private String user_picture;

    public User(int id, String username, String name, String email, String gender, String telp, String address, String user_picture) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.telp = telp;
        this.address = address;
        this.user_picture = user_picture;
    }

    public User(String username, String name, String email, String gender, String telp, String address) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.telp = telp;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }
}
