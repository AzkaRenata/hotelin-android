package com.example.hotelin_android.util.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.model.Hotel;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.myURL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class RecyclerViewAdapterHotelList extends RecyclerView.Adapter<RecyclerViewAdapterHotelList.MyViewHolder> {
    private static List<Hotel> mDataset;
    private static MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView hotel_iv;
        TextView hotel_name_tv;
        TextView hotel_location_tv;
        TextView hotel_price_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            hotel_iv = itemView.findViewById(R.id.hotel_list_item_hotel_iv);
            hotel_name_tv = itemView.findViewById(R.id.hotel_list_item_name_tv);
            hotel_location_tv = itemView.findViewById(R.id.hotel_list_item_location_tv);
            hotel_price_tv = itemView.findViewById(R.id.hotel_list_item_price_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterHotelList(List<Hotel> hotels) {
        mDataset = hotels;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterHotelList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_search_result, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.hotel_name_tv.setText(mDataset.get(position).getHotel_name());
        holder.hotel_location_tv.setText(mDataset.get(position).getHotel_location());

        DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        kurs.setDecimalFormatSymbols(formatRp);

        holder.hotel_price_tv.setText(kurs.format(mDataset.get(position).getHotel_price()));

        if(mDataset.get(position).getHotel_picture() != null){
            String url = myURL.getImageUrl() + mDataset.get(position).getHotel_picture();
            new AsyncTaskLoadImage(holder.hotel_iv).execute(url);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        RecyclerViewAdapterHotelList.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
}
