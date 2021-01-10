package com.example.hotelin_android.modul.booking_history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.BookingHistory;
import com.example.hotelin_android.modul.booking_detail.BookingDetailActivity;
import com.example.hotelin_android.util.RecyclerViewAdapter.RecyclerViewAdapterBookingList;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;

import java.util.List;

public class BookingHistoryFragment extends BaseFragment<BookingHistoryActivity, BookingHistoryContract.Presenter> implements BookingHistoryContract.View {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private TextView tvOngoing;
    private TextView tvDone;
    private TextView tvCanceled;

    TokenSharedUtil tokenSharedUtil;

    public BookingHistoryFragment() {
        this.tokenSharedUtil = UtilProvider.getTokenSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_booking_history, container, false);
        mPresenter = new BookingHistoryPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems() {
        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewHistoryBooking);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        tvOngoing = fragmentView.findViewById(R.id.booking_history_ongoing_btn);
        tvDone = fragmentView.findViewById(R.id.booking_history_done_btn);
        tvCanceled = fragmentView.findViewById(R.id.booking_history_cancel_btn);

        tvOngoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOngoingClick();
            }
        });

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDoneClick();
            }
        });

        tvCanceled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCancelClick();
            }
        });

        setTitle(getString(R.string.booking_history_title));
    }

    public void setOngoingClick(){
        tvOngoing.setTextColor(getResources().getColor(R.color.hotelinPrimary));
        tvDone.setTextColor(getResources().getColor(R.color.hotelinText));
        tvCanceled.setTextColor(getResources().getColor(R.color.hotelinText));
        mPresenter.getData("1");
        mAdapter.notifyDataSetChanged();
    }

    public void setDoneClick(){
        tvDone.setTextColor(getResources().getColor(R.color.hotelinPrimary));
        tvOngoing.setTextColor(getResources().getColor(R.color.hotelinText));
        tvCanceled.setTextColor(getResources().getColor(R.color.hotelinText));
        mPresenter.getData("2");
        mAdapter.notifyDataSetChanged();
    }

    public void setCancelClick(){
        tvCanceled.setTextColor(getResources().getColor(R.color.hotelinPrimary));
        tvOngoing.setTextColor(getResources().getColor(R.color.hotelinText));
        tvDone.setTextColor(getResources().getColor(R.color.hotelinText));
        mPresenter.getData("3");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(BookingHistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void redirectToCancelDetail(int id){
        Intent intent = new Intent(activity, BookingDetailActivity.class);
        intent.putExtra("booking_id", id);
        startActivity(intent);
    }

    @Override
    public void searchBooking(String status_id, final RequestCallback<BookingHistoryResponse> requestCallback) {
        AndroidNetworking.get(myURL.BOOKING_HISTORY_URL + status_id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(BookingHistoryResponse.class, new ParsedRequestListener<BookingHistoryResponse>() {
                    @Override
                    public void onResponse(BookingHistoryResponse response) {
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

    public void setResult(final List<BookingHistory> data){
        mAdapter = new RecyclerViewAdapterBookingList(data);
        mRecyclerView.setAdapter(mAdapter);

        ((RecyclerViewAdapterBookingList) mAdapter).setOnItemClickListener(new RecyclerViewAdapterBookingList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = data.get(position).getId();
                redirectToCancelDetail(id);
            }
        });

    }
}
