package com.example.hotelin_android.modul.register;

import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterActivity activity;
    private final RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view, RegisterActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start(){
        view.setItems();
    }

    @Override
    public void performRegister(User user){
        activity.startLoading();
        view.requestRegister(user, new RequestCallback<RegisterResponse>() {
            @Override
            public void requestSuccess(RegisterResponse data) {
                activity.stopLoading();
                view.showSuccessMessage();
                view.redirectToLogin();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showErrorMessage(errorMessage);
            }
        });
    }
}