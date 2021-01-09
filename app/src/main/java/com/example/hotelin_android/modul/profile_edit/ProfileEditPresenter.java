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
            public void requestSuccess(UserTemp data) {
                view.setProfile(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void performRegister(UserTemp newUserTemp){
        Log.e("tes", "tes4");
        view.editUser(newUserTemp, new RequestCallback<UserTemp>() {
            @Override
            public void requestSuccess(UserTemp userTemp) {
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
        view.updatePicture(new RequestCallback<UserTemp>() {

            @Override
            public void requestSuccess(UserTemp data) {
                Log.e("reqSuccess", data.getUser_picture());
                view.setPicture(data);
            }

            @Override
            public void requestFailed(String errorMessage) {

            }
        });
    }
}
