package com.example.hotelin_android.modul.register;

import com.example.hotelin_android.model.User;
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
    public void performRegister(User user){
        view.requestRegister(user, new RequestCallback<RegisterResponse>() {
            @Override
            public void requestSuccess(RegisterResponse data) {
                view.showSuccessMessage();
                view.redirectToLogin();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

}