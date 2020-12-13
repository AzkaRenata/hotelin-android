package com.example.hotelin_android.modul.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.signature.ObjectKey;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.modul.change_password.ChangePasswordActivity;
import com.example.hotelin_android.modul.login.LoginActivity;
import com.example.hotelin_android.modul.profile_edit.ProfileEditActivity;
import com.example.hotelin_android.modul.test.TestResponse;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

import de.hdodenhof.circleimageview.CircleImageView;
public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.ProfilePresenter> implements ProfileContract.ProfileView, View.OnClickListener {
    SharedPreferencesUtil sharedPreferencesUtil;

    TextView tvName;
    TextView tvEmail;
    TextView tvEditProfile;
    TextView tvHistoryBooking;
    TextView tvChangePassword;
    TextView tvLogout;
    CircleImageView civPhoto;
    String password;

    ProfilePresenter profilePresenter;

    public ProfileFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.profile_activity, container, false);
        mPresenter = new ProfilePresenter(this);
        mPresenter.start();

        tvName = fragmentView.findViewById(R.id.name_tv);
        tvEmail = fragmentView.findViewById(R.id.email_tv);
        tvEditProfile = fragmentView.findViewById(R.id.tvAccountInformation);
        tvHistoryBooking = fragmentView.findViewById(R.id.tvBookingHistory);
        tvChangePassword = fragmentView.findViewById(R.id.tvChangePassword);
        tvLogout = fragmentView.findViewById(R.id.tvLogOut);
        civPhoto = fragmentView.findViewById(R.id.profile_photo_civ);

        tvEditProfile.setOnClickListener(this);
        tvHistoryBooking.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
        mPresenter.showData();

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvAccountInformation :
                redirectToEditProfile();
                break;
            case R.id.tvBookingHistory :
                redirectToBookingHistory();
                break;
            case R.id.tvChangePassword :
                redirectToChangePassword();
                break;
            case R.id.tvLogOut :
                redirectToLogin();
                break;
        }
    }

    @Override
    public void redirectToChangePassword() {
        Intent intent = new Intent(activity, ChangePasswordActivity.class);
        intent.putExtra("oldPassword", password);
        startActivity(intent);
    }

    @Override
    public void redirectToEditProfile() {
        Intent intent = new Intent(activity, ProfileEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void redirectToBookingHistory() {
        Intent intent = new Intent(activity, BookingHistoryActivity.class);
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

    @Override
    public void requestProfile(final RequestCallback<User> requestCallback) {
        AndroidNetworking.get(myURL.PROFILE_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(TestResponse.class, new ParsedRequestListener<TestResponse>() {
                    @Override
                    public void onResponse(TestResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }else{
                            requestCallback.requestSuccess(response.user);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void setProfile(User user){
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
        Glide.with(fragmentView)
                .load(myURL.getImageUrl()+user.getUser_picture())
                .error(R.drawable.ic_profile_picture)
                .signature(new ObjectKey(System.currentTimeMillis()))
                .into(civPhoto);
        password = user.getPassword();
    }


    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }

}
