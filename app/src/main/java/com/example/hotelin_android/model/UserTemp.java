package com.example.hotelin_android.model;

public class UserTemp {
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;
    private int user_level;
    private String gender;
    private String telp;
    private String address;
    private String user_picture;

    public UserTemp(int id, String username, String name, String email, String password, int user_level, String gender, String telp, String address, String user_picture) {
        this(username, name, email, password, user_level, gender, telp, address, user_picture);
        this.id = id;
    }

    public UserTemp(String username, String name, String email, String password, int user_level, String gender, String telp, String address, String user_picture) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_level = user_level;
        this.gender = gender;
        this.telp = telp;
        this.address = address;
        this.user_picture = user_picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
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