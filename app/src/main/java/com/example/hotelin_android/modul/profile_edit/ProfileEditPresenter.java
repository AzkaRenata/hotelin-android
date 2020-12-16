package com.example.hotelin_android.modul.profile_edit;

import android.net.Uri;
import android.util.Log;

import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

import java.io.File;

public class ProfileEditPresenter implements ProfileEditContract.Presenter {
    ProfileEditContract.View view;
    String bearerToken;


    public ProfileEditPresenter(ProfileEditContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void showData(){
        view.requestProfile(new RequestCallback<User>() {
            @Override
            public void requestSuccess(User data) {
                view.setProfile(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void performRegister(User newUser){
        Log.e("tes", "tes4");
        view.editUser(newUser, new RequestCallback<User>() {
            @Override
            public void requestSuccess(User user) {
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void performUpdatePicture() {
        view.updatePicture(new RequestCallback<User>() {

            @Override
            public void requestSuccess(User data) {
                Log.e("reqSuccess", data.getUser_picture());
                view.setPicture(data);
            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }
}
