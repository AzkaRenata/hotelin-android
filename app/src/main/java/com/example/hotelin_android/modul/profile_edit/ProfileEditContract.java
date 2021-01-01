package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;

public interface ProfileEditContract {
    interface View extends BaseView<Presenter> {
        //void setProfileData(User user);
        void redirectToProfile();
        void requestProfile(RequestCallback<UserTemp> requestCallback);
        void setProfile(UserTemp userTemp);
        void showSuccessMessage();
        void setPicture(UserTemp userTemp);
        void showErrorMessage(String message);
        void editUser(UserTemp newUserTemp, final RequestCallback<UserTemp> requestCallback);
        void updatePicture(RequestCallback<UserTemp> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void showData();
        void performRegister(UserTemp newUserTemp);
        void performUpdatePicture();
    }
}