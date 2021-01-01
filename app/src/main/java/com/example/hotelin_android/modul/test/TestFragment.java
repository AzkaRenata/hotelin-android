package com.example.hotelin_android.modul.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.home.HomeActivity;
import com.example.hotelin_android.modul.register.RegisterActivity;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.TokenSharedUtil;
import com.example.hotelin_android.util.myURL;

public class TestFragment extends BaseFragment<TestActivity, TestContract.Presenter> implements TestContract.View {
    TextView id,userName,userEmail,gender;
    Button btnLogout;
    TokenSharedUtil tokenSharedUtil;

    public TestFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.test_layout, container, false);
        mPresenter = new TestPresenter(this);
        mPresenter.start();

        id = fragmentView.findViewById(R.id.textViewId);
        userName = fragmentView.findViewById(R.id.textViewUsername);
        userEmail = fragmentView.findViewById(R.id.textViewEmail);
        gender = fragmentView.findViewById(R.id.textViewGender);

        mPresenter.showData();

        return fragmentView;
    }

    public void setBtLoginClick(){

    }

    public void setTvRegisterClick(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
    }

    public void redirectToRegister(){

    }

    public void saveToken(String token){
        tokenSharedUtil.setToken(token);
    }

    @Override
    public void requestProfile(final RequestCallback<User> requestCallback) {
        AndroidNetworking.get(myURL.PROFILE_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .build()
                .getAsObject(TestResponse.class, new ParsedRequestListener<TestResponse>() {
                    @Override
                    public void onResponse(TestResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        }else{
                            requestCallback.requestSuccess(response.user);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void setProfile(User user){
        id.setText(String.valueOf(user.getId()));
        userEmail.setText(user.getEmail());
        gender.setText(user.getGender());
        userName.setText(user.getUsername());
    }

    public void showFailedMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }
}