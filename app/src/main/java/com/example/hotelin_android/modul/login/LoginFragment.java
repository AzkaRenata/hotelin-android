package com.example.hotelin_android.modul.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.register.RegisterActivity;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.SharedPreferencesUtil;
import com.example.hotelin_android.util.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    EditText username;
    EditText userpassword;
    Button btnLogin;
    TextView tvRegister;
    private Object SharedPreferencesUtil;
//    static SharedPreferencesUtil;

    public LoginFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.login_activity, container, false);
        mPresenter = new LoginPresenter(this);
        mPresenter.start();

        username = fragmentView.findViewById(R.id.username);
        userpassword = fragmentView.findViewById(R.id.password);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tvRegister = fragmentView.findViewById(R.id.register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                setBtnLoginClick();
            }
        });
        tvRegister.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTvRegisterClick();
                return true;
            }
        });
        return fragmentView;
    }

    private void setBtnLoginClick() throws JSONException {
        loginUser();
        getUser();
        mPresenter.performLogin();
    }

    private void loginUser() {
        final String email = username.getText().toString();
        final String password = userpassword.getText().toString();


        URL url = new URL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getBaseUrl() + "/api/user/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("response", jsonObject.toString());

                            if (jsonObject.getString("success").equals(201)) {
                                JSONObject userData = jsonObject.getJSONObject("user");
                                SharedPreferencesUtil = new SharedPreferencesUtil().setToken(jsonObject);
                            } else if (jsonObject.getString("success").equals("false")) {
                                Toast.makeText(getContext(), "Employee number or password is not correct", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse errorRes = error.networkResponse;
                        String errorMsg = "";
                        if(errorRes != null && errorRes.data != null){
                            try {
                                errorMsg = new String(errorRes.data,"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "Server didn't respond", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (!errorMsg.equals("")) {
                            try {
                                JSONObject jsonObject = new JSONObject(errorMsg);
                                Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Server didn't respond", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.new RequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getUser() throws JSONException {
        JSONObject token;
        JSONObject jsonObject;

        URL url = new URL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getBaseUrl() + "/api/user/get",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(token) {
                        try {
                            token = jsonObject.getJSONObject("token");
                            jsonObject = (JSONObject) SharedPreferencesUtil().getToken();
                            Log.d("token", jsonObject.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse errorRes = error.networkResponse;
                        String errorMsg = "";
                        if(errorRes != null && errorRes.data != null){
                            try {
                                errorMsg = new String(errorRes.data,"UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "Server didn't respond", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (!errorMsg.equals("")) {
                            try {
                                JSONObject jsonObject = new JSONObject(errorMsg);
                                Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Server didn't respond", Toast.LENGTH_SHORT).show();
                        }
                    }

                })
        {
            JSONObject userData = jsonObject.getJSONObject("user");
            User user = new User(
                    userData.getInt("id"),
                    userData.getString("name"),
                    userData.getString("username"),
                    userData.getString("email"),
                    userData.getString("user_level"),
                    userData.getString("gender"),
                    userData.getString("telp"),
                    userData.getString("user_picture"),
                    userData.getString("email_verified_at"),
                    userData.getString("created_at"),
                    userData.getString("updated_at")
        };
        RequestQueue requestQueue = Volley.new RequestQueue(this);
        requestQueue.add(stringRequest);
    };



    public void setTvRegisterClick(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToList() {
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    public void moveToRegister(LoginContract.View v){
        mPresenter.performMove(v);
    }

    public void redirectToRegister(){

    }
}