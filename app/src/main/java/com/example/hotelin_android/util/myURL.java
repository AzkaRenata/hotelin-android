package com.example.hotelin_android.util;

public class myURL {
    private final static String BASE_URL = "http://192.168.1.5:8000/api/";
    private final static String IMAGE_URL = "http://192.168.1.5:8000/storage/";
    public final static String LOGIN_URL = BASE_URL + "user/login/customer";
    public final static String CUSTOMER_REGISTER_URL = BASE_URL + "user/registerCustomer";
    public final static String PROFILE_URL = BASE_URL + "user";
    public final static String HOTEL_URL = BASE_URL + "hotel/detail/";
    public final static String BOOKING_URL = BASE_URL + "booking/create";
    public final static String SEARCH_HOTEL_URL = BASE_URL + "hotel/search/location";
    public final static String SEARCH_ROOM_URL = BASE_URL + "room/hotel/";
    public final static String VALIDATE_TIME = BASE_URL + "room/validate-time/";
    public final static String BOOKING_HISTORY_URL = BASE_URL + "booking/list/";
    public final static String MY_BOOKING_URL = BASE_URL + "booking/show/";
    public final static String CANCEL_BOOKING = BASE_URL + "booking/change-status/";
    public static final String HOTEL_DETAIL_URL = BASE_URL + "hotel/detail/";
    public final static String UPDATE_USER_URL = BASE_URL + "user/update/";
    public final static String UPDATE_USER_PICTURE_URL = BASE_URL + "user/update-picture";
    public final static String UPDATE_PASSWORD_URL = BASE_URL + "user/update-password";
    public static String getImageUrl(){
        return IMAGE_URL;
    }


}
