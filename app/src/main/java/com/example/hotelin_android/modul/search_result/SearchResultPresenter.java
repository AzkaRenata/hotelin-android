package com.example.hotelin_android.modul.search_result;

import com.example.hotelin_android.util.RequestCallback;

public class SearchResultPresenter implements SearchResultContract.Presenter{
    private final SearchResultContract.View view;

    public SearchResultPresenter(SearchResultContract.View view) {
        this.view = view;
    }

    @Override
    public void start(){
        view.setItems();
    }

    @Override
    public void getHotelList(String location){
        view.searchHotel(location, new RequestCallback<SearchResultResponse>() {
            @Override
            public void requestSuccess(SearchResultResponse data) {
                view.startLoading();
                view.setResult(data.hotelList);
                view.checkResult();
                //view.stopLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }

    @Override
    public void getHotelDetail(int id) {
        view.requestHotelDetail(id, new RequestCallback<SearchResultResponse>() {
            @Override
            public void requestSuccess(SearchResultResponse data) {
                view.startLoading();
                view.saveHotel(data.hotel);
                view.stopLoading();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}