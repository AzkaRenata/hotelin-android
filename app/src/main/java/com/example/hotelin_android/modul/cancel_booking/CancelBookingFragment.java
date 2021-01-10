package com.example.hotelin_android.modul.cancel_booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.booking_detail.BookingDetailActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;

public class CancelBookingFragment extends BaseFragment<CancelBookingActivity, CancelBookingContract.Presenter> implements CancelBookingContract.View{
    private final int booking_id;

    private final TokenSharedUtil tokenSharedUtil;

    public CancelBookingFragment(int booking_id) {
        this.booking_id = booking_id;

        this.tokenSharedUtil = UtilProvider.getTokenSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_cancel_booking, container, false);
        mPresenter = new CancelBookingPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems() {
        Button btnCancel = fragmentView.findViewById(R.id.cancel_booking_cancel_btn);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnCancelClick();
            }
        });

        setTitle(getString(R.string.cancel_booking_title));
    }

    public void setBtnCancelClick(){
        mPresenter.performCancelBooking();
    }

    @Override
    public void redirectToBookingDetail() {
        Intent intent = new Intent(activity, BookingDetailActivity.class);
        intent.putExtra("booking_id", booking_id);
        startActivity(intent);
    }

    @Override
    public void cancelBooking(final RequestCallback<CancelBookingResponse> requestCallback) {
        AndroidNetworking.get(myURL.CANCEL_BOOKING_URL + booking_id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(CancelBookingResponse.class, new ParsedRequestListener<CancelBookingResponse>() {
                    @Override
                    public void onResponse(CancelBookingResponse response) {
                        if(response == null){
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        } else {
                            requestCallback.requestSuccess(response,getString(R.string.cancel_booking_success_message) );
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void setPresenter(CancelBookingContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
