package com.example.hotelin_android.modul.booking_history;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.hotelin_android.model.Bookinghistory;
import com.example.hotelin_android.modul.cancel_detail.CancelDetailActivity;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.util.RecyclerViewAdapter.RecyclerViewAdapterBookingList;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.myURL;

import java.util.List;

public class BookingHistoryFragment extends BaseFragment<BookingHistoryActivity, BookingHistoryContract.Presenter> implements BookingHistoryContract.View {
    TokenSharedUtil tokenSharedUtil;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  TextView ongoing_tv;
    private  TextView done_tv;
    private  TextView cancel_tv;

    public BookingHistoryFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.my_booking_history, container, false);
        mPresenter = new BookingHistoryPresenter(this);
        mPresenter.start();

        ongoing_tv = fragmentView.findViewById(R.id.booking_history_ongoing_btn);
        done_tv = fragmentView.findViewById(R.id.booking_history_done_btn);
        cancel_tv = fragmentView.findViewById(R.id.booking_history_cancel_btn);

        ongoing_tv.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setOngoingClick();
                return true;
            }
        });

        done_tv.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setDoneClick();
                return true;
            }
        });

        cancel_tv.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setCancelClick();
                return true;
            }
        });

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewHistoryBooking);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.getData("1");
        setTitle("Booking History");
        return fragmentView;
    }

    public void setBtLoginClick(){

    }

    public void setOngoingClick(){
        ongoing_tv.setTextColor(0xFF03DAC5);
        done_tv.setTextColor(0xFF4F4F50);
        cancel_tv.setTextColor(0xFF4F4F50);
        mPresenter.getData("1");
        mAdapter.notifyDataSetChanged();
    }

    public void setDoneClick(){
        done_tv.setTextColor(0xFF03DAC5);
        ongoing_tv.setTextColor(0xFF4F4F50);
        cancel_tv.setTextColor(0xFF4F4F50);
        mPresenter.getData("2");
        mAdapter.notifyDataSetChanged();
    }

    public void setCancelClick(){
        cancel_tv.setTextColor(0xFF03DAC5);
        ongoing_tv.setTextColor(0xFF4F4F50);
        done_tv.setTextColor(0xFF4F4F50);
        mPresenter.getData("3");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(BookingHistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
    }

    public void redirectToCancelDetail(int id, int booking_status){
        Intent intent = new Intent(activity, CancelDetailActivity.class);
        intent.putExtra("booking_id", id);
        intent.putExtra("booking_status", booking_status);
        startActivity(intent);
    }

    public void saveToken(String token){
        tokenSharedUtil.setToken(token);
    }

    @Override
    public void searchBooking(String status_id, final RequestCallback<List<Bookinghistory>> requestCallback) {
        AndroidNetworking.get(myURL.BOOKING_HISTORY_URL+status_id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Bookinghistory.class, new ParsedRequestListener<List<Bookinghistory>>() {
                    @Override
                    public void onResponse(List<Bookinghistory> response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }else{
                            requestCallback.requestSuccess(response,"tes" );
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void setResult(final List<Bookinghistory> data){
        mAdapter = new RecyclerViewAdapterBookingList(data);
        mRecyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterBookingList) mAdapter).setOnItemClickListener(new RecyclerViewAdapterBookingList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = data.get(position).getId();
                int booking_status = data.get(position).getBooking_status();
                redirectToCancelDetail(id, booking_status);
            }
        });

    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}
