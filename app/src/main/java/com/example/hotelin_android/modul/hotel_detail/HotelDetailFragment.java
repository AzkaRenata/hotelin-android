package com.example.hotelin_android.modul.hotel_detail;

import android.content.Intent;
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

public class HotelDetailFragment extends BaseFragment<HotelDetailActivity, HotelDetailContract.HotelDetailPresenter> implements HotelDetailContract.HotelDetailView {
    private TextView tvHotelName;
    private TextView tvHotelLocation;
    private TextView tvHotelDescription;
    private TextView tvNoFacility;
    private TextView tvPrice;
    private ImageView ivHotelPicture;
    private ImageView ivFacWifi;
    private ImageView ivFacBreakfast;
    private ImageView ivFacAC;
    private ImageView ivFacTV;

    private final HotelSharedUtil hotelSharedUtil;

    public HotelDetailFragment() {
        hotelSharedUtil = UtilProvider.getHotelSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_hotel_detail, container, false);
        mPresenter = new HotelDetailPresenter(this);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems() {
        Button btnSelect;

        tvHotelName = fragmentView.findViewById(R.id.hotel_detail_name_tv);
        tvHotelLocation = fragmentView.findViewById(R.id.hotel_detail_location_tv);
        tvHotelDescription = fragmentView.findViewById(R.id.hotel_detail_description_tv);
        tvPrice = fragmentView.findViewById(R.id.hotel_detail_price_tv);
        ivHotelPicture = fragmentView.findViewById(R.id.detail_hotel_pic);
        tvNoFacility = fragmentView.findViewById(R.id.hotel_detail_no_facility_tv);
        ivFacWifi = fragmentView.findViewById(R.id.hotel_detail_fac_wifi_tv);
        ivFacBreakfast = fragmentView.findViewById(R.id.hotel_detail_fac_breakfast_tv);
        ivFacAC = fragmentView.findViewById(R.id.hotel_detail_fac_ac_tv);
        ivFacTV = fragmentView.findViewById(R.id.hotel_detail_fac_tv_tv);
        btnSelect = fragmentView.findViewById(R.id.hotel_detail_select_btn);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnSelectClick();
            }
        });

        setTitle(getString(R.string.hotel_detail_title));
    }

    public void setBtnSelectClick(){
        mPresenter.moveToRoomList();
    }

    @Override
    public void redirectToRoomList() {
        Intent intent = new Intent(activity, RoomListActivity.class);
        startActivity(intent);
    }

    @Override
    public void setHotelDetail() {
        String price;
        List<Facility> facility;

        DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        kurs.setDecimalFormatSymbols(formatRp);

        price = kurs.format(hotelSharedUtil.getHotel().getHotel_price());
        facility = hotelSharedUtil.getHotel().getFacility();

        tvHotelName.setText(hotelSharedUtil.getHotel().getHotel_name());
        tvHotelLocation.setText(hotelSharedUtil.getHotel().getHotel_location());
        tvHotelDescription.setText(hotelSharedUtil.getHotel().getHotel_desc());
        tvPrice.setText(price);

        if(hotelSharedUtil.getHotel().getHotel_picture() != null){
            String url = myURL.getImageUrl() + hotelSharedUtil.getHotel().getHotel_picture();
            new AsyncTaskLoadImage(ivHotelPicture).execute(url);
        }

        for(int i = 0; i < facility.size(); i++){
            if(facility.get(i).getFacility_name().equalsIgnoreCase("free wifi")){
                tvNoFacility.setVisibility(View.GONE);
                ivFacWifi.setVisibility(View.VISIBLE);
            }

            if(facility.get(i).getFacility_name().equalsIgnoreCase("sarapan")){
                tvNoFacility.setVisibility(View.GONE);
                ivFacBreakfast.setVisibility(View.VISIBLE);
            }

            if(facility.get(i).getFacility_name().equalsIgnoreCase("ac")){
                tvNoFacility.setVisibility(View.GONE);
                ivFacAC.setVisibility(View.VISIBLE);
            }

            if(facility.get(i).getFacility_name().equalsIgnoreCase("tv")){
                tvNoFacility.setVisibility(View.GONE);
                ivFacTV.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setPresenter(HotelDetailContract.HotelDetailPresenter presenter) {
        this.mPresenter = presenter;
    }
}
