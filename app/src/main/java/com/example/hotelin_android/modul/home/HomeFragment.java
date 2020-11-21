package com.example.hotelin_android.modul.home;

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
import com.example.hotelin_android.modul.register.RegisterActivity;

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.login_activity, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();

        etUsername = fragmentView.findViewById(R.id.username);
        etPassword = fragmentView.findViewById(R.id.password);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tvRegister = fragmentView.findViewById(R.id.register);

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

    public void moveToRegister(HomeContract.View v){
        mPresenter.performMove(v);
    }

    public void setBtLoginClick(){
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin();
    }

    public void setTvRegisterClick(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToList() {

    }

    public void redirectToRegister(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }
}