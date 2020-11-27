package com.example.hotelin_android.modul.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.modul.login.LoginActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.URL;

import static com.example.hotelin_android.R.id.*;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View {
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    EditText etFullname;
    EditText etTelp;
    EditText etAddress;
    String gender;
    RadioGroup rgGender;
    RadioButton rbGenderMale;
    RadioButton rbGenderFemale;
    Button btnRegister;
    TextView tvLogin;
    SharedPreferencesUtil sharedPreferencesUtil;

    public RegisterFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.register_activity, container, false);
        mPresenter = new RegisterPresenter(this);
        mPresenter.start();

        etUsername = fragmentView.findViewById(usernameR);
        etEmail = fragmentView.findViewById(emailR);
        etPassword = fragmentView.findViewById(passwordR);
        etConfirmPassword = fragmentView.findViewById(confirm_passwordR);
        etFullname = fragmentView.findViewById(fullnameR);
        etTelp = fragmentView.findViewById(telpR);
        etAddress = fragmentView.findViewById(addressR);
        rgGender = fragmentView.findViewById(genderR);
        rbGenderMale = fragmentView.findViewById(radio_maleR);
        rbGenderFemale = fragmentView.findViewById(radio_femaleR);
        btnRegister = fragmentView.findViewById(register_btnR);
        tvLogin = fragmentView.findViewById(login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tes", "tes1");
                setBtRegisterClick();
            }
        });

        tvLogin.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvLoginClick();
                return true;
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkGender(group, checkedId);
            }
        });

        return fragmentView;
    }

    public void checkGender(RadioGroup group, int checkedId){
        switch (checkedId){
            case radio_maleR:
                gender = "male";
                Log.e("tes", gender);
                break;
            case radio_femaleR:
                gender = "female";
                Log.e("tes", gender);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + checkedId);
        }
    }

    public void setBtRegisterClick(){
        Log.e("tes", "tes2");
        String fullname = etFullname.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String telp = etTelp.getText().toString();
        String address = etAddress.getText().toString();

        User newUser = new User(username, fullname, email, password, 2, gender, telp,address, null);

        Log.e("set", newUser.getName());
        Log.e("set", newUser.getUsername());
        Log.e("set", newUser.getPassword());
        Log.e("set", newUser.getEmail());
        Log.e("set", newUser.getGender());
        Log.e("set", newUser.getTelp());
        Log.e("set", newUser.getAddress());

        Log.e("tes", "tes3");

        mPresenter.performRegister(newUser);
    }

    public void setTvLoginClick(){
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestRegister(final User newUser, final RequestCallback<RegisterResponse> requestCallback) {
        Log.e("tes", URL.CUSTOMER_REGISTER_URL);
        Log.e("tes", newUser.getName());
        String username = "Username";
        AndroidNetworking.post(URL.CUSTOMER_REGISTER_URL)
                .addBodyParameter("username", newUser.getUsername())
                .addBodyParameter("name", newUser.getName())
                .addBodyParameter("email", newUser.getEmail())
                .addBodyParameter("password", newUser.getPassword())
                .addBodyParameter("gender", newUser.getGender())
                .addBodyParameter("telp", newUser.getTelp())
                .addBodyParameter("address", newUser.getAddress())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {

                    @Override
                    public void onResponse(RegisterResponse response) {
                        if(response == null){
                            Log.e("tes", URL.CUSTOMER_REGISTER_URL);
                            requestCallback.requestFailed("Null Response");
                        }else {
                            Log.e("tes", URL.CUSTOMER_REGISTER_URL);
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 401) {
                            Log.e("tes", "ERROR", anError);
                            requestCallback.requestFailed("Please input a valid e-mail");
                        }else {
                            Log.e("tes", String.valueOf(anError.getErrorCode()));
                            Log.e("tes", anError.getErrorBody());
                            Log.e("tes", anError.getErrorDetail());
                            Log.e("tesuser", newUser.getUsername());
                            Log.e("tesemail", newUser.getEmail());
                            Log.e("tespass", newUser.getPassword());
                            requestCallback.requestFailed("Server Error !");
                        }
                    }
                });
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Register Success", Toast.LENGTH_SHORT).show();
        Log.e("tes", "success");
        redirectToLogin();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("tes", "failed");
    }
}