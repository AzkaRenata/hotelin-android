package com.example.hotelin_android.modul.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.base.BaseFragmentHolderActivity;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.modul.search_result.SearchResultActivity;

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {
    EditText etSearchBar;
    Button btSearch;

    public HomeFragment() {
    }

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
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSearchClick();
            }
        });
        return fragmentView;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }

    public void setBtSearchClick(){
        String location = etSearchBar.getText().toString();
        Log.d("hotel_location", location);
        mPresenter.search(location);
    }

    public void redirectToSearchResult(String location){
        Intent intent = new Intent(activity, SearchResultActivity.class);
        intent.putExtra("hotel_location", location);
        startActivity(intent);
    }

    public void moveToSearch(BaseFragmentHolderActivity activity){
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
    }

    public void moveToBooking(BaseFragmentHolderActivity activity){
        Log.e("PINDAH", "PINDAH PO O");
        Intent intent = new Intent(activity, BookingHistoryActivity.class);
        startActivity(intent);
    }

    public void moveToProfile(BaseFragmentHolderActivity activity){
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
    }
}