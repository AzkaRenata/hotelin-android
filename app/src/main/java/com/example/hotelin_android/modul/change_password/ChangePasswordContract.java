package com.example.hotelin_android.modul.change_password;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.util.RequestCallback;

public interface ChangePasswordContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void redirectToProfile();
        void updatePassword(String newPassword, String oldPassword, String confirmNewPassword, final RequestCallback<ChangePasswordResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performUpdate(String newPassword, String oldPassword, String confirmNewPassword);
    }
}
