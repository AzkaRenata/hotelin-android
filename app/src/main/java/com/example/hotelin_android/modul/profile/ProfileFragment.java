package com.example.hotelin_android.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.modul.login.LoginActivity;
import com.example.hotelin_android.modul.profile_edit.ProfileEditActivity;
import com.example.hotelin_android.modul.test.TestResponse;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.ProfilePresenter> implements ProfileContract.ProfileView {
    SharedPreferencesUtil sharedPreferencesUtil;

    TextView tvName;
    TextView tvEmail;
    CardView cvEditProfile;
    CardView cvHistoryBooking;
    CardView cvLogout;

    ProfilePresenter profilePresenter;

    public ProfileFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_activity, container, false);
        mPresenter = new ProfilePresenter(this);
        mPresenter.start();

        tvName = view.findViewById(R.id.name_tv);
        tvEmail = view.findViewById(R.id.email_tv);
        cvEditProfile = view.findViewById(R.id.editProfile_cv);
        cvHistoryBooking = view.findViewById(R.id.cvHistoriBooking);
        cvLogout = view.findViewById(R.id.cvLogout);

        cvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToEditProfile();
            }
        });

        cvHistoryBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToBookingHistory();
            }
        });

        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToLogin();
            }
        });

        mPresenter.showData();

        return view;
    }

    private void redirectToEditProfile() {
        Intent intent = new Intent(activity, ProfileEditActivity.class);
        startActivity(intent);
    }

    private void redirectToBookingHistory() {
        Intent intent = new Intent(activity, BookingHistoryActivity.class);
        startActivity(intent);
    }

    private void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void setProfileData(User user) {
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
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
    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}
