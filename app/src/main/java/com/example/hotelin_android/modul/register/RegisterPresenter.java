package com.example.hotelin_android.modul.register;

import android.util.Log;

import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performRegister(User newUser){
        Log.e("tes", "tes4");
        view.requestRegister(newUser, new RequestCallback<RegisterResponse>() {
            @Override
            public void requestSuccess(RegisterResponse data) {
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

}