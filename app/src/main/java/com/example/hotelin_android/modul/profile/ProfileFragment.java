package com.example.hotelin_android.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.change_password.ChangePasswordActivity;
import com.example.hotelin_android.modul.login.LoginActivity;
import com.example.hotelin_android.modul.profile_edit.ProfileEditActivity;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.SharedPreferences.UserSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.ProfilePresenter> implements ProfileContract.ProfileView {
    private TextView tvUsername;
    private TextView tvEmail;
    private CircleImageView civPhoto;

    private final UserSharedUtil userSharedUtil;

    public ProfileFragment() {
        userSharedUtil = UtilProvider.getUserSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        mPresenter = new ProfilePresenter(this);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems() {
        TextView tvEditProfile;
        TextView tvChangePassword;
        TextView tvLogout;

        tvUsername = fragmentView.findViewById(R.id.profile_username_tv);
        tvEmail = fragmentView.findViewById(R.id.profile_email_tv);
        tvEditProfile = fragmentView.findViewById(R.id.profile_edit_account_tv);
        tvChangePassword = fragmentView.findViewById(R.id.profile_change_password_tv);
        tvLogout = fragmentView.findViewById(R.id.profile_logout_tv);
        civPhoto = fragmentView.findViewById(R.id.profile_photo_civ);

        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTvEditProfileClick();
            }
        });

        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTvChangePasswordClick();
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTvLogoutClick();
            }
        });

        setTitle(getString(R.string.profile_title));
    }

    @Override
    public void setProfile() {
        tvUsername.setText(userSharedUtil.getUser().getUsername());
        tvEmail.setText(userSharedUtil.getUser().getEmail());

        if(userSharedUtil.getUser().getUser_picture() != null){
            String url = myURL.getImageUrl() + userSharedUtil.getUser().getUser_picture();
            new AsyncTaskLoadImage(civPhoto).execute(url);
        }
    }

    public void setTvEditProfileClick(){
        mPresenter.moveToEditProfile();
    }

    public void setTvChangePasswordClick(){
        mPresenter.moveToChangePassword();
    }

    public void setTvLogoutClick(){
        userSharedUtil.clear();
        mPresenter.performLogOut();
    }

    @Override
    public void redirectToChangePassword() {
        Intent intent = new Intent(activity, ChangePasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void redirectToEditProfile() {
        Intent intent = new Intent(activity, ProfileEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(ProfileContract.ProfilePresenter presenter) {
        mPresenter = presenter;
    }
}
