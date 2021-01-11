package com.example.hotelin_android.modul.about_us;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface AboutUsContract {
    interface HotelDetailView extends BaseView<HotelDetailPresenter> {
        void setItems();
        void setPictures();
    }

    interface HotelDetailPresenter extends BasePresenter {
    }
}
