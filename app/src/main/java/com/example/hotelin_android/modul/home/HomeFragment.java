package com.example.hotelin_android.modul.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {
    EditText etSearchBar;
    Button btSearch;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.home_activity, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();

        etSearchBar = fragmentView.findViewById(R.id.search);
        btSearch = fragmentView.findViewById(R.id.search_btn);

        setTitle("Hotelin");
        return fragmentView;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }
}