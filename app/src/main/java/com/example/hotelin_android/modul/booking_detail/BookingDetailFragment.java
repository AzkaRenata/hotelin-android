package com.example.hotelin_android.modul.booking_detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.cancel_booking.CancelBookingActivity;
import com.example.hotelin_android.model.BookinghHstorytemp;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.myURL;

public class BookingDetailFragment extends BaseFragment<BookingDetailActivity, BookingDetailContract.Presenter> implements BookingDetailContract.View {
    TokenSharedUtil tokenSharedUtil;
    int booking_id;
    int booking_status;
    ImageView hotel_iv;
    TextView hotel_name;
    TextView room_type;
    TextView customer;
    TextView customer_email;
    TextView customer_telp;
    TextView check_in;
    TextView check_out;
    TextView booking_price;
    TextView cancel_tv;

    public BookingDetailFragment(TokenSharedUtil tokenSharedUtil, int booking_id) {
        this.booking_id = booking_id;
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.detail_cancel_booking_t, container, false);
        mPresenter = new BookingDetailPresenter(this);
        mPresenter.start();
        mPresenter.getData(booking_id);

        hotel_iv = fragmentView.findViewById(R.id.detail_cancel_hotel_iv);
        hotel_name = fragmentView.findViewById(R.id.detail_cancel_hotel_name);
        room_type = fragmentView.findViewById(R.id.detail_cancel_room_type);
        customer = fragmentView.findViewById(R.id.detail_cancel_username);
        customer_email = fragmentView.findViewById(R.id.detail_cancel_email);
        customer_telp = fragmentView.findViewById(R.id.detail_cancel_phone);
        check_in = fragmentView.findViewById(R.id.detail_cancel_checkin);
        check_out = fragmentView.findViewById(R.id.detail_cancel_checkout);
        booking_price = fragmentView.findViewById(R.id.detail_cancel_price);
        cancel_tv = fragmentView.findViewById(R.id.detail_cancel_cancel_tv);

        cancel_tv.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setOnCancelClick();
                return true;
            }
        });
Log.d("LIAT BOOKING ID : ", String.valueOf(booking_id));
        if(booking_status == 1){
            cancel_tv.setText("Cancel");
        } else if(booking_status == 2){
            cancel_tv.setText("Done");
        } else {
            cancel_tv.setText("Canceled");
        }
        setTitle("My Current Booking");
        return fragmentView;
    }

    public void setOnCancelClick(){
        if(booking_status == 1){
            Intent intent = new Intent(activity, CancelBookingActivity.class);
            intent.putExtra("booking_id", booking_id);
            startActivity(intent);
        }
    }

    @Override
    public void setPresenter(BookingDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void searchBooking(final int booking_id, final RequestCallback<BookinghHstorytemp> requestCallback) {
        Log.d("test lagi : ",myURL.MY_BOOKING_URL+booking_id);
        AndroidNetworking.get(myURL.MY_BOOKING_URL+String.valueOf(booking_id))
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .build()
                .getAsObject(BookingDetailResponse.class, new ParsedRequestListener<BookingDetailResponse>() {
                    @Override
                    public void onResponse(BookingDetailResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }else{
                            requestCallback.requestSuccess(response.bookinghistory, "tes");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void setResult(final BookinghHstorytemp booking){
        String url = myURL.getImageUrl() + booking.getHotel_picture();
        new AsyncTaskLoadImage(hotel_iv).execute(url);

        hotel_name.setText(booking.getHotel_name());
        room_type.setText(booking.getRoom_type());
        customer.setText(booking.getName());
        customer_email.setText(booking.getEmail());
        customer_telp.setText(booking.getTelp());
        check_in.setText(booking.getCheck_in());
        check_out.setText(booking.getCheck_out());
        booking_price.setText("Rp. "+booking.getRoom_price());

    }

    public void showFailedMessage(String message){
        Log.d("error","MASUKKKKKKKKKKKKKKKKKKKKKKKKKKKKK ERRORRRR");
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}
