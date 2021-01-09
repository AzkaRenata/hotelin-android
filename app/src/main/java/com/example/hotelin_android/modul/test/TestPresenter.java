package com.example.hotelin_android.modul.test;

import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.util.RequestCallback;

public class TestPresenter implements TestContract.Presenter{
    private final TestContract.View view;

    public TestPresenter(TestContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void showData(){
        view.requestProfile(new RequestCallback<UserTemp>() {
            @Override
            public void requestSuccess(UserTemp data, String message) {
                view.setProfile(data);
            }

            @Override
            public void requestFailed(String message) {
                view.showFailedMessage(message);
            }
        });
    }
}