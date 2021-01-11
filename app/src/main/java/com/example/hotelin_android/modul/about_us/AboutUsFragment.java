package com.example.hotelin_android.modul.about_us;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;

public class AboutUsFragment extends BaseFragment<AboutUsActivity, AboutUsContract.HotelDetailPresenter> implements AboutUsContract.HotelDetailView {

    public AboutUsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_about_us, container, false);
        mPresenter = new AboutUsPresenter(this);
        mPresenter.start();

        setTitle("About Us");

        return fragmentView;
    }

    @Override
    public void setPresenter(AboutUsContract.HotelDetailPresenter presenter) {
        this.mPresenter = presenter;
    }
}
