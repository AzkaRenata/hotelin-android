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

    public void setBtnBookClick(){
        Log.d("Req", sharedPreferencesUtil.getToken());
        int room_id = booking.getRoom_id();
        String checkin = booking.getsCheck_in();
        String checkout = booking.getsCheck_out();
        RunPost(room_id, checkin, checkout);
        mPresenter.performBooking(room_id, checkin, checkout);
    }

    public void RunPost(int room_id, String checkin, String checkout){
        Log.e("RunPost", "method jalan");

        AndroidNetworking.post(myURL.BOOKING_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("room_id", "2")
                .addBodyParameter("check_in", "22222")
                .addBodyParameter("check_out", "33333")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    redirectToBookingHistory();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.e("RunPost", error.toString());

                    }
                });
        Log.e("RunPost", "method jalan22");

    }

    public void setTextView(){
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
    public void redirectToBookingHistory() {
        Intent intent = new Intent(activity, BookingHistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestBooking(int room_id, String checkin, String checkout, final RequestCallback<PreviewBookingResponse> requestCallback) {
        AndroidNetworking.post(myURL.BOOKING_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("room_id", "1")
                .addBodyParameter("check_in", "22")
                .addBodyParameter("check_out", "2222")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(PreviewBookingResponse.class, new ParsedRequestListener<PreviewBookingResponse>() {

                    @Override
                    public void onResponse(PreviewBookingResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.e("ReqBook", booking.getsCheck_out());
                            Log.e("RequestBooking", "Null bang");

                        }else {
                            requestCallback.requestSuccess(response);
//                            Log.e(response.booking_time);
                            Log.e("ReqBook", booking.getsCheck_out());

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.e("RequestBooking", error.toString());

                    }
                });
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Booking Success", Toast.LENGTH_SHORT).show();
//        Log.e("tes", "success");
        redirectToBookingHistory();
    }


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("MSG", "failed");
    }
}