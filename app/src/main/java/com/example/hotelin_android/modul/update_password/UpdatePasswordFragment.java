package com.example.hotelin_android.modul.update_password;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.myURL;


public class UpdatePasswordFragment extends BaseFragment<UpdatePasswordActivity, UpdatePasswordContract.Presenter> implements UpdatePasswordContract.View, View.OnClickListener {
    SharedPreferencesUtil sharedPreferencesUtil;


    EditText etPassword;
    EditText etPasswordConfirmation;

    Button btnSave;

    public UpdatePasswordFragment(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.edit_profile_activity, container, false);
        mPresenter = new UpdatePasswordPresenter(this);
        mPresenter.start();
        setTitle("Preview Booking");



        btnSave = fragmentView.findViewById(R.id.save_edit_btn);
        btnSave.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_edit_btn) saveBtnClick();
    }

    public void saveBtnClick(){

//        mPresenter.performRegister(user);
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
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 401) {
                            Log.e("UpdatePassword", "ERROR" + anError);
                            requestCallback.requestFailed("Please input a valid e-mail");
                        }else {
                            Log.e("UpdatePassword", String.valueOf(anError.getErrorCode()));
                            Log.e("UpdatePassword", "fdfd" + anError.getErrorBody());
                            Log.e("UpdatePassword", "fdfdsasa" + anError.getErrorDetail());
                            requestCallback.requestFailed("Server Error !");
                        }
                    }
                });
    }


//    @Override
//    public void requestProfile(final RequestCallback<User> requestCallback) {
//        AndroidNetworking.get(myURL.PROFILE_URL)
//                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
//                .build()
//                .getAsObject(TestResponse.class, new ParsedRequestListener<TestResponse>() {
//                    @Override
//                    public void onResponse(TestResponse response) {
//                        if(response == null){
//                            requestCallback.requestFailed("Null Response");
//                            Log.d("tag", "response null");
//                        }else{
//                            requestCallback.requestSuccess(response.user);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        requestCallback.requestFailed(anError.getMessage());
//                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
//                    }
//                });
//    }


    @Override
    public void showSuccessMessage(SuccessMessage data) {
        Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("tes", "failed");
    }


    @Override
    public void setPresenter(UpdatePasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
