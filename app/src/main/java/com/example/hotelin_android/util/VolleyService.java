package com.example.hotelin_android.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Mangat kelompok 4 ~
// -ub

public class VolleyService {
    public String API_URL = "http://localhost:8000/api/";
    public String ERROR_TAG = "ERROR AT VOLLEYPOSTSERVICE : ";
    public RequestQueue queue;
    public JsonObjectRequest postRequest;

    public void getService (final Context context, int requestMethod, String API_LOGIN_ROUTE, Response.Listener<JSONObject> onSuccessListener, Response.ErrorListener onErrorListener, final Map<String, String> postParams, final Map<String, String> headerParams) {
        // Instantiate the RequestQueue.
        this.queue = Volley.newRequestQueue(context);

        this.postRequest = new JsonObjectRequest(requestMethod, API_URL + API_LOGIN_ROUTE, postParams.isEmpty() ? null : new JSONObject(postParams), onSuccessListener, onErrorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headerParams.isEmpty()) {
                    return super.getHeaders();
                } else {
                    return headerParams;
                }
            }
        };
    }

    public void startService () {
        this.queue.add(this.postRequest);
    }

}