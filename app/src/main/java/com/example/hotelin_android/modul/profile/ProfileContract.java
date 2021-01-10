package com.example.hotelin_android.modul.profile;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface ProfileContract {
    interface ProfileView extends BaseView<ProfilePresenter> {
        void setItems();
        void setProfile();
        void redirectToChangePassword();
        void redirectToEditProfile();
        void redirectToLogin();
    }

    interface ProfilePresenter extends BasePresenter {
        void performLogOut();
        void moveToEditProfile();
        void moveToChangePassword();
    }
}