package com.example.hotelin_android.modul.register;

import android.util.Log;

import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void start(){
        view.setItems();
    }

    @Override
    public void performRegister(UserTemp newUserTemp){
        Log.e("tes", "tes4");
        view.requestRegister(newUserTemp, new RequestCallback<RegisterResponse>() {
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