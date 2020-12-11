package com.example.hotelin_android.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.model.Hotel;

import java.util.List;

public class RecyclerViewAdapterHotelList extends RecyclerView.Adapter<RecyclerViewAdapterHotelList.MyViewHolder> {
    private static List<Hotel> mDataset;
    private static MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView hotel_iv;
        TextView hotel_name_tv;
        TextView hotel_location_tv;
        TextView hotel_price_tv;
        //CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            hotel_iv = (ImageView) itemView.findViewById(R.id.hotel_list_item_hotel_iv);
            hotel_name_tv = (TextView) itemView.findViewById(R.id.hotel_list_item_name_tv);
            hotel_location_tv = (TextView) itemView.findViewById(R.id.hotel_list_item_location_tv);
            hotel_price_tv = (TextView) itemView.findViewById(R.id.hotel_list_item_price_tv);
            //checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterHotelList(List<Hotel> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerViewAdapterHotelList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.hotel_name_tv.setText(mDataset.get(position).getHotel_name());
        holder.hotel_location_tv.setText(mDataset.get(position).getHotel_location());
        holder.hotel_price_tv.setText("Rp. "+mDataset.get(position).getHotel_price());

        String url = myURL.getImageUrl()+mDataset.get(position).getHotel_picture();
        new AsyncTaskLoadImage(holder.hotel_iv).execute(url);

        final Hotel hotel = mDataset.get(position);

//        holder.checkBox.setOnCheckedChangeListener(null);
        //holder.checkBox.setSelected(hotel.isSelected());
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    Log.d("gg", "ff");
//                    hotel.setSelected(true);
//                }else {
//                    hotel.setSelected(false);
//                }
//            }
//        });
//        holder.checkBox.setChecked(hotel.isSelected());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
