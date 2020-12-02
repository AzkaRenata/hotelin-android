package com.example.hotelin_android.modul.previewBooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

import org.json.JSONException;
import org.json.JSONObject;

public class PreviewBookingFragment extends BaseFragment<PreviewBookingActivity, PreviewBookingContract.Presenter> implements PreviewBookingContract.View {
    Booking booking;
    Button btnBook;
    TextView tvNamaHotel;
    TextView tvTipeKamar;
    TextView tvHargaKamar;
    TextView tvPemesan;
    TextView tvCheckout;
    TextView tvEmail;
    TextView tvTelp;
    TextView tvCheckin;
    SharedPreferencesUtil sharedPreferencesUtil;

    public PreviewBookingFragment(SharedPreferencesUtil sharedPreferencesUtil, Booking booking) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        this.booking = booking;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.preview_booking, container, false);
        mPresenter = new PreviewBookingPresenter(this);
        mPresenter.start();
        setTitle("Preview Booking");

        tvNamaHotel = fragmentView.findViewById(R.id.preview_booking_hotel_name_tv);
        tvTipeKamar = fragmentView.findViewById(R.id.preview_booking_room_type_tv);
        tvHargaKamar = fragmentView.findViewById(R.id.preview_booking_room_price_tv);
        tvPemesan = fragmentView.findViewById(R.id.preview_booking_pemesan_tv);
        tvEmail = fragmentView.findViewById(R.id.preview_booking_email_tv);
        tvCheckout = fragmentView.findViewById(R.id.preview_booking_checkout_tv);
        tvTelp = fragmentView.findViewById(R.id.preview_booking_telp_tv);
        tvCheckin = fragmentView.findViewById(R.id.preview_booking_checkin_tv);
        btnBook = fragmentView.findViewById(R.id.preview_booking_book_btn);


        setTextView();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnBookClick();
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnBookClick();
            }
        });
        return fragmentView;
    }

    public void setBtnBookClick() {
        Log.d("Req", sharedPreferencesUtil.getToken());
        int room_id = booking.getRoom_id();
        String checkin = booking.getsCheck_in();
        String checkout = booking.getsCheck_out();
        mPresenter.performBooking(room_id, checkin, checkout);
    }

    public void setTextView() {
        tvNamaHotel.setText(booking.getHotel_name());
        tvTipeKamar.setText(booking.getRoom_type());
        tvHargaKamar.setText(booking.getRoom_price());
        tvPemesan.setText(booking.getNamaPemesan());
        tvEmail.setText(booking.getEmail());
        tvTelp.setText(booking.getTelp());
        tvCheckin.setText(booking.getCheck_in());
        tvCheckout.setText(booking.getCheck_out());
        Log.e("_DATES", booking.getsCheck_in());

    }

    @Override
    public void setPresenter(PreviewBookingContract.Presenter presenter) {
        Log.e("tes", booking.getsCheck_in());

        mPresenter = presenter;
    }


    @Override
    public void redirectToBookingHistory(boolean isSuccess) {
        Intent intent = new Intent(activity, BookingHistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestBooking(final int room_id, final String checkin, final String checkout, final RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.BOOKING_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("room_id", String.valueOf(room_id))
                .addBodyParameter("check_in", checkin)
                .addBodyParameter("check_out", checkout)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SuccessMessage.class, new ParsedRequestListener<SuccessMessage>() {

                    @Override
                    public void onResponse(SuccessMessage response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        } else if (response.isSuccess() == false) {
                            requestCallback.requestFailed("Cancel Failed");
                        } else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("reqBooking", "error : " + anError.getMessage() + anError.getErrorCode());
                        Log.e("reqBooking", "id         :" + String.valueOf(room_id));
                        Log.e("reqBooking", "checkin    :" + checkin);
                        Log.e("reqBooking", "checkout   :" + checkout);
                    }
                });
    }

    @Override
    public void setResult(final SuccessMessage message) {
        Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT);
        redirectToBookingHistory(true);
    }

    @Override
    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("MSG", "failed");
    }

}