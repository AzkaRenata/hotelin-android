package com.example.hotelin_android.modul.change_password;

import com.example.hotelin_android.util.RequestCallback;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter{
    private final ChangePasswordActivity activity;
    private final ChangePasswordContract.View view;

    public ChangePasswordPresenter(ChangePasswordContract.View view, ChangePasswordActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start() {
        view.setItems();
    }


    @Override
    public void performUpdate(String newPassword, String oldPassword, String confirmNewPassword) {
        activity.startLoading();
        view.updatePassword(newPassword,  oldPassword, confirmNewPassword, new RequestCallback<ChangePasswordResponse>() {
            @Override
            public void requestSuccess(ChangePasswordResponse data, String message) {
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
}