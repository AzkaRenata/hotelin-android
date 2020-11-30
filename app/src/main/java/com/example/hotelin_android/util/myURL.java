package com.example.hotelin_android.util;

public class myURL {
    private final static String BASE_URL = "http://192.168.100.6:8000/api/";
    private final static String IMAGE_URL = "http://192.168.100.6:8000/storage/";
    public final static String LOGIN_URL = BASE_URL + "user/login";
    public final static String CUSTOMER_REGISTER_URL = BASE_URL + "user/register";
    public final static String PROFILE_URL = BASE_URL + "user";
    public final static String HOTEL_URL = BASE_URL + "hotel/detail/";
    public final static String BOOKING_URL = BASE_URL + "booking";
    public final static String CUSTOMER_REGISTER_URL = BASE_URL + "user/registerCustomer";
    public final static String SEARCH_HOTEL_URL = BASE_URL + "hotel/search/location";
    public final static String SEARCH_ROOM_URL = BASE_URL + "room/hotel/";
    public final static String BOOKING_HISTORY_URL = BASE_URL + "booking/list/";
    public String getBaseUrl(){
        return this.BASE_URL;
    }
    public static String getImageUrl(){
        return IMAGE_URL;
    }
}
