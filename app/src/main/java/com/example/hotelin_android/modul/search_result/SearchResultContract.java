package com.example.hotelin_android.modul.search_result;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public interface SearchResultContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void redirectToRoomList();
        void setResult(List<Hotel> data);
        void checkResult();
        void saveHotel(Hotel hotel);
        void requestHotelSearch(String location, final RequestCallback<SearchResultResponse> requestCallback);
        void requestHotelDetail(final int id, final RequestCallback<SearchResultResponse> requestCallback);
    }

    interface Presenter extends BasePresenter {
        void performHotelSearch(String location);
        void getHotelDetail(int id);
    }
}
