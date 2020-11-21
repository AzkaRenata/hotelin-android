package com.example.hotelin_android.modul.login;

<<<<<<< Updated upstream
import android.annotation.SuppressLint;
import android.content.Intent;
=======
import android.app.ProgressDialog;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< Updated upstream
import android.widget.TextView;
=======
import android.widget.Toast;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
import com.example.hotelin_android.modul.register.RegisterActivity;
=======
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
>>>>>>> Stashed changes

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    private String usertoken;
    EditText username;
    EditText userpassword;

    Button btnLogin;
    TextView tvRegister;

    public LoginFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.login_activity, container, false);
        mPresenter = new LoginPresenter(this);
        mPresenter.start();

<<<<<<< Updated upstream
        etUsername = fragmentView.findViewById(R.id.username);
        etPassword = fragmentView.findViewById(R.id.password);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
        tvRegister = fragmentView.findViewById(R.id.register);
=======
        usertoken = "";
        username = fragmentView.findViewById(R.id.username);
        userpassword = fragmentView.findViewById(R.id.password);
        btnLogin = fragmentView.findViewById(R.id.login_btn);
>>>>>>> Stashed changes

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                setBtnLoginClick();
            }
        });

<<<<<<< Updated upstream
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

    public void moveToRegister(LoginContract.View v){
        mPresenter.performMove(v);
    }

    public void setBtLoginClick(){
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();
=======
        setTitle("Sign in");
        return fragmentView;
    }

    private void setBtnLoginClick() {
        String email = username.getText().toString();
        String password = userpassword.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);

        if (TextUtils.isEmpty(email)) {
            username.setError("Please input your employee number");
            userpassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            userpassword.setError("Please input your password");
            username.requestFocus();
            return;
        }
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

>>>>>>> Stashed changes
        mPresenter.performLogin();
    }
}

    private void loginUser() {
        final String email = username.getText().toString();
        final String password = userpassword.getText().toString();
        final String token = getUserToken();

        URL url = new URL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getBaseUrl() + "/api/user/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                            progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("response", jsonObject.toString());



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                //jika respon error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                getTokenFCM();
                params.put("email", email);
                params.put("password", password);
//                Log.d("user_parameters", params.toString());
//                Log.d("token_fcm", user_token);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String getUserToken() {
        return this.usertoken;
    }

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

    }

    public void redirectToRegister(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }
}