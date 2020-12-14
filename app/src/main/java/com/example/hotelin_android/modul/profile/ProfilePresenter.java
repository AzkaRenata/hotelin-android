package com.example.hotelin_android.modul.profile;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.hotel_detail.HotelDetail;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfilePresenter implements ProfileContract.ProfilePresenter {
    ProfileContract.ProfileView view;
    SharedPreferencesUtil sharedPreferencesUtil;


    public ProfilePresenter(ProfileContract.ProfileView view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void start() {

    }

    @Override
    public void performLogOut() {
        if (sharedPreferencesUtil.getToken() != null) {
            sharedPreferencesUtil.clear();
            view.redirectToLogin();
        }
    }

    @Override
    public void showData() {
        view.requestProfile(new RequestCallback<User>() {
            @Override
            public void requestSuccess(User data) {
                view.setProfile(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}
