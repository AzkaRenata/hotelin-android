package com.example.hotelin_android.modul.update_password;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

public interface UpdatePasswordContract {
    interface View extends BaseView<Presenter> {
        //void setProfileData(User user);
//        void redirectToProfile();
//        void requestProfile(RequestCallback<User> requestCallback);
//        void setProfile(User user);
        void showSuccessMessage(SuccessMessage data);
        void showErrorMessage(String message);
        void updatePassword(String password, final RequestCallback<SuccessMessage> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performUpdate(String password);
    }

}