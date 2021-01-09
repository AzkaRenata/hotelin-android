package com.example.hotelin_android.modul.profile;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;

public interface ProfileContract {
    interface ProfileView extends BaseView<ProfilePresenter> {
        void redirectToChangePassword();
        void redirectToLogin();
        void redirectToBookingHistory();
        void redirectToEditProfile();
        void requestProfile(RequestCallback<UserTemp> requestCallback);
        void setProfile(UserTemp userTemp);
        void showFailedMessage(String message);
    }

    interface ProfilePresenter extends BasePresenter {
        void showData();
        void performLogOut();
    }
}