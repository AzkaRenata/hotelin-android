package com.example.hotelin_android.modul.profile_edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.modul.test.TestActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;

public class ProfileEditFragment extends BaseFragment<ProfileEditActivity, ProfileEditContract.ProfileEditPresenter> implements ProfileEditContract.ProfileEditView {
    SharedPreferencesUtil sharedPreferencesUtil;

    TextView usernameET;
    TextView nameET;
    TextView emailET;
    TextView telpET;
    TextView passwordET;
    RadioGroup genderRG;
    RadioButton maleRB;
    RadioButton femaleRB;
    Button saveBTN;

    User userData;

    ProfileEditPresenter profileEditPresenter;

    public ProfileEditFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile_activity, container, false);

        usernameET = view.findViewById(R.id.username_edit_et);
        nameET = view.findViewById(R.id.name_edit_et);
        emailET = view.findViewById(R.id.email_edit_et);
        telpET = view.findViewById(R.id.telp_edit_et);
        passwordET = view.findViewById(R.id.passwod_edit_et);
        genderRG = view.findViewById(R.id.gender_edit_rg);
        maleRB = view.findViewById(R.id.male_edit_rb);
        femaleRB = view.findViewById(R.id.female_edit_rb);
        saveBTN = view.findViewById(R.id.save_edit_btn);

        profileEditPresenter = new ProfileEditPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        profileEditPresenter.fetchEditProfile("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3Q6ODAwMFwvYXBpXC91c2VyXC9sb2dpbiIsImlhdCI6MTYwNjU3ODY1OCwiZXhwIjoxNjA2NTgyMjU5LCJuYmYiOjE2MDY1Nzg2NTksImp0aSI6InVWWFhTekJ1eWd3REFFNHkiLCJzdWIiOjMsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.dYgMAVqJv1YMAMw0ICClIZto1u2C5ku4bBd0_gJ3WYo");

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasAnyDataEdited() == true) {
                    profileEditPresenter.updateUserData(getCurrentUserData());
                } else {
                    redirectToProfile();
                }
            }
        });
    }



    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
    }

    private boolean hasAnyDataEdited() {
        boolean flag = true;

        if(usernameET.getText().length() == 0) flag = false;
        if(nameET.getText().length() == 0) flag = false;
        if(emailET.getText().length() == 0) flag = false;
        if(telpET.getText().length() == 0) flag = false;
        if(passwordET.getText().length() == 0) flag = false;

        if(userData.getGender() != null) {
            if(userData.getGender() != "Female" || femaleRB.isChecked() == true) flag = false;
            if(userData.getGender() != "Male" || maleRB.isChecked() == true) flag = false;
        }

        return !flag;

    }

    private User getCurrentUserData() {
        return new User(
                userData.getId(),
                usernameET.getText().length() == 0 ? userData.getUsername() : usernameET.getText().toString(),
                nameET.getText().length() == 0 ? userData.getName() : nameET.getText().toString(),
                emailET.getText().length() == 0 ? userData.getEmail() : emailET.getText().toString(),
                userData.getPassword(),
                userData.getUser_level(),
                getCurrentGender(),
                telpET.getText().length() == 0 ? userData.getTelp() : telpET.getText().toString(),
                userData.getAddress(),
                userData.getUser_picture()
        );

                //this(id, username, name, email, password, user_level, gender, telp, address, user_picture)
    }

    private String getCurrentGender() {
        if(femaleRB.isChecked() == true) return "Female";
        if(maleRB.isChecked() == true) return "Male";
        return null;
    }

    @Override
    public void setProfileData(User user) {
        this.userData = user;

        usernameET.setHint(user.getUsername());
        nameET.setHint(user.getName());
        emailET.setHint(user.getEmail());
        telpET.setHint(user.getTelp());
        passwordET.setHint(user.getPassword());

        if(userData.getGender() != null) {
            if (user.getGender() == "Female") {
                femaleRB.setChecked(true);
            }

            if (user.getGender() == "Male") {
                maleRB.setChecked(true);
            }
        }
    }

    @Override
    public void setPresenter(ProfileEditContract.ProfileEditPresenter presenter) {

    }
}
