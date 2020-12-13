package com.example.hotelin_android.modul.change_password;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.Booking;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.modul.booking_history.BookingHistoryActivity;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;

public class ChangePasswordFragment extends BaseFragment<ChangePasswordActivity, ChangePasswordContract.Presenter> implements ChangePasswordContract.View, View.OnClickListener {
    EditText etPassword;
    EditText etPasswordOld;
    EditText etPasswordConfirmation;
    TextView forgotPassword;
    Button btnSave;
    String oldPassword;
    SharedPreferencesUtil sharedPreferencesUtil;

    public ChangePasswordFragment(SharedPreferencesUtil sharedPreferencesUtil, String oldPassword) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        this.oldPassword = oldPassword;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.change_password_activity, container, false);
        mPresenter = new ChangePasswordPresenter(this);
        mPresenter.start();
        setTitle("Change Password");

        etPasswordOld = fragmentView.findViewById(R.id.change_password_old_pass_et);
        forgotPassword = fragmentView.findViewById(R.id.change_password_forgot_password);
        etPassword = fragmentView.findViewById(R.id.change_password_new_pass_et);
        etPasswordConfirmation = fragmentView.findViewById(R.id.change_password_confirm_new_pass_et);
        btnSave = fragmentView.findViewById(R.id.change_password_save_btn);

        etPasswordOld.setVisibility(View.GONE);
        forgotPassword.setVisibility(View.GONE);
        btnSave.setOnClickListener(this);


        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.change_password_save_btn) saveBtnClick();
    }

    private void saveBtnClick() {
        if (etPassword.getText().toString().isEmpty() ||
                etPasswordConfirmation.getText().toString().isEmpty()) {
            if (etPassword.getText().toString().isEmpty())
                Toast.makeText(getContext(), "Fill your new password", Toast.LENGTH_SHORT);
            else if (etPasswordConfirmation.getText().toString().isEmpty())
            Toast.makeText(getContext(), "Fill your new password confirmation", Toast.LENGTH_SHORT);
//            else
//                Toast.makeText(getContext(), "Fill your old Password", Toast.LENGTH_SHORT);
        }
//        else if (etPasswordOld.getText().toString().isEmpty())
//            Toast.makeText(getContext(), "Fill your old Password", Toast.LENGTH_SHORT);
        else {
//            if (etPasswordOld.getText().toString().equals(oldPassword)) {
                if (etPassword.getText().toString().equals(etPasswordConfirmation.getText().toString()))
                    mPresenter.performUpdate(etPassword.getText().toString());
                else {
                    Toast.makeText(getContext(), "Password confirmation and Password must match!", Toast.LENGTH_SHORT);
                }
//            } else
//                Toast.makeText(getContext(), "Your old password not match!", Toast.LENGTH_SHORT);
        }
    }


    @Override
    public void updatePassword(String password, final RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_PASSWORD_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("password", password)
                .addBodyParameter("password_confirmation", password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SuccessMessage.class, new ParsedRequestListener<SuccessMessage>() {
                    @Override
                    public void onResponse(SuccessMessage response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                        } else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 401) {
                            Log.e("UpdatePassword", "ERROR" + anError);
                            requestCallback.requestFailed("Please input a valid e-mail");
                        } else {
                            Log.e("UpdatePassword", String.valueOf(anError.getErrorCode()));
                            Log.e("UpdatePassword", "fdfd" + anError.getErrorBody());
                            Log.e("UpdatePassword", "fdfdsasa" + anError.getErrorDetail());
                            requestCallback.requestFailed("Server Error !");
                        }
                    }
                });
    }

    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(ChangePasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void showSuccessMessage(SuccessMessage data) {
        Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT);
        redirectToProfile();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);

    }

}