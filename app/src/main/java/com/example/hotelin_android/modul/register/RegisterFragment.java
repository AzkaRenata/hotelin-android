package com.example.hotelin_android.modul.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.login.LoginActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.myURL;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.hotelin_android.R.id.*;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View {
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etFullname;
    private EditText etTelp;
    private EditText etAddress;
    private String gender;

    public RegisterFragment() {
        gender = "male";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    public void setItems(){
        TextInputLayout tilPassword;
        TextInputLayout tilConfirmPassword;

        RadioGroup rgGender;
        Button btnRegister;
        TextView tvLogin;

        etUsername = fragmentView.findViewById(usernameR);
        etEmail = fragmentView.findViewById(emailR);
        etPassword = fragmentView.findViewById(passwordR);
        etConfirmPassword = fragmentView.findViewById(confirm_passwordR);
        etFullname = fragmentView.findViewById(fullnameR);
        etTelp = fragmentView.findViewById(telpR);
        etAddress = fragmentView.findViewById(addressR);

        rgGender = fragmentView.findViewById(genderR);
        btnRegister = fragmentView.findViewById(register_btnR);
        tvLogin = fragmentView.findViewById(login);
        tilPassword = fragmentView.findViewById(register_password_til);
        tilConfirmPassword = fragmentView.findViewById(register_confirm_password_til);

        tilPassword.setHintEnabled(false);
        tilConfirmPassword.setHintEnabled(false);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtRegisterClick();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTvLoginClick();
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkGender(checkedId);
            }
        });
    }

    public void setBtRegisterClick(){
        if(validateForm()){
            String name = etFullname.getText().toString();
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String telp = etTelp.getText().toString();
            String address = etAddress.getText().toString();
            User user = new User(username, name, email, gender, telp, address);

            mPresenter.performRegister(user);
        }
    }

    public void setTvLoginClick(){
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    public void checkGender(int checkedId){
        switch (checkedId){
            case radio_maleR:
                gender = "male";
                break;
            case radio_femaleR:
                gender = "female";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + checkedId);
        }
    }

    public boolean validateForm() {
        AwesomeValidation emptyValidation = new AwesomeValidation(ValidationStyle.BASIC);
        emptyValidation.addValidation(activity, usernameR, RegexTemplate.NOT_EMPTY, R.string.error_username_empty);
        emptyValidation.addValidation(activity, fullnameR, RegexTemplate.NOT_EMPTY, R.string.error_name_empty);
        emptyValidation.addValidation(activity, emailR, RegexTemplate.NOT_EMPTY, R.string.error_email_empty);
        emptyValidation.addValidation(activity, passwordR, RegexTemplate.NOT_EMPTY, R.string.error_password_empty);

        return emptyValidation.validate();
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestRegister(final User user, final RequestCallback<RegisterResponse> requestCallback) {
        String password = etPassword.getText().toString();
        String confirm_password = etConfirmPassword.getText().toString();
        AndroidNetworking.post(myURL.REGISTER_URL)
                .addBodyParameter("username", user.getUsername())
                .addBodyParameter("name", user.getName())
                .addBodyParameter("email", user.getEmail())
                .addBodyParameter("password", password)
                .addBodyParameter("password_confirmation", confirm_password)
                .addBodyParameter("gender", user.getGender())
                .addBodyParameter("telp", user.getTelp())
                .addBodyParameter("address", user.getAddress())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {
                    @Override
                    public void onResponse(RegisterResponse response) {
                        if(response == null){
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        }else {
                            requestCallback.requestSuccess(response, getString(R.string.register_success_message));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 400) {
                            String error = anError.getResponse().header("error");
                            requestCallback.requestFailed(error);
                        }else{
                            requestCallback.requestFailed(getString(R.string.error_database));
                        }
                    }
                });
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }
}