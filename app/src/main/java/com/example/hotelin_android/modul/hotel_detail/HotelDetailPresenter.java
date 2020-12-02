package com.example.hotelin_android.modul.hotel_detail;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.hotelin_android.util.myURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HotelDetailPresenter implements HotelDetailContract.HotelDetailPresenter {
    HotelDetailContract.HotelDetailView view;


    public HotelDetailPresenter(HotelDetailContract.HotelDetailView view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void fetchHotelDetail(int hotelID, String bearerToken) {
        final HotelDetail[] result = new HotelDetail[1];

        JSONArrayRequestListener requestListener = new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject responseObject = (JSONObject) response.get(0);

                    int id = responseObject.getInt("id");
                    int user_id = responseObject.getInt("user_id");

                    String hotel_name = responseObject.getString("hotel_name");
                    String hotel_location = responseObject.getString("hotel_location");
                    String hotel_desc = responseObject.getString("hotel_desc");
                    String hotel_picture = responseObject.getString("hotel_picture");


                    result[0] = new HotelDetail(id, hotel_name, hotel_location, hotel_desc, hotel_picture, user_id);

                    view.setHotelDetailData(result[0]);

                }catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.e("HotelDetailPresenter : ", "ga dapet respon om");
            }
        };

        AndroidNetworking
                .get(myURL.HOTEL_URL + hotelID)
                .addHeaders("Authorization", "Bearer " + bearerToken)
                .build()
                .getAsJSONArray(requestListener);
    }
}
