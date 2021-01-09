package com.example.hotelin_android.modul.profile;

import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;

public class ProfilePresenter implements ProfileContract.ProfilePresenter {
    ProfileContract.ProfileView view;
    TokenSharedUtil tokenSharedUtil;


    public ProfilePresenter(ProfileContract.ProfileView view, TokenSharedUtil tokenSharedUtil) {
        this.view = view;
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Override
    public void start() {

    }

    @Override
    public void performLogOut() {
        if (tokenSharedUtil.getToken() != null) {
            tokenSharedUtil.clear();
            view.redirectToLogin();
        }
    }

    @Override
    public void showData() {
        view.requestProfile(new RequestCallback<UserTemp>() {
            @Override
            public void requestSuccess(UserTemp data) {
                view.setProfile(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}
