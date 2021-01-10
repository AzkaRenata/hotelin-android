package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public interface ProfileEditContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void setProfile();
        void setPicture();
        void redirectToProfile();
        void saveUser(User user);
        void requestEditProfile(User user, final RequestCallback<ProfileEditResponse> requestCallback);
        void requestUpdatePicture(RequestCallback<ProfileEditResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performProfileEdit(User user);
        void performUpdatePicture();
    }
}