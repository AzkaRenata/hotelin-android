package com.example.hotelin_android.modul.booking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.previewBooking.PreviewBookingActivity;
import com.example.hotelin_android.modul.test.TestActivity;
import com.example.hotelin_android.util.SharedPreferencesUtil;

import java.util.Calendar;

public class BookingFragment extends BaseFragment<BookingActivity, BookingContract.Presenter> implements BookingContract.View {
    EditText etPemesan;
    EditText etEmail;
    EditText etCheckOut;
    String sCheckOut;
    EditText etCheckIn;
    String sCheckIn;
    EditText etTelp;
    Button btnNext;
    int room_id;
    String room_type;
    String room_price;
    String hotel_name;
    SharedPreferencesUtil sharedPreferencesUtil;

    public BookingFragment(SharedPreferencesUtil sharedPreferencesUtil, String hotel_name, int room_id, String room_type, String room_price) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        this.room_id = room_id;
        this.room_price = room_price;
        this.room_type = room_type;
        this.hotel_name = hotel_name;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.booking_activity, container, false);
        setTitle("Booking");

        etPemesan = fragmentView.findViewById(R.id.pemesan_et);
        etEmail = fragmentView.findViewById(R.id.email_et);
        etCheckIn = fragmentView.findViewById(R.id.checkIn_et);
        etCheckOut = fragmentView.findViewById(R.id.checkOut_et);
        etTelp = fragmentView.findViewById(R.id.telp_et);
        btnNext = fragmentView.findViewById(R.id.next_btn);

        initCalendar();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("tes", "tes1");
                setBtnNextClick();
            }
        });

        return fragmentView;
    }


    public void setBtnNextClick(){
        redirectToPreviewBooking();
    }

    public void initCalendar(){
        checkInDate();
        checkOutDate();
    }

    public void checkInDate(){
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
//                        String date = day+"/"+month+"/"+year;
//                        String dates = date.toString();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year-1);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        sCheckIn = year+"-"+month+"-"+day;
                        CharSequence date = DateFormat.format("EEE, d MMM yyyy", calendar);
                        etCheckIn.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


    }
    public void checkOutDate(){

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
//                        String date = day+"/"+month+"/"+year;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year-1);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        sCheckOut = year+"-"+month+"-"+day;
                        Log.e("_DATE", sCheckIn);
                        CharSequence date = DateFormat.format("EEE, d MMM yyyy", calendar);
                        etCheckOut.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void redirectToPreviewBooking() {
        Intent intent = new Intent(activity, PreviewBookingActivity.class);
        intent.putExtra("room_id", room_id);
        intent.putExtra("email",etEmail.getText().toString());
        intent.putExtra("pemesan", etPemesan.getText().toString());
        intent.putExtra("check_in", etCheckIn.getText().toString());
        intent.putExtra("check_out", etCheckOut.getText().toString());
        intent.putExtra("telp", etTelp.getText().toString());
        intent.putExtra("room_type", room_type);
        intent.putExtra("room_price", room_price);
        intent.putExtra("sCheckin", sCheckIn);
        intent.putExtra("sCheckout", sCheckOut);
        intent.putExtra("hotel_name", hotel_name);
        startActivity(intent);
    }

    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
    }
}