package com.example.hotelin_android.modul.login;

import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.TokenSharedUtil;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final TokenSharedUtil sessionRepository;

    public LoginPresenter(LoginContract.View view, TokenSharedUtil sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void start() {
        view.setItems();
        /*
        if(sessionRepository.getToken() != null){
            view.redirectToHome();
        }
         */
    }

    @Override
    public void performLogin(String email, String password){
        view.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.redirectToHome();
                view.saveToken(data.token);
                view.saveUser(data.user);
                view.showSuccessMessage();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}