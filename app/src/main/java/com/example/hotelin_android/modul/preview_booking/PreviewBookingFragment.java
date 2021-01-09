package com.example.hotelin_android.modul.preview_booking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.HotelSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.RoomSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.UserSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PreviewBookingFragment extends BaseFragment<PreviewBookingActivity, PreviewBookingContract.Presenter> implements PreviewBookingContract.View {
    private TextView tvHotelName;
    private TextView tvRoomType;
    private TextView tvRoomPrice;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvTelp;
    private TextView tvCheckIn;
    private TextView tvCheckOut;
    private TextView tvDaysCount;
    private TextView tvTotalPrice;
    private ImageView ivRoomPicture;

    private String strCheckIn;
    private String strCheckOut;
    private int daysCount;
    private double totalPrice;

    private final TokenSharedUtil tokenSharedUtil;
    private final UserSharedUtil userSharedUtil;
    private final HotelSharedUtil hotelSharedUtil;
    private final RoomSharedUtil roomSharedUtil;


    public PreviewBookingFragment(String check_in, String check_out) {
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        userSharedUtil = UtilProvider.getUserSharedUtil();
        hotelSharedUtil = UtilProvider.getHotelSharedUtil();
        roomSharedUtil = UtilProvider.getRoomSharedUtil();

        this.strCheckIn = check_in;
        this.strCheckOut = check_out;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_preview_booking, container, false);
        mPresenter = new PreviewBookingPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems() {
        Button btnBook;

        ivRoomPicture = fragmentView.findViewById(R.id.preview_booking_room_pic);
        tvHotelName = fragmentView.findViewById(R.id.preview_booking_hotel_name_tv);
        tvRoomType = fragmentView.findViewById(R.id.preview_booking_room_type_tv);
        tvRoomPrice = fragmentView.findViewById(R.id.preview_booking_room_price_tv);
        tvName = fragmentView.findViewById(R.id.preview_booking_name_tv);
        tvEmail = fragmentView.findViewById(R.id.preview_booking_email_tv);
        tvTelp = fragmentView.findViewById(R.id.preview_booking_telp_tv);
        tvCheckIn = fragmentView.findViewById(R.id.preview_booking_check_in_tv);
        tvCheckOut = fragmentView.findViewById(R.id.preview_booking_check_out_tv);
        tvDaysCount = fragmentView.findViewById(R.id.preview_booking_days_count_tv);
        tvTotalPrice = fragmentView.findViewById(R.id.preview_booking_total_price_tv);
        btnBook = fragmentView.findViewById(R.id.preview_booking_book_btn);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnBookClick();
            }
        });

        calculateDaysCount();
        calculateTotalPrice();

        setTitle(getString(R.string.preview_booking_title));
    }

    @SuppressLint("SimpleDateFormat")
    public void calculateDaysCount(){
        strCheckIn = strCheckIn + " " + getString(R.string.booking_time);
        strCheckOut = strCheckOut + " " + getString(R.string.booking_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date checkIn = sdf.parse(strCheckIn);
            Date checkOut = sdf.parse(strCheckOut);

            Log.e("checkin", String.valueOf(checkIn));
            Log.e("checkout", String.valueOf(checkOut));

            long differenceInTime = checkOut.getTime() - checkIn.getTime();
            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInTime) % 365;

            Log.e("time", String.valueOf(differenceInTime));
            Log.e("day", String.valueOf(differenceInDays));

            daysCount = (int) differenceInDays;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void calculateTotalPrice(){
        totalPrice = roomSharedUtil.getRoom().getRoom_price() * daysCount;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setBookingDetails() {
        String roomType;
        String roomPrice;
        String strTotalPrice;

        DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        kurs.setDecimalFormatSymbols(formatRp);

        roomType = roomSharedUtil.getRoom().getRoom_code() + " - " + roomSharedUtil.getRoom().getRoom_type();
        roomPrice = kurs.format(roomSharedUtil.getRoom().getRoom_price());
        strTotalPrice = kurs.format(totalPrice);

        tvHotelName.setText(hotelSharedUtil.getHotel().getHotel_name());
        tvRoomType.setText(roomType);
        tvRoomPrice.setText(roomPrice);
        tvName.setText(userSharedUtil.getUser().getName());
        tvEmail.setText(userSharedUtil.getUser().getEmail());
        tvTelp.setText(userSharedUtil.getUser().getTelp());
        tvCheckIn.setText(strCheckIn);
        tvCheckOut.setText(strCheckOut);
        tvDaysCount.setText(daysCount + " hari");
        tvTotalPrice.setText(strTotalPrice);

        if(roomSharedUtil.getRoom().getRoom_picture() != null){
            String url = myURL.getImageUrl() + roomSharedUtil.getRoom().getRoom_picture();
            new AsyncTaskLoadImage(ivRoomPicture).execute(url);
        }
    }

    public void setBtnBookClick() {
        mPresenter.performBooking();
    }

    @Override
    public void redirectToBookingHistory() {
        Intent intent = new Intent(activity, BookingHistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestBooking(final RequestCallback<PreviewBookingResponse> requestCallback) {
        AndroidNetworking.post(myURL.BOOKING_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("room_id", String.valueOf(roomSharedUtil.getRoom().getId()))
                .addBodyParameter("check_in", strCheckIn)
                .addBodyParameter("check_out", strCheckOut)
                .addBodyParameter("days_count", String.valueOf(daysCount))
                .addBodyParameter("total_price", String.valueOf(totalPrice))
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(PreviewBookingResponse.class, new ParsedRequestListener<PreviewBookingResponse>() {
                    @Override
                    public void onResponse(PreviewBookingResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        } else {
                            requestCallback.requestSuccess(response, getString(R.string.booking_success_message));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void setPresenter(PreviewBookingContract.Presenter presenter) {
        mPresenter = presenter;
    }
}