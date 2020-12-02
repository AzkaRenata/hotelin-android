package com.example.hotelin_android.modul.room_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.hotelin_android.model.RoomGroup;
import com.example.hotelin_android.modul.booking.BookingActivity;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.modul.register.RegisterActivity;
import com.example.hotelin_android.util.RecyclerViewAdapterRoomList;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

import java.util.List;

public class RoomListFragment extends BaseFragment<RoomListActivity, RoomListContract.Presenter> implements RoomListContract.View {
    SharedPreferencesUtil sharedPreferencesUtil;
    int hotel_id;
    String hotel_name;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public RoomListFragment(int hotel_id, String hotel_name, SharedPreferencesUtil sharedPreferencesUtil) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.room_list, container, false);
        mPresenter = new RoomListPresenter(this);
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewRoomList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.getData(hotel_id);
        setTitle("Room List");


        return fragmentView;
    }


    @Override
    public void setPresenter(RoomListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
    }

    public void redirectToBooking(int id, String type, String price){
        Intent intent = new Intent(activity, BookingActivity.class);
        intent.putExtra("room_id", id);
        intent.putExtra("hotel_name", hotel_name);
        intent.putExtra("room_type", type);
        intent.putExtra("room_price", price);
        startActivity(intent);
    }

    public void saveToken(String token){
        sharedPreferencesUtil.setToken(token);
    }

    @Override
    public void searchRoom(final int hotel_id, final RequestCallback<List<Room>> requestCallback) {
        AndroidNetworking.get(myURL.SEARCH_ROOM_URL+hotel_id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Room.class, new ParsedRequestListener<List<Room>>() {
                    @Override
                    public void onResponse(List<Room> response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }else{
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void setResult(final List<RoomGroup> rooms){
        TextView hotel_name = (TextView) fragmentView.findViewById(R.id.room_list_hotel_name_tv);
        hotel_name.setText(rooms.get(0).getRooms().get(0).getHotel_name());
        mAdapter = new RecyclerViewAdapterRoomList(rooms);
        mRecyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterRoomList) mAdapter).setOnItemClickListener(new RecyclerViewAdapterRoomList.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id = rooms.get(position).getRooms().get(0).getId();
                String price = rooms.get(position).getRooms().get(0).getRoom_price();
                String type = rooms.get(position).getRooms().get(0).getRoom_type();
                redirectToBooking(id, type, price);
            }
        });

    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}