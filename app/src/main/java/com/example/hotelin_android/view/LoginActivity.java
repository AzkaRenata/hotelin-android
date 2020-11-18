package com.example.hotelin_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelin_android.contract.LoginContract;
import com.example.hotelin_android.databinding.LoginActivityBinding;
import com.example.hotelin_android.interactor.LoginInteractor;
import com.example.hotelin_android.presenter.LoginPresenter;
import com.example.hotelin_android.util.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter presenter;
    private LoginActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this, new LoginInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        binding.loginBtn.setOnClickListener(this);
    }

    @Override
    public void startLoading() {
//        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
//        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess() {
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.loginBtn.getId()){
            onButtonLoginClick();
        }
    }

    public void onButtonLoginClick(){
        presenter.login(binding.username.getText().toString(), binding.password.getText().toString());
    }
}
