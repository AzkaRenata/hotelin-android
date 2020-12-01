package com.example.hotelin_android.util;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.model.RoomGroup;
import com.example.hotelin_android.modul.previewBooking.PreviewBookingActivity;

import java.util.List;

public class RecyclerViewAdapterRoomList extends RecyclerView.Adapter<RecyclerViewAdapterRoomList.MyViewHolder> {
    private static List<RoomGroup> mDataset;
    private static RecyclerViewAdapterRoomList.MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView room_iv;
        TextView room_type_tv;
        TextView room_price_tv;
        TextView room_fac1;
        TextView room_fac2;
        TextView room_fac3;
        Button pilihKamar;
        //TextView hotel_price_tv;
        //CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            room_iv = (ImageView) itemView.findViewById(R.id.room_list_item_room_iv);
            room_type_tv = (TextView) itemView.findViewById(R.id.room_list_item_room_type_tv);
            room_price_tv = (TextView) itemView.findViewById(R.id.room_list_item_room_price_tv);
            room_fac1 = (TextView) itemView.findViewById(R.id.room_list_item_fac1);
            room_fac2 = (TextView) itemView.findViewById(R.id.room_list_item_fac2);
            room_fac3 = (TextView) itemView.findViewById(R.id.room_list_item_fac3);
            pilihKamar = (Button) itemView.findViewById(R.id.room_list_item_select_btn);

            //checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxItem);
            itemView.setOnClickListener(this);
            pilihKamar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterRoomList(List<RoomGroup> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerViewAdapterRoomList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_item, parent, false);
        RecyclerViewAdapterRoomList.MyViewHolder myViewHolder = new RecyclerViewAdapterRoomList.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterRoomList.MyViewHolder holder, int position) {
        //holder.hotel_name_tv.setText(mDataset.get(position).getHotel_name());
        holder.room_price_tv.setText("Rp. "+mDataset.get(position).getRooms().get(0).getRoom_price());
        holder.room_type_tv.setText(mDataset.get(position).getRooms().get(0).getRoom_type());

        int i = 0;
        for(Room room : mDataset.get(position).getRooms()){
            switch (i){
                case 0:
                    holder.room_fac1.setText("     "+mDataset.get(position).getRooms().get(i).getFacility_name());
                    break;
                case 1:
                    holder.room_fac2.setText("     "+mDataset.get(position).getRooms().get(i).getFacility_name());
                    break;
                case 2:
                    holder.room_fac3.setText("     "+mDataset.get(position).getRooms().get(i).getFacility_name());
                    break;
            }
            i++;
        }
        String url = myURL.getImageUrl()+mDataset.get(position).getRooms().get(0).getRoom_picture();
        new AsyncTaskLoadImage(holder.room_iv).execute(url);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(RecyclerViewAdapterRoomList.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
