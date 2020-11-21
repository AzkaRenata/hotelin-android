package com.example.hotelin_android.modul.itemDetails;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface ItemDetailsContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
    }

    interface Presenter extends BasePresenter {
        void performLogin();
    }
}
