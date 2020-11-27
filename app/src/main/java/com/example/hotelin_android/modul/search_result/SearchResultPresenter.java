package com.example.hotelin_android.modul.search_result;

import android.util.Log;

import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.RequestCallback;

import java.util.List;

public class SearchResultPresenter implements SearchResultContract.Presenter{
    private final SearchResultContract.View view;

    public SearchResultPresenter(SearchResultContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void getData(String location){
        view.searchHotel(location, new RequestCallback<List<Hotel>>() {
            @Override
            public void requestSuccess(List<Hotel> data) {
                view.setResult(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}