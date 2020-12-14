package com.example.hotelin_android.modul.change_password;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.util.RequestCallback;

public interface ChangePasswordContract {
    interface View extends BaseView<Presenter> {
        void redirectToProfile();
        void showSuccessMessage(SuccessMessage data);
        void showErrorMessage(String message);
        void updatePassword(String newPassword, String oldPassword, final RequestCallback<SuccessMessage> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performUpdate(String newPassword, String oldPassword);
    }
}
