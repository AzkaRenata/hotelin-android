package com.example.hotelin_android.util;

public class myURL {

    private final static String BASE_URL = "http://192.168.43.221:8000/api/";
    private final static String IMAGE_URL = "http://192.168.43.221:8000/storage/";
    public final static String LOGIN_URL = BASE_URL + "user/login";
    public final static String PROFILE_URL = BASE_URL + "user/";
    public final static String CUSTOMER_REGISTER_URL = BASE_URL + "user/registerCusomer";
    public final static String SEARCH_HOTEL_URL = BASE_URL + "hotel/search/location";
    public static final String HOTEL_DETAIL_URL = BASE_URL + "hotel/detail/";
    public final static String UPDATE_USER_URL = PROFILE_URL + "update/";

    public String getBaseUrl(){
        return this.BASE_URL;
    }
    public static String getImageUrl(){
        return IMAGE_URL;
    }
}
