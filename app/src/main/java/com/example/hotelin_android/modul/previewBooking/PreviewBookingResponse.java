package com.example.hotelin_android.modul.previewBooking;

public class PreviewBookingResponse {
    public int id;
    public int user_id;
    public int room_id;
    public String check_in;
    public String check_out;
    public String booking_time;

    public PreviewBookingResponse(int id, int user_id, int room_id, String check_in, String check_out, String booking_time) {
        this.id = id;
        this.user_id = user_id;
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.booking_time = booking_time;
    }
}