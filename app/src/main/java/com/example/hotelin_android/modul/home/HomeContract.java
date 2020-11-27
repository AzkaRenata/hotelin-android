package com.example.hotelin_android.modul.home;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void redirectToSearchResult(String location);
    }

    interface Presenter extends BasePresenter {
        void search(String location);
    }
}
