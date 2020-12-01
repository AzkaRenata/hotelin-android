package com.example.hotelin_android.modul.cancel_detail;

import com.example.hotelin_android.model.Bookinghistory;

public class CancelDetailResponse {
    //perahtikan return dari api
    //pastikan return api berupa objek ditandai dengan method compact()
    //nama attribute di class response ini harus sama dengan nama objek yang direturn oleh api
    public Bookinghistory bookinghistory;
}
