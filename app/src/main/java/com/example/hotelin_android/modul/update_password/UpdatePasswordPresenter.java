package com.example.hotelin_android.modul.update_password;

import android.util.Log;

import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public class UpdatePasswordPresenter implements UpdatePasswordContract.Presenter {
    UpdatePasswordContract.View view;
    String bearerToken;


    public UpdatePasswordPresenter(UpdatePasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void performUpdate(String password) {
        view.updatePassword(password, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data) {
                view.showSuccessMessage(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

}
