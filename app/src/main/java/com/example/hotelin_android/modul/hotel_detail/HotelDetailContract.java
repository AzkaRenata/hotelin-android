package com.example.hotelin_android.modul.hotel_detail;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface HotelDetailContract {
    interface HotelDetailView extends BaseView<HotelDetailPresenter> {
        void setHotelDetailData(HotelDetail hotelDetail);
    }

    interface HotelDetailPresenter extends BasePresenter {
        void fetchHotelDetail(int hotelID, String bearerToken);
    }
}
