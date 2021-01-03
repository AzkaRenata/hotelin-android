package com.example.hotelin_android.modul.search_result;

import com.example.hotelin_android.util.RequestCallback;

public class SearchResultPresenter implements SearchResultContract.Presenter{
    private final SearchResultActivity activity;
    private final SearchResultContract.View view;

    public SearchResultPresenter(SearchResultContract.View view, SearchResultActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void start(){
        view.setItems();
    }

    @Override
    public void getHotelList(String location){
        activity.startLoading();
        view.searchHotel(location, new RequestCallback<SearchResultResponse>() {
            @Override
            public void requestSuccess(SearchResultResponse data) {
                view.setResult(data.hotelList);
                view.checkResult();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
        activity.stopLoading();
    }

    @Override
    public void getHotelDetail(int id) {
        activity.startLoading();
        view.requestHotelDetail(id, new RequestCallback<SearchResultResponse>() {
            @Override
            public void requestSuccess(SearchResultResponse data) {
                view.saveHotel(data.hotel);
                activity.stopLoading();
                view.redirectToRoomList();
            }

            @Override
            public void requestFailed(String errorMessage) {
                activity.stopLoading();
                view.showFailedMessage(errorMessage);
            }
        });
    }
}