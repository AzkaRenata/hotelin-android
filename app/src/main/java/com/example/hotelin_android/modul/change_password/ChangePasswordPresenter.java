package com.example.hotelin_android.modul.change_password;

import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.util.RequestCallback;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter{
    private final ChangePasswordContract.View view;

    public ChangePasswordPresenter(ChangePasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}


    @Override
    public void performUpdate(String newPassword, String oldPassword) {
        view.updatePassword(newPassword, oldPassword, new RequestCallback<SuccessMessage>() {
            @Override
            public void requestSuccess(SuccessMessage data, String message) {
                view.showSuccessMessage(data);
            }

            @Override
            public void requestFailed(String message) {
                view.showErrorMessage(message);
            }
        });
    }
}