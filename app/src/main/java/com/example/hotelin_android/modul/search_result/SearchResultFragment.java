package com.example.hotelin_android.modul.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.example.hotelin_android.modul.room_list.RoomListActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.HotelSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;
import com.example.hotelin_android.util.RecyclerViewAdapter.RecyclerViewAdapterHotelList;

import java.util.List;

public class SearchResultFragment extends BaseFragment<SearchResultActivity, SearchResultContract.Presenter> implements SearchResultContract.View {
    private final TokenSharedUtil tokenSharedUtil;
    private final String hotel_location;
    private RecyclerView mRecyclerView;
    private RelativeLayout rlNoResult;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout loading;
    private final HotelSharedUtil hotelSharedUtil;

    public SearchResultFragment(String hotel_location, TokenSharedUtil tokenSharedUtil) {
        this.hotel_location = hotel_location;
        this.tokenSharedUtil = tokenSharedUtil;
        hotelSharedUtil = UtilProvider.getHotelSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_search_result, container, false);
        mPresenter = new SearchResultPresenter(this);
        mPresenter.start();

        return fragmentView;
    }

    public void setItems(){
        TextView tvTitle;
        RecyclerView.LayoutManager mLayoutManager;

        tvTitle = fragmentView.findViewById(R.id.search_result_title);
        rlNoResult = fragmentView.findViewById(R.id.no_result_layout);
        loading = fragmentView.findViewById(R.id.search_result_loading);

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewHotelList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.getHotelList(hotel_location);

        tvTitle.setText("Hotel di " + hotel_location);

        setTitle("Hasil Pencarian");
    }

    @Override
    public void setPresenter(SearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void redirectToRoomList(int id, String hotel_name){
        Intent intent = new Intent(activity, RoomListActivity.class);
        intent.putExtra("hotel_id", id);
        intent.putExtra("hotel_name", hotel_name);
        startActivity(intent);
    }

    @Override
    public void searchHotel(final String hotel_location, final RequestCallback<SearchResultResponse> requestCallback) {
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
                            requestCallback.requestFailed("Null Response");
                        }else{
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    public void setResult(final List<Hotel> hotels){
        mAdapter = new RecyclerViewAdapterHotelList(hotels);
        mRecyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterHotelList) mAdapter).setOnItemClickListener(new RecyclerViewAdapterHotelList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = hotels.get(position).getId();
                String hotel_name = hotels.get(position).getHotel_name();
                mPresenter.getHotelDetail(id);
                redirectToRoomList(id, hotel_name);
            }
        });
    }

    public void checkResult(){
        if(mAdapter.getItemCount() == 0){
            rlNoResult.setVisibility(View.VISIBLE);
        }
    }

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
                            requestCallback.requestFailed("Null Response");
                        }else{
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    public void saveHotel(Hotel hotel){
        hotelSharedUtil.setHotel(hotel);
    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void startLoading(){
        loading.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void stopLoading(){
        loading.setVisibility(View.GONE);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}