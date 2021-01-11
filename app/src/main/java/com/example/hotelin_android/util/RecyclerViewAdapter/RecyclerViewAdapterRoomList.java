package com.example.hotelin_android.util.RecyclerViewAdapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.model.Facility;
import com.example.hotelin_android.model.Room;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.myURL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class RecyclerViewAdapterRoomList extends RecyclerView.Adapter<RecyclerViewAdapterRoomList.MyViewHolder> {
    private static List<Room> mDataset;
    private static RecyclerViewAdapterRoomList.MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView room_iv;
        TextView room_type_tv;
        TextView room_bed_tv;
        TextView room_capacity_tv;
        TextView room_price_tv;
        TextView fac_wifi_tv;
        TextView fac_breakfast_tv;
        TextView fac_ac_tv;
        TextView fac_tv_tv;
        Button select_btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            room_iv = itemView.findViewById(R.id.room_list_item_room_iv);
            room_type_tv = itemView.findViewById(R.id.room_list_item_room_type_tv);
            room_bed_tv = itemView.findViewById(R.id.room_list_bed_tv);
            room_capacity_tv = itemView.findViewById(R.id.room_list_capacity_tv);
            room_price_tv = itemView.findViewById(R.id.room_list_price_tv);
            fac_wifi_tv = itemView.findViewById(R.id.room_list_fac_wifi_tv);
            fac_breakfast_tv = itemView.findViewById(R.id.room_list_fac_breakfast_tv);
            fac_ac_tv = itemView.findViewById(R.id.room_list_fac_ac_tv);
            fac_tv_tv = itemView.findViewById(R.id.room_list_fac_tv_tv);
            select_btn = itemView.findViewById(R.id.room_list_item_select_btn);

            select_btn.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterRoomList(List<Room> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterRoomList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_room_list, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerViewAdapterRoomList.MyViewHolder holder, int position) {
        holder.room_type_tv.setText(mDataset.get(position).getRoom_code() + " - " + mDataset.get(position).getRoom_type());
        holder.room_bed_tv.setText(mDataset.get(position).getBed_type() + " (" + mDataset.get(position).getBed_count() + ")");
        holder.room_capacity_tv.setText(mDataset.get(position).getGuest_capacity() + " Orang");

        DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");

        kurs.setDecimalFormatSymbols(formatRp);
        holder.room_price_tv.setText(kurs.format(mDataset.get(position).getRoom_price()));

        if(mDataset.get(position).getRoom_picture() != null){
            String url = myURL.getImageUrl()+mDataset.get(position).getRoom_picture();
            new AsyncTaskLoadImage(holder.room_iv).execute(url);
        }

        List<Facility> facility = mDataset.get(position).getFacility();

        for(int i = 0; i < facility.size(); i++){
            if(facility.get(i).getFacility_name().equalsIgnoreCase("free wifi")){
                holder.fac_wifi_tv.setText(R.string.wifi_text);
                holder.fac_wifi_tv.setTextColor(Color.parseColor("#707070"));
            }

            if(facility.get(i).getFacility_name().equalsIgnoreCase("sarapan")){
                holder.fac_breakfast_tv.setText(R.string.breakfast_text);
                holder.fac_breakfast_tv.setTextColor(Color.parseColor("#707070"));
            }

            if(facility.get(i).getFacility_name().equalsIgnoreCase("ac")){
                holder.fac_ac_tv.setText(R.string.ac_text);
                holder.fac_ac_tv.setTextColor(Color.parseColor("#707070"));
            }

            if(facility.get(i).getFacility_name().equalsIgnoreCase("tv")){
                holder.fac_tv_tv.setText(R.string.tv_text);
                holder.fac_tv_tv.setTextColor(Color.parseColor("#707070"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(RecyclerViewAdapterRoomList.MyClickListener myClickListener) {
        RecyclerViewAdapterRoomList.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
}
