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

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.home_activity, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();


        setTitle("Hotelin");
        return fragmentView;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }
}