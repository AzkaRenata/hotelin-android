package com.example.hotelin_android.modul.booking_detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.BookingDetail;
import com.example.hotelin_android.modul.cancel_booking.CancelBookingActivity;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.UserSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BookingDetailFragment extends BaseFragment<BookingDetailActivity, BookingDetailContract.Presenter> implements BookingDetailContract.View {
    private ImageView ivRoomPic;
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
    private TextView tvCancel;

    private BookingDetail bookingDetail;
    private final int booking_id;

    private final TokenSharedUtil tokenSharedUtil;
    private final UserSharedUtil userSharedUtil;

    public BookingDetailFragment(int booking_id) {
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        userSharedUtil = UtilProvider.getUserSharedUtil();

        this.booking_id = booking_id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_booking_detail, container, false);
        mPresenter = new BookingDetailPresenter(this, activity);
        mPresenter.start();
        mPresenter.getBookingDetail();

        return fragmentView;
    }

    @Override
    public void setItems() {
        ivRoomPic = fragmentView.findViewById(R.id.detail_booking_room_pic);
        tvHotelName = fragmentView.findViewById(R.id.detail_booking_hotel_name_tv);
        tvRoomType = fragmentView.findViewById(R.id.detail_booking_room_type_tv);
        tvRoomPrice = fragmentView.findViewById(R.id.detail_booking_room_price_tv);
        tvName = fragmentView.findViewById(R.id.detail_booking_name_tv);
        tvEmail = fragmentView.findViewById(R.id.detail_booking_email_tv);
        tvTelp = fragmentView.findViewById(R.id.detail_booking_telp_tv);
        tvCheckIn = fragmentView.findViewById(R.id.detail_booking_check_in_tv);
        tvCheckOut = fragmentView.findViewById(R.id.detail_booking_check_out_tv);
        tvDaysCount = fragmentView.findViewById(R.id.detail_booking_days_count_tv);
        tvTotalPrice = fragmentView.findViewById(R.id.detail_booking_total_price_tv);
        tvCancel = fragmentView.findViewById(R.id.detail_booking_cancel_tv);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnCancelClick();
            }
        });

        setTitle(getString(R.string.booking_detail_title));
    }

    @Override
    public void checkBookingStatus() {
        int status = bookingDetail.getBooking().getBooking_status();

        switch (status){
            case 1:
                tvCancel.setVisibility(View.VISIBLE);
                tvCancel.setText(getString(R.string.cancel_text));
                tvCancel.setTextColor(getResources().getColor(R.color.hotelinPrimary));
                tvCancel.setClickable(true);
                break;
            case 2:
                tvCancel.setVisibility(View.GONE);
                break;
            case 3:
                tvCancel.setVisibility(View.VISIBLE);
                tvCancel.setText(getString(R.string.canceled_text));
                tvCancel.setTextColor(getResources().getColor(R.color.hotelinText));
                tvCancel.setClickable(false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
    }

    @Override
    public void redirectToCancelBooking() {
        Intent intent = new Intent(activity, CancelBookingActivity.class);
        intent.putExtra("booking_id", booking_id);
        startActivity(intent);
    }

    public void setOnCancelClick(){
        mPresenter.moveToCancelBooking();
    }

    @Override
    public void setBookingDetail(BookingDetailResponse response) {
        bookingDetail = new BookingDetail(response.hotel, response.room, response.booking);
    }

    @Override
    public void setResult(){
        String roomType;
        String roomPrice;
        String totalPrice;
        String daysCount;

        if(bookingDetail.getRoom().getRoom_picture() != null){
            String url = myURL.getImageUrl() + bookingDetail.getRoom().getRoom_picture();
            new AsyncTaskLoadImage(ivRoomPic).execute(url);
        }

        DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        kurs.setDecimalFormatSymbols(formatRp);

        roomType = bookingDetail.getRoom().getRoom_code() + " - " + bookingDetail.getRoom().getRoom_type();
        roomPrice = kurs.format(bookingDetail.getRoom().getRoom_price());
        totalPrice = kurs.format(bookingDetail.getBooking().getTotal_price());
        daysCount = bookingDetail.getBooking().getDays_count() + " hari";

        tvHotelName.setText(bookingDetail.getHotel().getHotel_name());
        tvRoomType.setText(roomType);
        tvRoomPrice.setText(roomPrice);
        tvName.setText(userSharedUtil.getUser().getName());
        tvEmail.setText(userSharedUtil.getUser().getEmail());
        tvTelp.setText(userSharedUtil.getUser().getTelp());
        tvCheckIn.setText(bookingDetail.getBooking().getCheck_in());
        tvCheckOut.setText(bookingDetail.getBooking().getCheck_out());
        tvDaysCount.setText(daysCount);
        tvTotalPrice.setText(totalPrice);
    }

    @Override
    public void requestBookingDetail(final RequestCallback<BookingDetailResponse> requestCallback) {
        AndroidNetworking.get(myURL.BOOKING_DETAIL_URL + booking_id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .build()
                .getAsObject(BookingDetailResponse.class, new ParsedRequestListener<BookingDetailResponse>() {
                    @Override
                    public void onResponse(BookingDetailResponse response) {
                        if(response == null){
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        }else if(response.message != null){
                            requestCallback.requestFailed(response.message);
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
    public void setPresenter(BookingDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
