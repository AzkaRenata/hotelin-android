package com.example.hotelin_android.util;

public class myURL {
    private final static String BASE_URL = "http://api.hotelin.live/api/";
    private final static String IMAGE_URL = "http://api.hotelin.live/storage/";

    public final static String LOGIN_URL = BASE_URL + "user/login/customer";
    public final static String REGISTER_URL = BASE_URL + "user/register/customer";
    public final static String SEARCH_HOTEL_URL = BASE_URL + "hotel/search/location";
    public final static String GET_HOTEL_DETAIL_URL = BASE_URL + "hotel/detail-by-id/";
    public final static String SEARCH_AVAILABLE_ROOM_URL = BASE_URL + "room/show-available/";
    public final static String GET_ROOM_DETAIL_URL = BASE_URL + "room/detail/";
    public final static String BOOKING_URL = BASE_URL + "booking/create";
    public final static String BOOKING_HISTORY_URL = BASE_URL + "booking/list/";
    public final static String BOOKING_DETAIL_URL = BASE_URL + "booking/detail/";
    public final static String CANCEL_BOOKING_URL = BASE_URL + "booking/cancel/";
    public final static String EDIT_PROFILE_URL = BASE_URL + "user/update/user";
    public final static String EDIT_PROFILE_PICTURE_URL = BASE_URL + "user/update/picture";
    public final static String UPDATE_PASSWORD_URL = BASE_URL + "user/update/password";

    public static String getImageUrl(){
        return IMAGE_URL;
    }


}
