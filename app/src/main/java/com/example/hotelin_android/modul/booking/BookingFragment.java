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
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.modul.previewBooking.PreviewBookingActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

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
        int id = 5;
        int user_id = 1;
        int room_id = 1;
        int booking_status = 1;
        String check_in = etCheckIn.getText().toString();
        String check_out = etCheckOut.getText().toString();
        String email = etEmail.getText().toString();
        Date current_time = (Date) Calendar.getInstance().getTime();

        Booking newBooking = new Booking(id, user_id, room_id, booking_status, check_in, check_out, current_time);

        Log.e("set", String.valueOf(newBooking.getId()));
        Log.e("set", String.valueOf(newBooking.getUser_id()));
        Log.e("set", String.valueOf(newBooking.getRoom_id()));
        Log.e("set", String.valueOf(newBooking.getBooking_status()));
        Log.e("set", newBooking.getCheck_in());
        Log.e("set", newBooking.getCheck_out());
        Log.e("set", String.valueOf(newBooking.getBooking_time()));


        Log.e("tes", "tes3");

        mPresenter.performBooking(newBooking);
    }


    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void redirectToPreviewBooking() {
        Intent intent = new Intent(activity, PreviewBookingActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestBooking(final Booking newBooking, final RequestCallback<BookingResponse> requestCallback) {
//        Log.e("tes", URL.BOOKING_URL);
//        Log.e("tes", String.valueOf(newBooking.getId()));
//        String username = "Username";
        AndroidNetworking.post(myURL.BOOKING_URL)
                .addBodyParameter("id", String.valueOf(newBooking.getId()))
                .addBodyParameter("user_id", String.valueOf(newBooking.getUser_id()))
                .addBodyParameter("room_id", String.valueOf(newBooking.getRoom_id()))
                .addBodyParameter("booking_status", String.valueOf(newBooking.getBooking_status()))
                .addBodyParameter("check_in", newBooking.getCheck_in())
                .addBodyParameter("check_out", newBooking.getCheck_out())
                .addBodyParameter("booking_time", String.valueOf(newBooking.getBooking_time()))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BookingResponse.class, new ParsedRequestListener<BookingResponse>() {

                    @Override
                    public void onResponse(BookingResponse response) {
                        if(response == null){
                            Log.e("tes", myURL.BOOKING_URL);
                            requestCallback.requestFailed("Null Response");
                        }else {
                            Log.e("tes", myURL.BOOKING_URL);
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
//                        if(anError.getErrorCode() == 401) {
//                            Log.e("tes", "ERROR", anError);
//                            requestCallback.requestFailed("Please input a valid e-mail");
//                        }else {
//                            Log.e("tes", String.valueOf(anError.getErrorCode()));
//                            Log.e("tes", anError.getErrorBody());
//                            Log.e("tes", anError.getErrorDetail());
////                            Log.e("tesuser", newUser.getUsername());
////                            Log.e("tesemail", newUser.getEmail());
////                            Log.e("tespass", newUser.getPassword());
//                            requestCallback.requestFailed("Server Error !");
//                        }
                    }
                });
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Booking Success", Toast.LENGTH_SHORT).show();
        Log.e("tes", "success");
//        redirectToLogin();
        redirectToPreviewBooking();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("tes", "failed");
    }
}