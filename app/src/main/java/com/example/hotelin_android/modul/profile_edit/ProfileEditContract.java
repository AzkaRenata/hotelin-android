package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.register.RegisterResponse;
import com.example.hotelin_android.util.RequestCallback;

public interface ProfileEditContract {
    interface ProfileEditView extends BaseView<ProfileEditPresenter> {
        //void setProfileData(User user);
        void redirectToProfile();
        void requestProfile(RequestCallback<User> requestCallback);
        void setProfile(User user);
        void showSuccessMessage();
        void showErrorMessage(String message);
        void editUser(User newUser, final RequestCallback<String> requestCallback);
    }

    interface ProfileEditPresenter extends BasePresenter {
        void showData();
        void performRegister(User newUser);
    }
}