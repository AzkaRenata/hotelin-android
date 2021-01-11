package com.example.hotelin_android.modul.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.about_us.AboutUsActivity;
import com.example.hotelin_android.modul.login.LoginActivity;

public class SplashScreenFragment extends BaseFragment<SplashScreenActivity, SplashScreenContract.Presenter> implements SplashScreenContract.View {

    public SplashScreenFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        mPresenter = new SplashScreenPresenter(this);
        mPresenter.start();

        return fragmentView;
    }

    public void setItems(){
        TextView tvAboutUs = fragmentView.findViewById(R.id.about_us_tv);
        RelativeLayout rlSplash = fragmentView.findViewById(R.id.splash_layout);

        rlSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRlSplashClick();
            }
        });

        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTvAboutUsClick();
            }
        });
    }

    public void setRlSplashClick() {
        Log.e("tes", "tes");
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    public void setTvAboutUsClick() {
        Intent intent = new Intent(activity, AboutUsActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(SplashScreenContract.Presenter presenter) {
        mPresenter = presenter;
    }
}