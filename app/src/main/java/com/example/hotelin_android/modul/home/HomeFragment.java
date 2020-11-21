package com.example.hotelin_android.modul.home;

<<<<<<< Updated upstream
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
=======
import android.os.Bundle;
import android.view.LayoutInflater;
>>>>>>> Stashed changes
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< Updated upstream
import android.widget.TextView;
=======
>>>>>>> Stashed changes

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
<<<<<<< Updated upstream
import com.example.hotelin_android.modul.register.RegisterActivity;
=======
>>>>>>> Stashed changes

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
<<<<<<< Updated upstream
    TextView tvRegister;
=======
>>>>>>> Stashed changes

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
<<<<<<< Updated upstream
        fragmentView = inflater.inflate(R.layout.login_activity, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();

        etUsername = fragmentView.findViewById(R.id.username);
        etPassword = fragmentView.findViewById(R.id.password);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tvRegister = fragmentView.findViewById(R.id.register);

=======
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();

        etUsername = fragmentView.findViewById(R.id.et_username);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btnLogin = fragmentView.findViewById(R.id.bt_login);
>>>>>>> Stashed changes
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

<<<<<<< Updated upstream
        tvRegister.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvRegisterClick();
                return true;
            }
        });


=======
        setTitle("Sign in");
>>>>>>> Stashed changes

        return fragmentView;
    }

<<<<<<< Updated upstream
    public void moveToRegister(HomeContract.View v){
        mPresenter.performMove(v);
    }

=======
>>>>>>> Stashed changes
    public void setBtLoginClick(){
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin();
    }

<<<<<<< Updated upstream
    public void setTvRegisterClick(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

=======
>>>>>>> Stashed changes
    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToList() {

    }
<<<<<<< Updated upstream

    public void redirectToRegister(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }
=======
>>>>>>> Stashed changes
}