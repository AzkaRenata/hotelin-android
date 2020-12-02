package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.User;

public interface ProfileEditContract {
    interface ProfileEditView extends BaseView<ProfileEditPresenter> {
        void setProfileData(User user);
        void redirectToProfile();
    }

    interface ProfileEditPresenter extends BasePresenter {
        void fetchEditProfile(String bearerToken);
        void updateUserData(User user);
    }
}