package com.example.hotelin_android.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.hotel_detail.HotelDetail;
import com.example.hotelin_android.modul.profile_edit.ProfileEditActivity;
import com.example.hotelin_android.modul.test.TestActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;

public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.ProfilePresenter> implements ProfileContract.ProfileView {
    SharedPreferencesUtil sharedPreferencesUtil;

    TextView profileNameTV;
    TextView profileEmailTV;
    CardView editProfileCV;

    ProfilePresenter profilePresenter;

    public ProfileFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_activity, container, false);

        profileNameTV = view.findViewById(R.id.name_tv);
        profileEmailTV = view.findViewById(R.id.email_tv);
        editProfileCV = view.findViewById(R.id.editProfile_cv);

        profilePresenter = new ProfilePresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        profilePresenter.fetchProfile("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3Q6ODAwMFwvYXBpXC91c2VyXC9sb2dpbiIsImlhdCI6MTYwNjU3ODY1OCwiZXhwIjoxNjA2NTgyMjU5LCJuYmYiOjE2MDY1Nzg2NTksImp0aSI6InVWWFhTekJ1eWd3REFFNHkiLCJzdWIiOjMsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.dYgMAVqJv1YMAMw0ICClIZto1u2C5ku4bBd0_gJ3WYo");

        editProfileCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToEditProfile();
            }
        });
    }

    private void redirectToEditProfile() {
        Intent intent = new Intent(activity, ProfileEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void setProfileData(User user) {
        profileNameTV.setText(user.getName());
        profileEmailTV.setText(user.getEmail());
    }

    @Override
    public void setPresenter(ProfileContract.ProfilePresenter presenter) {

    }
}
