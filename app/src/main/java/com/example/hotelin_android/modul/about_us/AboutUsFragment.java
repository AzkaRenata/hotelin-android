package com.example.hotelin_android.modul.about_us;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Facility;
import com.example.hotelin_android.modul.room_list.RoomListActivity;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.SharedPreferences.HotelSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutUsFragment extends BaseFragment<AboutUsActivity, AboutUsContract.HotelDetailPresenter> implements AboutUsContract.HotelDetailView {
    private CircleImageView civAziz;

    public AboutUsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_about_us, container, false);
        mPresenter = new AboutUsPresenter(this);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems() {
        civAziz = fragmentView.findViewById(R.id.aziz_civ);
    }

    @Override
    public void setPictures() {
    }

    @Override
    public void setPresenter(AboutUsContract.HotelDetailPresenter presenter) {
        this.mPresenter = presenter;
    }
}
