package com.example.hotelin_android.modul.booking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.modul.preview_booking.PreviewBookingActivity;
import com.example.hotelin_android.util.SharedPreferences.BookingSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.HotelSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.RoomSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;

public class BookingFragment extends BaseFragment<BookingActivity, BookingContract.Presenter> implements BookingContract.View {
    EditText etHotelName;
    EditText etRoomType;
    EditText etName;
    EditText etEmail;
    EditText etTelp;
    TextView tvCheckOut;
    TextView tvCheckIn;
    Button btnNext;

    String strCheckOut;
    String strCheckIn;

    TokenSharedUtil tokenSharedUtil;
    HotelSharedUtil hotelSharedUtil;
    RoomSharedUtil roomSharedUtil;
    BookingSharedUtil bookingSharedUtil;

    public BookingFragment(String check_in, String check_out) {
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        hotelSharedUtil = UtilProvider.getHotelSharedUtil();
        roomSharedUtil = UtilProvider.getRoomSharedUtil();
        bookingSharedUtil = UtilProvider.getBookingSharedUtil();

        this.strCheckIn = check_in;
        this.strCheckOut = check_out;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_booking, container, false);
        mPresenter = new BookingPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setItems() {
        String hotelName;
        String roomType;

        etHotelName = fragmentView.findViewById(R.id.booking_hotel_name_tv);
        etRoomType = fragmentView.findViewById(R.id.booking_room_type_tv);
        etName = fragmentView.findViewById(R.id.booking_name_et);
        etEmail = fragmentView.findViewById(R.id.booking_email_et);
        tvCheckIn = fragmentView.findViewById(R.id.booking_check_in_tv);
        tvCheckOut = fragmentView.findViewById(R.id.booking_check_out_tv);
        etTelp = fragmentView.findViewById(R.id.booking_telp_et);
        btnNext = fragmentView.findViewById(R.id.next_btn);

        hotelName = hotelSharedUtil.getHotel().getHotel_name();
        roomType = roomSharedUtil.getRoom().getRoom_code() + " - " + roomSharedUtil.getRoom().getRoom_type();

        etHotelName.setText(hotelName);
        etRoomType.setText(roomType);
        tvCheckIn.setText(strCheckIn);
        tvCheckOut.setText(strCheckOut);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnNextClick();
            }
        });

        setTitle(getString(R.string.booking_title));
    }

    public void setBtnNextClick(){
        int room_id = roomSharedUtil.getRoom().getId();

        mPresenter.moveToPreviewBooking();
    }

    @Override
    public void redirectToPreviewBooking() {
        Intent intent = new Intent(activity, PreviewBookingActivity.class);
        startActivity(intent);
    }

    @Override
    public void saveBooking(Booking booking) {
        bookingSharedUtil.setBooking(booking);
    }

    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
    }
}