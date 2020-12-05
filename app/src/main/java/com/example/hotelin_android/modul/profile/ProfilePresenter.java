package com.example.hotelin_android.modul.profile;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.hotel_detail.HotelDetail;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.myURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfilePresenter implements ProfileContract.ProfilePresenter {
    ProfileContract.ProfileView view;


    public ProfilePresenter(ProfileContract.ProfileView view) {
        this.view = view;
    }

    @Override
    public void start() {

    }



    @Override
    public void fetchProfile(String bearerToken) {
        final User[] result = new User[1];

        JSONObjectRequestListener requestListener = new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject userResponse = response.getJSONObject("user");

                    int id = userResponse.getInt("id");
                    int user_level = userResponse.getInt("user_level");

                    String username = userResponse.getString("username");
                    String name = userResponse.getString("name");
                    String email = userResponse.getString("email");
//                    String password = userResponse.getString("password");
                    String gender = userResponse.getString("gender");
                    String telp = userResponse.getString("telp");
                    String address = userResponse.getString("address");
                    String user_picture = userResponse.getString("user_picture");

                    result[0] = new User(id, username, name, email, null, user_level, gender, telp, address, user_picture);

                    view.setProfileData(result[0]);

                }catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.e("ProfilePresenter : ", "ga dapet respon om");
            }
        };

        AndroidNetworking
                .get(myURL.PROFILE_URL)
                .addHeaders("Authorization", "Bearer " + bearerToken)
                .build()
                .getAsJSONObject(requestListener);
    }

    @Override
    public void showData(){
        view.requestProfile(new RequestCallback<User>() {
            @Override
            public void requestSuccess(User data) {
                view.setProfile(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.showFailedMessage(errorMessage);
            }
        });
    }
}
