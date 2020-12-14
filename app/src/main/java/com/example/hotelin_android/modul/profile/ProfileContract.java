package com.example.hotelin_android.modul.profile;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.hotel_detail.HotelDetail;
import com.example.hotelin_android.util.RequestCallback;

public interface ProfileContract {
    interface ProfileView extends BaseView<ProfilePresenter> {
        void redirectToChangePassword();
        void redirectToLogin();
        void redirectToBookingHistory();
        void redirectToEditProfile();
        void requestProfile(RequestCallback<User> requestCallback);
        void setProfile(User user);
        void showFailedMessage(String message);
    }

    interface ProfilePresenter extends BasePresenter {
        void showData();
        void performLogOut();
    }
}