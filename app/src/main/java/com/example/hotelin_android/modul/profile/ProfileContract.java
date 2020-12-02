package com.example.hotelin_android.modul.profile;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.hotel_detail.HotelDetail;

public interface ProfileContract {
    interface ProfileView extends BaseView<ProfilePresenter> {
        void setProfileData(User user);
    }

    interface ProfilePresenter extends BasePresenter {
        void fetchProfile(String bearerToken);
    }
}