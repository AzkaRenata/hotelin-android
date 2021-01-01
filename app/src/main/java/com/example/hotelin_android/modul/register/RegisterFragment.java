package com.example.hotelin_android.modul.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.modul.login.LoginActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.TokenSharedUtil;
import com.example.hotelin_android.util.UserSharedUtil;
import com.example.hotelin_android.util.myURL;

import static com.example.hotelin_android.R.id.*;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View {
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    EditText etFullname;
    EditText etTelp;
    EditText etAddress;
    String gender;
    RadioGroup rgGender;
    RadioButton rbGenderMale;
    RadioButton rbGenderFemale;
    Button btnRegister;
    TextView tvLogin;
    TokenSharedUtil tokenSharedUtil;
    UserSharedUtil userSharedUtil;

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this);
        mPresenter.start();



        return fragmentView;
    }

    public void setItems(){
        etUsername = fragmentView.findViewById(usernameR);
        etEmail = fragmentView.findViewById(emailR);
        etPassword = fragmentView.findViewById(passwordR);
        etConfirmPassword = fragmentView.findViewById(confirm_passwordR);
        etFullname = fragmentView.findViewById(fullnameR);
        etTelp = fragmentView.findViewById(telpR);
        etAddress = fragmentView.findViewById(addressR);
        rgGender = fragmentView.findViewById(genderR);
        rbGenderMale = fragmentView.findViewById(radio_maleR);
        rbGenderFemale = fragmentView.findViewById(radio_femaleR);
        btnRegister = fragmentView.findViewById(register_btnR);
        tvLogin = fragmentView.findViewById(login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tes", "tes1");
                setBtRegisterClick();
            }
        });

        tvLogin.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvLoginClick();
                return true;
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkGender(group, checkedId);
            }
        });
    }

    public void checkGender(RadioGroup group, int checkedId){
        switch (checkedId){
            case radio_maleR:
                gender = "male";
                Log.e("tes", gender);
                break;
            case radio_femaleR:
                gender = "female";
                Log.e("tes", gender);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + checkedId);
        }
    }

    public void setBtRegisterClick(){
        String fullname = etFullname.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String telp = etTelp.getText().toString();
        String address = etAddress.getText().toString();

        UserTemp newUserTemp = new UserTemp(username, fullname, email, password, 2, gender, telp,address, null);

        mPresenter.performRegister(newUserTemp);
    }

    public void setTvLoginClick(){
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestRegister(final UserTemp newUserTemp, final RequestCallback<RegisterResponse> requestCallback) {
        AndroidNetworking.post(myURL.CUSTOMER_REGISTER_URL)
                .addBodyParameter("username", newUserTemp.getUsername())
                .addBodyParameter("name", newUserTemp.getName())
                .addBodyParameter("email", newUserTemp.getEmail())
                .addBodyParameter("password", newUserTemp.getPassword())
                .addBodyParameter("password_confirmation", etConfirmPassword.getText().toString())
                .addBodyParameter("gender", newUserTemp.getGender())
                .addBodyParameter("telp", newUserTemp.getTelp())
                .addBodyParameter("address", newUserTemp.getAddress())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RegisterResponse.class, new ParsedRequestListener<RegisterResponse>() {

                    @Override
                    public void onResponse(RegisterResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 401) {
                            Log.e("tesqqq", "ERROR", anError);
                            requestCallback.requestFailed("Please input a valid e-mail");
                        }else {
                            Log.e("tesww", String.valueOf(anError.getErrorCode()));
                            Log.e("teswwwww", "fdfd" + anError.getErrorBody());
                            Log.e("teswwww", "fdfdsasa" + anError.getErrorDetail());
                            requestCallback.requestFailed("Server Error !");
                        }
                    }
                });
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Register Success", Toast.LENGTH_SHORT).show();
        Log.e("tes", "success");
        redirectToLogin();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("tes", "failed");
    }
}