package com.example.hotelin_android.model;

public class Facility {
    private int id;
    private String facility_name;
    private String facility_icon;

    public Facility(int id, String facility_name, String facility_icon) {
        this.id = id;
        this.facility_name = facility_name;
        this.facility_icon = facility_icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public String getFacility_icon() {
        return facility_icon;
    }

    public void setFacility_icon(String facility_icon) {
        this.facility_icon = facility_icon;
    }
}
