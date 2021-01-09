package com.example.hotelin_android.util.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelin_android.R;
import com.example.hotelin_android.model.BookingDetail;
import com.example.hotelin_android.model.BookingHistory;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.myURL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class RecyclerViewAdapterBookingList extends RecyclerView.Adapter<RecyclerViewAdapterBookingList.MyViewHolder> {
    private static List<BookingHistory> mDataset;
    private static RecyclerViewAdapterBookingList.MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivHotel;
        TextView tvHotelName;
        TextView tvHotelLocation;
        TextView tvCheckIn;
        TextView tvCheckOut;
        TextView tvRoomType;
        TextView tvTotalPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivHotel = (ImageView) itemView.findViewById(R.id.booking_history_item_hotel_iv);
            tvRoomType = (TextView) itemView.findViewById(R.id.booking_history_item_room_tv);
            tvTotalPrice = (TextView) itemView.findViewById(R.id.booking_history_item_price_tv);
            tvHotelName = (TextView) itemView.findViewById(R.id.booking_history_item_hotel_name_tv);
            tvHotelLocation = (TextView) itemView.findViewById(R.id.booking_history_item_hotel_location_tv);
            tvCheckIn = (TextView) itemView.findViewById(R.id.booking_history_item_check_in_tv);
            tvCheckOut = (TextView) itemView.findViewById(R.id.booking_history_item_check_out_tv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterBookingList(List<BookingHistory> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerViewAdapterBookingList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_booking_history, parent, false);
        RecyclerViewAdapterBookingList.MyViewHolder myViewHolder = new RecyclerViewAdapterBookingList.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterBookingList.MyViewHolder holder, int position) {
        holder.tvHotelName.setText(mDataset.get(position).getHotel_name());
        holder.tvHotelLocation.setText(mDataset.get(position).getHotel_location());
        holder.tvCheckIn.setText(mDataset.get(position).getCheck_in());
        holder.tvCheckOut.setText(mDataset.get(position).getCheck_out());

        DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        kurs.setDecimalFormatSymbols(formatRp);

        holder.tvTotalPrice.setText(kurs.format(mDataset.get(position).getTotal_price()));

        String roomType = mDataset.get(position).getRoom_code() + " - " + mDataset.get(position).getRoom_type();
        holder.tvRoomType.setText(roomType);

        if(mDataset.get(position).getHotel_picture() != null){
            String url = myURL.getImageUrl() + mDataset.get(position).getHotel_picture();
            new AsyncTaskLoadImage(holder.ivHotel).execute(url);
        }


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
