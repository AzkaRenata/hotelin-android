package com.example.hotelin_android.model;

public class BookingDetail {
    private Hotel hotel;
    private Room room;
    private Booking booking;

    public BookingDetail(Hotel hotel, Room room, Booking booking) {
        this.hotel = hotel;
        this.room = room;
        this.booking = booking;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
