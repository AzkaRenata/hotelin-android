package com.example.hotelin_android.modul.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.modul.register.RegisterActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    EditText etEmail;
    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    TextInputEditText etPassword;
    Button btnLogin;
    TextView tvRegister;
    String authToken;
    SharedPreferencesUtil sharedPreferencesUtil;

    public LoginFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.login_activity, container, false);
        mPresenter = new LoginPresenter(this, sharedPreferencesUtil);
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.login_email_et);
        etPassword = fragmentView.findViewById(R.id.login_password_tiet);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tvRegister = fragmentView.findViewById(R.id.register);
        tilEmail = fragmentView.findViewById(R.id.login_email_til);
        tilPassword = fragmentView.findViewById(R.id.login_password_til);

        tilEmail.setHintEnabled(false);
        tilPassword.setHintEnabled(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        tvRegister.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvRegisterClick();
                return true;
            }
        });

        return fragmentView;
    }

    private boolean validateLogin() {
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        mAwesomeValidation.addValidation(activity, R.id.login_email_et, Patterns.EMAIL_ADDRESS, R.string.error_email_valid);
        mAwesomeValidation.addValidation(activity, R.id.login_email_et, Patterns.EMAIL_ADDRESS, R.string.error_email_empty);
        mAwesomeValidation.addValidation(activity, R.id.login_password_tiet, RegexTemplate.NOT_EMPTY, R.string.error_password_empty);
        return mAwesomeValidation.validate();
    }

    public void setBtLoginClick() {
        if (validateLogin()) {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            mPresenter.performLogin(email, password);
        }
    }

    public void setTvRegisterClick() {
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
//        activity.finish();
        startActivity(intent);
    }

    public void redirectToRegister() {

    }

    public void requestLogin(final String email, String password, final RequestCallback<LoginResponse> requestCallback) {
        Log.e("tes", "tes");
        AndroidNetworking.post(myURL.LOGIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        //Log.e("tes", "tes2");
                        Log.e("tes", email);
                        if (response == null) {
                            Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                            requestCallback.requestFailed("Null Response");
                        } else if (response.token == null) {
                            Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                            requestCallback.requestFailed("Wrong Email or Password");
                        } else {
                            Toast.makeText(getContext(), email, Toast.LENGTH_SHORT).show();
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tesww", String.valueOf(anError.getErrorCode()));
                        Log.e("teswwwww", "fdfd" + anError.getErrorBody());
                        Log.e("teswwww", "fdfdsasa" + anError.getErrorDetail());
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        requestCallback.requestFailed("Wrong Email or Password");
                    }
                });
    }

    public void saveToken(String token) {
        Log.e("tes555", token);
        sharedPreferencesUtil.setToken(token);
    }

    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}