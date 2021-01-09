package com.example.hotelin_android.modul.cancel_booking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.modul.cancel_detail.CancelDetailActivity;
import com.example.hotelin_android.modul.register.RegisterActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.myURL;

public class CancelBookingFragment extends BaseFragment<CancelBookingActivity, CancelBookingContract.Presenter> implements CancelBookingContract.View{
    TokenSharedUtil tokenSharedUtil;
    int booking_id;
    Button back_btn;
    Button cancel_btn;

    public CancelBookingFragment(TokenSharedUtil tokenSharedUtil, int booking_id) {
        this.booking_id = booking_id;
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.cancel_activity, container, false);
        mPresenter = new CancelBookingPresenter(this);
        mPresenter.start();


        cancel_btn = fragmentView.findViewById(R.id.cancel_booking_cancel_btn);



        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.performCancelBooking(booking_id);
            }
        });

        setTitle("Cancel Confirmation");
        return fragmentView;
    }

    public void setBtLoginClick(){

    }

    public void setTvRegisterClick(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(CancelBookingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToCancelDetail(boolean isSuccess) {
        if(isSuccess){
            Intent intent = new Intent(activity, BookingHistoryActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(activity, CancelDetailActivity.class);
            intent.putExtra("booking_id", booking_id);
            intent.putExtra("booking_status", 1);
            startActivity(intent);
        }


    }

    @Override
    public void cancelBooking(final int booking_id, final RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.CANCEL_BOOKING+booking_id+"/3")
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(SuccessMessage.class, new ParsedRequestListener<SuccessMessage>() {
                    @Override
                    public void onResponse(SuccessMessage response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }else if(response.isSuccess() == false){
                            requestCallback.requestFailed("Cancel Failed");
                        } else {
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

    public void setResult(final SuccessMessage message){
        Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT);
        redirectToCancelDetail(true);
    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}
