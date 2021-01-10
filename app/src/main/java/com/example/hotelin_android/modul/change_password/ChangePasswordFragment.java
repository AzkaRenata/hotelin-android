package com.example.hotelin_android.modul.change_password;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordFragment extends BaseFragment<ChangePasswordActivity, ChangePasswordContract.Presenter> implements ChangePasswordContract.View, View.OnClickListener {
    private EditText etPassword;
    private EditText etPasswordOld;
    private EditText etPasswordConfirmation;

    private final TokenSharedUtil tokenSharedUtil;


    public ChangePasswordFragment() {
        this.tokenSharedUtil = UtilProvider.getTokenSharedUtil();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_change_password, container, false);
        mPresenter = new ChangePasswordPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.change_password_save_btn) saveBtnClick();
    }

    @Override
    public void setItems() {
        etPasswordOld = fragmentView.findViewById(R.id.change_password_old_pass_et);
        etPassword = fragmentView.findViewById(R.id.change_password_new_pass_et);
        etPasswordConfirmation = fragmentView.findViewById(R.id.change_password_confirm_new_pass_et);
        Button btnSave = fragmentView.findViewById(R.id.change_password_save_btn);
        TextInputLayout tilOldPassword = fragmentView.findViewById(R.id.change_old_password_til);
        TextInputLayout tilNewPassword = fragmentView.findViewById(R.id.change_new_password_til);
        TextInputLayout tilConfirmNewPassword = fragmentView.findViewById(R.id.change_confirm_new_password_til);

        tilOldPassword.setHintEnabled(false);
        tilNewPassword.setHintEnabled(false);
        tilConfirmNewPassword.setHintEnabled(false);

        btnSave.setOnClickListener(this);

        setTitle(getString(R.string.change_password_title));
    }

    private void saveBtnClick() {
        if (validatePassword()){
            String oldPassword = etPasswordOld.getText().toString();
            String newPassword = etPassword.getText().toString();
            String confirmNewPassword = etPasswordConfirmation.getText().toString();

            mPresenter.performUpdate(newPassword, oldPassword, confirmNewPassword);
        }
    }

    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
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
    public void updatePassword(String newPassword, String oldPassword, String confirmNewPassword, final RequestCallback<ChangePasswordResponse> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_PASSWORD_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("old_password", oldPassword)
                .addBodyParameter("password", newPassword)
                .addBodyParameter("password_confirmation", confirmNewPassword)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ChangePasswordResponse.class, new ParsedRequestListener<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(ChangePasswordResponse response) {
                        if(!response.success){
                            requestCallback.requestFailed(response.message);
                        }else{
                            requestCallback.requestSuccess(response, response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 401) {
                            requestCallback.requestFailed(getString(R.string.error_email_valid));
                        } else {
                            requestCallback.requestFailed(getString(R.string.error_password_format));
                        }
                    }
                });
    }

    @Override
    public void setPresenter(ChangePasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }
}