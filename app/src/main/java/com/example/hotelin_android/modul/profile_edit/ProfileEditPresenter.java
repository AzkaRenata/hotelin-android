package com.example.hotelin_android.modul.profile_edit;

import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public class ProfileEditPresenter implements ProfileEditContract.Presenter {
    private final ProfileEditActivity activity;
    private final ProfileEditContract.View view;


    public ProfileEditPresenter(ProfileEditContract.View view, ProfileEditActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
        view.setProfile();
        view.setPicture();
    }

    @Override
    public void performProfileEdit(User user){
        activity.startLoading();
        view.requestEditProfile(user, new RequestCallback<ProfileEditResponse>() {
            @Override
            public void requestSuccess(ProfileEditResponse response, String message) {
                view.saveUser(response.user);
                activity.stopLoading();
                activity.showMessage(message);
                view.redirectToProfile();
            }

            @Override
            public void requestFailed(String message) {
                activity.stopLoading();
                activity.showMessage(message);
            }
        });
    }

    @Override
    public void performUpdatePicture() {
        activity.startLoading();
        view.requestUpdatePicture(new RequestCallback<ProfileEditResponse>() {
            @Override
            public void requestSuccess(ProfileEditResponse response, String message) {
                view.saveUser(response.user);
                view.setPicture();
                activity.stopLoading();
                activity.showMessage(message);
            }

            @Override
            public void requestFailed(String message) {
                activity.stopLoading();
                activity.showMessage(message);
            }
        });
    }
}
