package com.example.hotelin_android.base;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelin_android.R;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.modul.register.RegisterActivity;


public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected ImageButton btOptionMenu;
    protected ImageButton btBack;
    protected ImageButton btSearch;
    protected ImageButton btBooking;
    protected ImageButton btProfile;
    protected View vMenuBarShadow;
    protected RelativeLayout rlActivityFragmentHolder;
    protected RelativeLayout loading;

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        btSearch = (ImageButton) findViewById(R.id.btSearch);
        btBooking = (ImageButton) findViewById(R.id.btBooking);
        btProfile = (ImageButton) findViewById(R.id.btProfile);
        vMenuBarShadow = findViewById(R.id.vMenuBarShadow);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
        loading = (RelativeLayout) findViewById(R.id.loading_screen);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        btBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingHistoryActivity.class);
                startActivity(intent);
            }
        });

        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }

    public void setItems(){}

    public void startLoading(){
        loading.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void stopLoading(){
        loading.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

