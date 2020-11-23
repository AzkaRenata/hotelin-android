package com.example.hotelin_android.model;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private int user_level;
    private String gender;
    private String telp;
    private String user_picture;
    private String email_verified_at;
    private String created_at;
    private String updated_at;

    public User(int id, String name, String username, String email, int user_level, String gender, String telp, String user_picture, String email_verified_at, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.user_level = user_level;
        this.gender = gender;
        this.telp = telp;
        this.user_picture = user_picture;
        this.email_verified_at = email_verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
