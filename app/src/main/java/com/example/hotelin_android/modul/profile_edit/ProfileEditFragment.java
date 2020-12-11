package com.example.hotelin_android.modul.profile_edit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.modul.profile.ProfilePresenter;
import com.example.hotelin_android.modul.register.RegisterResponse;
import com.example.hotelin_android.modul.test.TestActivity;
import com.example.hotelin_android.modul.test.TestResponse;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

import static com.example.hotelin_android.R.id.add;
import static com.example.hotelin_android.R.id.female_edit_rb;
import static com.example.hotelin_android.R.id.male_edit_rb;
import static com.example.hotelin_android.R.id.radio_femaleR;
import static com.example.hotelin_android.R.id.radio_maleR;

public class ProfileEditFragment extends BaseFragment<ProfileEditActivity, ProfileEditContract.ProfileEditPresenter> implements ProfileEditContract.ProfileEditView {
    SharedPreferencesUtil sharedPreferencesUtil;

    TextView usernameET;
    TextView nameET;
    TextView emailET;
    TextView telpET;
    TextView addressET;
    TextView passwordET;
    RadioGroup genderRG;
    RadioButton maleRB;
    RadioButton femaleRB;
    Button saveBTN;
    String gender;
    String password;

    public ProfileEditFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile_activity, container, false);
        mPresenter = new ProfileEditPresenter(this);
        mPresenter.start();

        usernameET = view.findViewById(R.id.username_edit_et);
        nameET = view.findViewById(R.id.name_edit_et);
        emailET = view.findViewById(R.id.email_edit_et);
        telpET = view.findViewById(R.id.telp_edit_et);
        addressET = view.findViewById(R.id.address_edit_et);
        genderRG = view.findViewById(R.id.gender_edit_rg);
        maleRB = view.findViewById(male_edit_rb);
        femaleRB = view.findViewById(female_edit_rb);
        saveBTN = view.findViewById(R.id.save_edit_btn);

        mPresenter.showData();

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBtnClick();
            }
        });

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkGender(group, checkedId);
            }
        });

        return view;
    }

    public void checkGender(RadioGroup group, int checkedId){
        switch (checkedId){
            case male_edit_rb:
                gender = "male";
                Log.e("tes", gender);
                break;
            case female_edit_rb:
                gender = "female";
                Log.e("tes", gender);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + checkedId);
        }
    }

    public void saveBtnClick(){
        String username = usernameET.getText().toString();
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String address = addressET.getText().toString();
        String telp = telpET.getText().toString();

        User user = new User(username, name, email, password, 2, gender, telp, address, null);
        mPresenter.performRegister(user);
    }

    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void editUser(final User newUser, final RequestCallback<String> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_USER_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("username", newUser.getUsername())
                .addBodyParameter("name", newUser.getName())
                .addBodyParameter("email", newUser.getEmail())
                .addBodyParameter("gender", newUser.getGender())
                .addBodyParameter("telp", newUser.getTelp())
                .addBodyParameter("address", newUser.getAddress())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProfileEditResponse.class, new ParsedRequestListener<ProfileEditResponse>() {
                    @Override
                    public void onResponse(ProfileEditResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 401) {
                            Log.e("tesqqq", "ERROR", anError);
                            requestCallback.requestFailed("Please input a valid e-mail");
                        }else {
                            Log.e("tesww", String.valueOf(anError.getErrorCode()));
                            Log.e("teswwwww", "fdfd" + anError.getErrorBody());
                            Log.e("teswwww", "fdfdsasa" + anError.getErrorDetail());
                            requestCallback.requestFailed("Server Error !");
                        }
                    }
                });
    }

    @Override
    public void setPresenter(ProfileEditContract.ProfileEditPresenter presenter) {
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
        usernameET.setText(user.getUsername());
        nameET.setText(user.getName());
        emailET.setText(user.getEmail());
        telpET.setText(user.getTelp());
        addressET.setText(user.getAddress());
        password = user.getPassword();

        if(user.getGender() != null) {
            if (user.getGender().equalsIgnoreCase("female")) {
                femaleRB.setChecked(true);
                gender = "female";
            }

            if (user.getGender().equalsIgnoreCase("male")) {
                maleRB.setChecked(true);
                gender = "male";
            }
        }
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Register Success", Toast.LENGTH_SHORT).show();
        Log.e("tes", "success");
        redirectToProfile();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("tes", "failed");
    }
}
