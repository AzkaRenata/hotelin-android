package com.example.hotelin_android.modul.profile_edit;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.util.myURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileEditPresenter implements ProfileEditContract.ProfileEditPresenter {
    ProfileEditContract.ProfileEditView view;
    String bearerToken;


    public ProfileEditPresenter(ProfileEditContract.ProfileEditView view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void fetchEditProfile(String bearerToken) {
        this.bearerToken = bearerToken;

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
    public void updateUserData(User user) {

        JSONObjectRequestListener requestListener = new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Ngecek responnya ada usernya ato engga (kalo berhasil dapet user)
                    JSONObject updatedUserJSON = response.getJSONObject("user");

                    view.redirectToProfile();
                }catch (JSONException exception) {
                    try {
                        Log.e("ON UPDATEUSER : ", "ga dapet user");
                        String responseStatus = response.getString("status");
                    }catch (JSONException anotherException) {
                        Log.e("ON UPDATEUSER : ", "ga dapet status");
                        anotherException.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.e("ON UPDATEUSER : ", "ga dapet respon");
            }
        };

        AndroidNetworking
                .post(myURL.UPDATE_USER_URL)
                .addHeaders("Authorization", "Bearer " + this.bearerToken)
                .addBodyParameter("username", user.getUsername())
                .addBodyParameter("name", user.getName())
                .addBodyParameter("email", user.getEmail())
                .addBodyParameter("user_level", String.valueOf(user.getUser_level()))
                .addBodyParameter("gender", user.getGender())
                .addBodyParameter("telp", user.getTelp())
                .addBodyParameter("address", user.getAddress())
                .addBodyParameter("user_picture", user.getUser_picture())
                .build()
                .getAsJSONObject(requestListener);

    }
}
