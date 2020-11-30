package com.example.hotelin_android.modul.booking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.hotelin_android.modul.register.RegisterResponse;
import com.example.hotelin_android.modul.test.TestActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.Calendar;

public class BookingFragment extends BaseFragment<BookingActivity, BookingContract.Presenter> implements BookingContract.View {
    EditText etPemesan;
    EditText etEmail;
    EditText etCheckOut;
    EditText etCheckIn;
    EditText etTelp;
    EditText etJumlahKamar;
    Button btnBook;
    SharedPreferencesUtil sharedPreferencesUtil;

    public BookingFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.booking_activity, container, false);
        mPresenter = new BookingPresenter(this);
        mPresenter.start();

        etPemesan = fragmentView.findViewById(R.id.pemesan_et);
        etEmail = fragmentView.findViewById(R.id.email_et);
        etCheckIn = fragmentView.findViewById(R.id.checkIn_et);
        etCheckOut = fragmentView.findViewById(R.id.checkOut_et);
        etTelp = fragmentView.findViewById(R.id.telp_et);
        etJumlahKamar = fragmentView.findViewById(R.id.jumlahKamar_et);
        btnBook = fragmentView.findViewById(R.id.book_btn);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tes", "tes1");
                setBtnBookClick();
            }
        });

//        tvLogin.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                setTvLoginClick();
//                return true;
//            }
//        });

//        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                checkGender(group, checkedId);
//            }
//        });

        return fragmentView;
    }

//    public void checkGender(RadioGroup group, int checkedId){
//        switch (checkedId){
//            case radio_maleR:
//                gender = "male";
//                Log.e("tes", gender);
//                break;
//            case radio_femaleR:
//                gender = "female";
//                Log.e("tes", gender);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + checkedId);
//        }
//    }

    public void setBtnBookClick(){
        Log.e("tes", "tes2");
        int room_id = 1;
        String email = etEmail.getText().toString();
        String pemesan = etPemesan.getText().toString();
        String jumlahKamar = etJumlahKamar.getText().toString();
        String check_in = etCheckIn.getText().toString();
        String check_out = etCheckOut.getText().toString();

//        Booking newBooking = new Booking(room_id, check_in, check_out);
//        newBooking.setEmail(etEmail.getText().toString());
//        newBooking.setEmail(etEmail.getText().toString());

        Booking booking = new Booking(pemesan, email, jumlahKamar, room_id, check_in, check_out);

//        Log.e("set", String.valueOf(newBooking.getId()));
//        Log.e("set", String.valueOf(newBooking.getUser_id()));
//        Log.e("set", String.valueOf(newBooking.getRoom_id()));
//        Log.e("set", newBooking.getCheck_in());
//        Log.e("set", newBooking.getCheck_out());
//
//
//        Log.e("tes", "tes3");

        mPresenter.performBooking(booking);
    }


    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void redirectToPreviewBooking() {
        Intent intent = new Intent(activity, TestActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestBooking(final Booking newBooking, final RequestCallback<BookingResponse> requestCallback) {
        AndroidNetworking.post(myURL.BOOKING_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("room_id", "2")
                .addBodyParameter("check_in", newBooking.getCheck_in())
                .addBodyParameter("check_out", newBooking.getCheck_out())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BookingResponse.class, new ParsedRequestListener<BookingResponse>() {

                    @Override
                    public void onResponse(BookingResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response);
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Booking Success", Toast.LENGTH_SHORT).show();
        Log.e("tes", "success");
        redirectToPreviewBooking();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("tes", "failed");
    }
}