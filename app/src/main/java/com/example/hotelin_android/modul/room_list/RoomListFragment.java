package com.example.hotelin_android.modul.room_list;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.modul.preview_booking.PreviewBookingActivity;
import com.example.hotelin_android.util.RecyclerViewAdapter.RecyclerViewAdapterRoomList;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.HotelSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.RoomSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RoomListFragment extends BaseFragment<RoomListActivity, RoomListContract.Presenter> implements RoomListContract.View {
    private TextView tvCheckIn;
    private TextView tvCheckOut;
    private RelativeLayout rlNoResult;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private final int hotel_id;
    private final String hotel_name;
    private String strCheckOut;
    private String strCheckIn;

    private final TokenSharedUtil tokenSharedUtil;
    private final RoomSharedUtil roomSharedUtil;

    public RoomListFragment() {
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        HotelSharedUtil hotelSharedUtil = UtilProvider.getHotelSharedUtil();
        roomSharedUtil = UtilProvider.getRoomSharedUtil();

        hotel_id = hotelSharedUtil.getHotel().getId();
        hotel_name = hotelSharedUtil.getHotel().getHotel_name();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_room_list, container, false);
        mPresenter = new RoomListPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void setItems(){
        Button btnSearch;

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewRoomList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        tvCheckIn = fragmentView.findViewById(R.id.room_list_check_in_tv);
        tvCheckOut = fragmentView.findViewById(R.id.room_list_check_out_tv);
        btnSearch = fragmentView.findViewById(R.id.room_list_search_btn);
        rlNoResult = fragmentView.findViewById(R.id.no_result_layout);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnSearchClick();
            }
        });

        setTitle(hotel_name);
    }

    @Override
    public void initCalendar() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        setTvCheckInCalendar(year, month, day);
        setTvCheckOutCalendar(year, month, day);
    }

    public void setTvCheckInCalendar(final int year, final int month, final int day) {
        tvCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year - 1);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);

                        strCheckIn = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
                        tvCheckIn.setText(strCheckIn);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void setTvCheckOutCalendar(final int year, final int month, final int day) {
        tvCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year - 1);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);

                        strCheckOut = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
                        tvCheckOut.setText(strCheckOut);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void setBtnSearchClick(){
        strCheckIn = tvCheckIn.getText().toString();
        strCheckOut = tvCheckOut.getText().toString();

        checkDate();
    }

    public void checkDate(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date checkInDate = format.parse(strCheckIn);
            Date checkOutDate = format.parse(strCheckOut);

            if(checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate)){
                activity.showMessage(getString(R.string.error_wrong_date_input));
            }else{
                mPresenter.performRoomSearch();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            activity.showMessage(getString(R.string.error_empty_date_input));
        }
    }

    @Override
    public void redirectToPreviewBooking() {
        Intent intent = new Intent(activity, PreviewBookingActivity.class);
        intent.putExtra("check_in", strCheckIn);
        intent.putExtra("check_out", strCheckOut);
        startActivity(intent);
    }

    @Override
    public void checkResult(){
        rlNoResult.setVisibility(View.GONE);

        if(mAdapter.getItemCount() == 0){
            rlNoResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setResult(final List<Room> rooms) {
        List<Room> roomList = new ArrayList<>();

        for(int i = 0; i < rooms.size(); i++){
            if(!rooms.get(i).isIs_booked()){
                roomList.add(rooms.get(i));
            }
        }

        mAdapter = new RecyclerViewAdapterRoomList(roomList);
        mRecyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterRoomList) mAdapter).setOnItemClickListener(new RecyclerViewAdapterRoomList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = rooms.get(position).getId();
                mPresenter.getRoomDetail(id);
            }
        });

    }

    @Override
    public void requestAvailableRoom(final RequestCallback<RoomListResponse> requestCallback) {
        AndroidNetworking.post(myURL.SEARCH_AVAILABLE_ROOM_URL + hotel_id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("check_in", strCheckIn)
                .addBodyParameter("check_out", strCheckOut)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(RoomListResponse.class, new ParsedRequestListener<RoomListResponse>() {
                    @Override
                    public void onResponse(RoomListResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        } else {
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
    public void requestRoomDetail(final int id, final RequestCallback<RoomListResponse> requestCallback) {
        AndroidNetworking.get(myURL.GET_ROOM_DETAIL_URL + id)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(RoomListResponse.class, new ParsedRequestListener<RoomListResponse>() {
                    @Override
                    public void onResponse(RoomListResponse response) {
                        if(response == null){
                            requestCallback.requestFailed(getString(R.string.error_null_response));
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
    public void saveRoom(Room room){
        roomSharedUtil.setRoom(room);
    }

    @Override
    public void setPresenter(RoomListContract.Presenter presenter) {
        mPresenter = presenter;
    }
}