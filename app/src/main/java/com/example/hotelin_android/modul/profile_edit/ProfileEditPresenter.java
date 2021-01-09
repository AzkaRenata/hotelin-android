package com.example.hotelin_android.modul.profile_edit;

import android.util.Log;

import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;

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
        view.requestProfile(new RequestCallback<UserTemp>() {
            @Override
            public void requestSuccess(UserTemp data, String message) {
                view.setProfile(data);
            }

            @Override
            public void requestFailed(String message) {
                view.showErrorMessage(message);
            }
        });
    }

    @Override
    public void performRegister(UserTemp newUserTemp){
        Log.e("tes", "tes4");
        view.editUser(newUserTemp, new RequestCallback<UserTemp>() {
            @Override
            public void requestSuccess(UserTemp userTemp, String message) {
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String message) {
                view.showErrorMessage(message);
            }
        });
    }

    @Override
    public void performUpdatePicture() {
        view.updatePicture(new RequestCallback<UserTemp>() {

            @Override
            public void requestSuccess(UserTemp data, String message) {
                Log.e("reqSuccess", data.getUser_picture());
                view.setPicture(data);
            }

            @Override
            public void requestFailed(String message) {

            }
        });
    }
}
