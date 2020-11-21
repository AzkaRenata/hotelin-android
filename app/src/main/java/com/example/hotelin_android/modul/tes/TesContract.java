package com.example.hotelin_android.modul.tes;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface TesContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
    }

    interface Presenter extends BasePresenter {
        void performLogin();
    }
}
