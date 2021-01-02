package com.example.hotelin_android.modul.search_result;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;
import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public interface SearchResultContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void startLoading();
        void stopLoading();
        void redirectToHome();
        void setResult(List<Hotel> data);
        void checkResult();
        void searchHotel(String location, final RequestCallback<SearchResultResponse> requestCallback);
        void showFailedMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String location);
    }
}
