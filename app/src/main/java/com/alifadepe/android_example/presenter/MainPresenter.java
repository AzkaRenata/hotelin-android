package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainContract.Interactor interactor;

    public MainPresenter(MainContract.View view, MainContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void checkIsUserLogin() {
        if(interactor.isUserLogin()){
            view.whenUserLogin();
        }
        else {
            view.whenUserNotLogin();
        }
    }
}
