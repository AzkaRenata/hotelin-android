package com.example.hotelin_android.modul.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.login.LoginActivity;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View {
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button btnRegister;
    TextView tvlogin;

    public RegisterFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.register_activity, container, false);
        mPresenter = new RegisterPresenter(this);
        mPresenter.start();

        etUsername = fragmentView.findViewById(R.id.username);
        etEmail = fragmentView.findViewById(R.id.email);
        etPassword = fragmentView.findViewById(R.id.password);
        etConfirmPassword = fragmentView.findViewById(R.id.confirm_password);
        btnRegister = fragmentView.findViewById(R.id.register_btn);
        tvlogin = fragmentView.findViewById(R.id.login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtRegisterClick();
            }
        });

        tvlogin.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvLoginClick();
                return true;
            }
        });

        setTitle("Sign Up");

        return fragmentView;
    }

    public void setBtRegisterClick(){
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin();
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
    public void redirectToList() {

    }
}