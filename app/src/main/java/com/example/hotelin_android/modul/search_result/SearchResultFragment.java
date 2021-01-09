package com.example.hotelin_android.modul.search_result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.modul.room_list.RoomListActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.HotelSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;
import com.example.hotelin_android.util.RecyclerViewAdapter.RecyclerViewAdapterHotelList;

import java.util.List;

public class SearchResultFragment extends BaseFragment<SearchResultActivity, SearchResultContract.Presenter> implements SearchResultContract.View {
    private RecyclerView mRecyclerView;
    private RelativeLayout rlNoResult;
    private Adapter mAdapter;

    private final String hotel_location;

    private final TokenSharedUtil tokenSharedUtil;
    private final HotelSharedUtil hotelSharedUtil;

    public SearchResultFragment(String hotel_location) {
        this.hotel_location = hotel_location;
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        hotelSharedUtil = UtilProvider.getHotelSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_search_result, container, false);
        mPresenter = new SearchResultPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @SuppressLint("SetTextI18n")
    public void setItems(){
        TextView tvTitle;
        RecyclerView.LayoutManager mLayoutManager;

        tvTitle = fragmentView.findViewById(R.id.search_result_title);
        rlNoResult = fragmentView.findViewById(R.id.no_result_layout);

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewHotelList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.performHotelSearch(hotel_location);

        tvTitle.setText("Hotel di " + hotel_location);

        setTitle(getString(R.string.search_result_toolbar_title));
    }

    @Override
    public void redirectToRoomList(){
        Intent intent = new Intent(activity, RoomListActivity.class);
        startActivity(intent);
    }

    @Override
    public void setResult(final List<Hotel> hotels){
        mAdapter = new RecyclerViewAdapterHotelList(hotels);
        mRecyclerView.setAdapter(mAdapter);

        ((RecyclerViewAdapterHotelList) mAdapter).setOnItemClickListener(new RecyclerViewAdapterHotelList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = hotels.get(position).getId();
                mPresenter.getHotelDetail(id);
            }
        });
    }

    @Override
    public void checkResult(){
        if(mAdapter.getItemCount() == 0){
            rlNoResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void requestHotelSearch(final String hotel_location, final RequestCallback<SearchResultResponse> requestCallback) {
        AndroidNetworking.get(myURL.SEARCH_HOTEL_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addQueryParameter("hotel_location", hotel_location)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(SearchResultResponse.class, new ParsedRequestListener<SearchResultResponse>() {
                    @Override
                    public void onResponse(SearchResultResponse response) {
                        if(response == null){
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        }else{
                            requestCallback.requestSuccess(response,getString(R.string.success_message));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void requestHotelDetail(final int id, final RequestCallback<SearchResultResponse> requestCallback) {
        AndroidNetworking.get(myURL.GET_HOTEL_DETAIL_URL + id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(SearchResultResponse.class, new ParsedRequestListener<SearchResultResponse>() {
                    @Override
                    public void onResponse(SearchResultResponse response) {
                        if(response == null){
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        }else{
                            requestCallback.requestSuccess(response, getString(R.string.success_message));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void saveHotel(Hotel hotel){
        hotelSharedUtil.setHotel(hotel);
    }

    @Override
    public void setPresenter(SearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }
}