package com.example.hotelin_android.modul.itemDetails;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.util.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemDetailsFragment extends BaseFragment<ItemDetailsActivity, ItemDetailsContract.Presenter> implements ItemDetailsContract.View {
    TextView nama;
    Button btnLogin;

    public ItemDetailsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.item_detail_activity, container, false);
        mPresenter = new ItemDetailsPresenter(this);
        mPresenter.start();

        nama = fragmentView.findViewById(R.id.nama);
        btnLogin = fragmentView.findViewById(R.id.login_btn);

        private void getListEbook(String id){
            URL url = new URL();

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Fetching data from server...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url.getBaseUrl() + "/room/detail/" + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString("status").equals("success")) {
                            JSONObject data = new JSONObject(jsonObject.getString("data"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Server did not respond", Toast.LENGTH_SHORT).show();
                }
            })
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        setTitle("Sign in");
        return fragmentView;
    }

    public void setBtLoginClick(){
        mPresenter.performLogin();
    }

    @Override
    public void setPresenter(ItemDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToList() {

    }
}