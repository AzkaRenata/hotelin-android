package com.example.hotelin_android.modul.profile;

public class ProfilePresenter implements ProfileContract.ProfilePresenter {
    private final ProfileContract.ProfileView view;

    public ProfilePresenter(ProfileContract.ProfileView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setItems();
        view.setProfile();
    }

    @Override
    public void performLogOut() {
        view.redirectToLogin();
    }

    @Override
    public void moveToEditProfile() {
        view.redirectToEditProfile();
    }

    @Override
    public void moveToChangePassword() {
        view.redirectToChangePassword();
    }
}
