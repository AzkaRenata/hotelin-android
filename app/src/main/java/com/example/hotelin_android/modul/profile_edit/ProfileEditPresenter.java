package com.example.hotelin_android.modul.profile_edit;

import android.util.Log;

import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public class ProfileEditPresenter implements ProfileEditContract.ProfileEditPresenter {
    ProfileEditContract.ProfileEditView view;
    String bearerToken;


    public ProfileEditPresenter(ProfileEditContract.ProfileEditView view) {
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
        view.editUser(newUser, new RequestCallback<String>() {
            @Override
            public void requestSuccess(String message) {
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }
}
