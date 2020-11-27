package com.example.hotelin_android.modul.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.modul.register.RegisterActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;
import com.example.hotelin_android.util.RecyclerViewAdapterHotelList;

import java.util.List;

public class SearchResultFragment extends BaseFragment<SearchResultActivity, SearchResultContract.Presenter> implements SearchResultContract.View {
    SharedPreferencesUtil sharedPreferencesUtil;
    String hotel_location;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SearchResultFragment(String hotel_location, SharedPreferencesUtil sharedPreferencesUtil) {
        this.hotel_location = hotel_location;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.see_all_activity, container, false);
        mPresenter = new SearchResultPresenter(this);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewHotelList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.getData(hotel_location);

        return fragmentView;
    }

    public void setBtLoginClick(){

    }

    public void setTvRegisterClick(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(SearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
    }

    public void redirectToRegister(){

    }

    public void saveToken(String token){
        sharedPreferencesUtil.setToken(token);
    }

    @Override
    public void searchHotel(final String hotel_location, final RequestCallback<List<Hotel>> requestCallback) {
        AndroidNetworking.get(myURL.SEARCH_HOTEL_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addQueryParameter("hotel_location", hotel_location)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Hotel.class, new ParsedRequestListener<List<Hotel>>() {
                    @Override
                    public void onResponse(List<Hotel> response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }else{
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void setResult(List<Hotel> hotels){
        mAdapter = new RecyclerViewAdapterHotelList(hotels);
        mRecyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterHotelList) mAdapter).setOnItemClickListener(new RecyclerViewAdapterHotelList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //String id = data.get(position).getId();
                //editTask(id);
            }
        });

    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}