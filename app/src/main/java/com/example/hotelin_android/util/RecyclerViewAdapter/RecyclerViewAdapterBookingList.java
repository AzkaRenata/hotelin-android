package com.example.hotelin_android.util.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.model.Bookinghistory;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.myURL;

import java.util.List;

public class RecyclerViewAdapterBookingList extends RecyclerView.Adapter<RecyclerViewAdapterBookingList.MyViewHolder> {
    private static List<Bookinghistory> mDataset;
    private static RecyclerViewAdapterBookingList.MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView hotel_iv;
        TextView hotel_name;
        TextView hotel_location;
        TextView booking_time;
        TextView room_type_tv;
        TextView room_price_tv;
        //TextView hotel_price_tv;
        //CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            hotel_iv = (ImageView) itemView.findViewById(R.id.booing_history_hotel_iv);
            room_type_tv = (TextView) itemView.findViewById(R.id.booing_history_room_type);
            room_price_tv = (TextView) itemView.findViewById(R.id.booing_history_price);
            hotel_name = (TextView) itemView.findViewById(R.id.booing_history_hotel_name);
            hotel_location = (TextView) itemView.findViewById(R.id.booing_history_hotel_location);
            booking_time = (TextView) itemView.findViewById(R.id.booing_history_booking_date);
            //checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterBookingList(List<Bookinghistory> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerViewAdapterBookingList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_booking_history_list, parent, false);
        RecyclerViewAdapterBookingList.MyViewHolder myViewHolder = new RecyclerViewAdapterBookingList.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterBookingList.MyViewHolder holder, int position) {
        //holder.hotel_name_tv.setText(mDataset.get(position).getHotel_name());
        holder.hotel_name.setText(mDataset.get(position).getHotel_name());
        holder.hotel_location.setText(mDataset.get(position).getHotel_location());
        holder.room_price_tv.setText("Rp. "+mDataset.get(position).getRoom_price());
        holder.room_type_tv.setText(mDataset.get(position).getRoom_type());
        holder.booking_time.setText(mDataset.get(position).getBooking_time());

        String url = myURL.getImageUrl()+mDataset.get(position).getHotel_picture();
        new AsyncTaskLoadImage(holder.hotel_iv).execute(url);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(RecyclerViewAdapterBookingList.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
