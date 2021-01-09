package com.example.hotelin_android.modul.home;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void setItems();
        void redirectToSearchResult(String location);
    }

    interface Presenter extends BasePresenter {
        void performSearch(String location);
    }
}
