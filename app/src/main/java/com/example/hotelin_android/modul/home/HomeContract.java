package com.example.hotelin_android.modul.home;

import com.example.hotelin_android.base.BasePresenter;
import com.example.hotelin_android.base.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void redirectToList();
<<<<<<< Updated upstream
        void redirectToRegister();
=======
>>>>>>> Stashed changes
    }

    interface Presenter extends BasePresenter {
        void performLogin();
<<<<<<< Updated upstream
        void performMove(View v);
=======
>>>>>>> Stashed changes
    }
}
