package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.LoginResponse;
import com.alifadepe.android_example.callback.RequestCallback;
import com.alifadepe.android_example.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    public LoginPresenter(LoginContract.View view, LoginContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String username, String password) {
        view.startLoading();
        interactor.requestLogin(username, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                view.endLoading();
                view.loginSuccess();
                interactor.saveToken(data.token);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.loginFailed(errorMessage);
            }
        });
    }
}
