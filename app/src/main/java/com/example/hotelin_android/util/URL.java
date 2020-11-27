package com.example.hotelin_android.util;

public class URL {
//    private final static String BASE_URL = "http://192.168.1.2:8000/api/";
    private final static String BASE_URL = "http://1bedae87edba.ngrok.io/api/";
    public final static String LOGIN_URL = BASE_URL + "user/login";
    public final static String PROFILE_URL = BASE_URL + "user";
    public final static String HOTEL_DETAIL_URL = BASE_URL + "hotel/detail/";
    public final static String CUSTOMER_REGISTER_URL = BASE_URL + "user/registerCustomer";
    public String getBaseUrl(){
        return this.BASE_URL;
    }
}
