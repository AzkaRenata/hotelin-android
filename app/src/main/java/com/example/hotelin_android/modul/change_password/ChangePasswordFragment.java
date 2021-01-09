package com.example.hotelin_android.modul.change_password;

import android.content.Intent;
import android.graphics.Color;
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
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.SuccessMessage;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.myURL;

public class ChangePasswordFragment extends BaseFragment<ChangePasswordActivity, ChangePasswordContract.Presenter> implements ChangePasswordContract.View, View.OnClickListener {
    EditText etPassword;
    EditText etPasswordOld;
    EditText etPasswordConfirmation;
    TextView tvForgotPassword;
    TextView tvWarning;
    Button btnSave;
    String oldPassword;
    TokenSharedUtil tokenSharedUtil;

    public ChangePasswordFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
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
        tvForgotPassword = fragmentView.findViewById(R.id.change_password_forgot_password);
        tvWarning = fragmentView.findViewById(R.id.change_password_warning_tv);
        etPassword = fragmentView.findViewById(R.id.change_password_new_pass_et);
        etPasswordConfirmation = fragmentView.findViewById(R.id.change_password_confirm_new_pass_et);
        btnSave = fragmentView.findViewById(R.id.change_password_save_btn);

//        etPasswordOld.setVisibility(View.GONE);
        tvForgotPassword.setVisibility(View.GONE);
        btnSave.setOnClickListener(this);


        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.change_password_save_btn) saveBtnClick();
    }

    private void saveBtnClick() {
        String oldPassword = etPasswordOld.getText().toString();
        String newPassword = etPassword.getText().toString();
        if (validatePassword()) mPresenter.performUpdate(newPassword, oldPassword);
    }

    private boolean validatePassword() {
        AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(activity, R.id.change_password_old_pass_et, RegexTemplate.NOT_EMPTY, R.string.field_empty);
        awesomeValidation.addValidation(activity, R.id.change_password_new_pass_et, RegexTemplate.NOT_EMPTY, R.string.field_empty);
        awesomeValidation.addValidation(activity, R.id.change_password_confirm_new_pass_et, RegexTemplate.NOT_EMPTY, R.string.field_empty);
        awesomeValidation.addValidation(activity, R.id.change_password_confirm_new_pass_et, etPassword.getText().toString(), R.string.confirmation_password_not_match);
        return awesomeValidation.validate();
    }


    @Override
    public void updatePassword(String newPassword, String oldPassword, final RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_PASSWORD_URL)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("old_password", oldPassword)
                .addBodyParameter("password", newPassword)
                .addBodyParameter("password_confirmation", newPassword)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SuccessMessage.class, new ParsedRequestListener<SuccessMessage>() {
                    @Override
                    public void onResponse(SuccessMessage response) {
                        Log.e("UpdatePassword", "ERROR" + response.getMessage());
                        showErrorMessage(response.getMessage());
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 401) {
                            Log.e("UpdatePassword", "ERROR" + anError);
//                            requestCallback.requestFailed("Please input a valid e-mail");
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
        Toast.makeText(activity, data.getMessage(), Toast.LENGTH_SHORT);
        redirectToProfile();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        tvWarning.setVisibility(View.VISIBLE);
        tvWarning.setText(message);
        if (message.equalsIgnoreCase("berhasil")) redirectToProfile();
    }

}