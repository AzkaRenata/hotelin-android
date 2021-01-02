package com.example.hotelin_android.modul.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.search_result.SearchResultActivity;

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {
    private EditText etSearchBar;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems() {
        Button btSearch;

        etSearchBar = fragmentView.findViewById(R.id.search);
        btSearch = fragmentView.findViewById(R.id.search_btn);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSearchClick();
            }
        });

        setTitle("Hotelin");
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setBtSearchClick(){
        String location = etSearchBar.getText().toString();
        mPresenter.search(location);
    }
    public void redirectToSearchResult(String location){
        Intent intent = new Intent(activity, SearchResultActivity.class);
        intent.putExtra("hotel_location", location);
        startActivity(intent);
    }
}