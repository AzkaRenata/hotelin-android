package com.example.hotelin_android.modul.hotel_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;

public class HotelDetailFragment extends BaseFragment<HotelDetailActivity, HotelDetailContract.HotelDetailPresenter> implements HotelDetailContract.HotelDetailView {
    TokenSharedUtil tokenSharedUtil;

    TextView hotelNameTV;
    TextView hotelLocationTV;
    TextView hotelDescTV;
//    ImageView hotelImageIV;

    HotelDetailPresenter hotelDetailPresenter;
    int HOTEL_ID =  4;

    public HotelDetailFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail_activity, container, false);

        hotelNameTV = view.findViewById(R.id.nama);
        hotelLocationTV = view.findViewById(R.id.location_tv);
        hotelDescTV = view.findViewById(R.id.desc_tv);
//        hotelImageIV = view.findViewById(R.id.main_image_iv);

        hotelDetailPresenter = new HotelDetailPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);

        hotelDetailPresenter.fetchHotelDetail(HOTEL_ID, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3Q6ODAwMFwvYXBpXC91c2VyXC9sb2dpbiIsImlhdCI6MTYwNjQwNzEzNiwiZXhwIjoxNjA2NDEwNzM2LCJuYmYiOjE2MDY0MDcxMzYsImp0aSI6IktkWmZjWHZNTjNKWEJmVUQiLCJzdWIiOjMsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.7TkZ-VuMxTievvoiW1NLxRa7RI8I-MgTo0am0QRXA-c");
    }

    @Override
    public void setHotelDetailData(HotelDetail hotelDetail) {
        hotelNameTV.setText(hotelDetail.getHotel_name());
        hotelLocationTV.setText(hotelDetail.getHotel_location());
        hotelDescTV.setText(hotelDetail.getHotel_desc());

//         Glide.with(activity).load(URL.BASE_URL + hotelDetail.hotel_picture).into(hotelImageIV);
    }

    @Override
    public void setPresenter(HotelDetailContract.HotelDetailPresenter presenter) {

    }
}
