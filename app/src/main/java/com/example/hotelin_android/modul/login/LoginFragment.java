package com.example.hotelin_android.modul.login;

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
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.modul.register.RegisterActivity;
import com.example.hotelin_android.util.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    EditText username;
    EditText userpassword;
    Button btnLogin;
    TextView tvRegister;
    String authToken;
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
                setBtLoginClick();
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
//        getUser();
//        mPresenter.performLogin();
    }

    private void loginUser() {
        String email = username.getText().toString();
        String password = userpassword.getText().toString();

        Log.e("SOMETHING", email);
        Log.e("SOMETHING", password);

        String API_LOGIN_ROUTE = "user/login";

        VolleyService service = new VolleyService();

        // Create Listener to respond what to do after the data has been fetched.
        Response.Listener<JSONObject> onSuccessListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseData;

                try {
                    responseData = response.getString("token");

                    // TODO : Masukin token ke SharedPreferences.

                    authToken = responseData;

//                    Toast.makeText(activity, responseData, Toast.LENGTH_SHORT).show();
                }
                catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }
        };

        // Create Listener to respond if Volley failed to fetch data.
        Response.ErrorListener onErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode == 400) {
                    Toast.makeText(activity, "Input lagi bro", Toast.LENGTH_SHORT).show();
                }

            }
        };

        // Create Map to store POST parameters.
        Map<String, String> postParams = new HashMap<String, String>();
        postParams.put("email", email);
        postParams.put("password", password);

        // Create Header params
        Map<String, String> headerParams = new HashMap<>();

        // Create the service with provided details.
        service.getService(getContext(), Request.Method.POST, API_LOGIN_ROUTE, onSuccessListener, onErrorListener, postParams, headerParams);

        // Start the service
        service.startService();
    }

    private void getUser() {
        String API_LOGIN_ROUTE = "user";

        VolleyService service = new VolleyService();

        // Create Listener to respond what to do after the data has been fetched.
        Response.Listener<JSONObject> onSuccessListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseData;

                try {

                    JSONObject json = response.getJSONObject("user");

                    responseData = json.getString("name");

                    // TODO : Masukin token ke SharedPreferences.

                    Toast.makeText(activity, responseData, Toast.LENGTH_SHORT).show();
                }
                catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }
        };

        // Create Listener to respond if Volley failed to fetch data.
        Response.ErrorListener onErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode == 400) {
                    Toast.makeText(activity, "Input lagi bro", Toast.LENGTH_SHORT).show();
                }

            }
        };

        // Create Map to store POST parameters.
        Map<String, String> postParams = new HashMap<String, String>();

        // Create Header params
        Map<String, String> headerParams = new HashMap<>();
//        headerParams.put("Authorization", "Bearer " + authToken);

        // Create the service with provided details.
        service.getService(getContext(), Request.Method.GET, API_LOGIN_ROUTE, onSuccessListener, onErrorListener, postParams, headerParams);

        // Start the service
        service.startService();
    }

    public void setBtLoginClick(){
        loginUser();
//        getUser();
//        mPresenter.performLogin();
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
    public void redirectToHome() {
//        Intent intent = new Intent(activity, HomeActivity.class);
//        startActivity(intent);
    }

    public void redirectToRegister(){

    }
}