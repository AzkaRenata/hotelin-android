package com.example.hotelin_android.modul.profile_edit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.UserTemp;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.modul.test.TestResponse;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.myURL;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.hotelin_android.R.id.female_edit_rb;
import static com.example.hotelin_android.R.id.male_edit_rb;

public class ProfileEditFragment extends BaseFragment<ProfileEditActivity, ProfileEditContract.Presenter> implements ProfileEditContract.View, View.OnClickListener {
    TokenSharedUtil tokenSharedUtil;

    TextView usernameET;
    TextView nameET;
    TextView emailET;
    TextView telpET;
    TextView addressET;
    RadioGroup genderRG;
    RadioButton maleRB;
    RadioButton femaleRB;
    Button saveBTN;
    Button btnConfirm;
    String gender;
    String password;
    File imageUpload;
    CircleImageView civPhoto;
    UserTemp userTemp;

    public ProfileEditFragment(TokenSharedUtil tokenSharedUtil) {
        this.tokenSharedUtil = tokenSharedUtil;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.edit_profile_activity, container, false);
        mPresenter = new ProfileEditPresenter(this);
        mPresenter.start();

        usernameET = fragmentView.findViewById(R.id.username_edit_et);
        nameET = fragmentView.findViewById(R.id.name_edit_et);
        emailET = fragmentView.findViewById(R.id.email_edit_et);
        telpET = fragmentView.findViewById(R.id.telp_edit_et);
        addressET = fragmentView.findViewById(R.id.address_edit_et);
        genderRG = fragmentView.findViewById(R.id.gender_edit_rg);
        maleRB = fragmentView.findViewById(male_edit_rb);
        femaleRB = fragmentView.findViewById(female_edit_rb);
        saveBTN = fragmentView.findViewById(R.id.edit_profile_save_edit_btn);
        civPhoto = fragmentView.findViewById(R.id.edit_profile_photo_civ);
        btnConfirm = fragmentView.findViewById(R.id.edit_profile_photoConfirm_btn);

        mPresenter.showData();
        civPhoto.setOnClickListener(this);
        saveBTN.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkGender(group, checkedId);
            }
        });

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit_profile_photo_civ) choosePicture();
        if (v.getId() == R.id.edit_profile_save_edit_btn) saveBtnClick();
        if (v.getId() == R.id.edit_profile_photoConfirm_btn) uploadImage();

    }

    private void uploadImage() {
        mPresenter.performUpdatePicture();
        btnConfirm.setVisibility(View.INVISIBLE);
    }

    private void choosePicture() {
        ImagePicker.Companion.with(this)
                .cropSquare()                    //Crop image(Optional), Check Customization for more option
                .compress(2048)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == activity.RESULT_OK) {
//            Uri imageUri = data.getData();
            imageUpload = ImagePicker.Companion.getFile(data);
            civPhoto.setImageURI(Uri.parse(imageUpload.getPath()));
//            Glide.with(fragmentView)
//                    .load(imageUpload)
//                    .into(civPhoto);
        btnConfirm.setVisibility(View.VISIBLE);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }


    }

    public void checkGender(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case male_edit_rb:
                gender = "male";
                Log.e("tes", gender);
                break;
            case female_edit_rb:
                gender = "female";
                Log.e("tes", gender);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + checkedId);
        }
    }

    public void saveBtnClick() {
        String username = usernameET.getText().toString();
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String address = addressET.getText().toString();
        String telp = telpET.getText().toString();

        userTemp = new UserTemp(username, name, email, password, 2, gender, telp, address, null);
        mPresenter.performRegister(userTemp);
    }

    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void editUser(final UserTemp newUserTemp, final RequestCallback<UserTemp> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_USER_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("username", newUserTemp.getUsername())
                .addBodyParameter("name", newUserTemp.getName())
                .addBodyParameter("email", newUserTemp.getEmail())
                .addBodyParameter("gender", newUserTemp.getGender())
                .addBodyParameter("telp", newUserTemp.getTelp())
                .addBodyParameter("address", newUserTemp.getAddress())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(TestResponse.class, new ParsedRequestListener<TestResponse>() {
                    @Override
                    public void onResponse(TestResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                        } else {
                            requestCallback.requestSuccess(response.userTemp, "tes");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 401) {
                            Log.e("tesqqq", "ERROR", anError);
                            requestCallback.requestFailed("Please input a valid e-mail");
                        } else {
                            Log.e("tesww", String.valueOf(anError.getErrorCode()));
                            Log.e("teswwwww", "fdfd" + anError.getErrorBody());
                            Log.e("teswwww", "fdfdsasa" + anError.getErrorDetail());
                            requestCallback.requestFailed("Server Error !");
                        }
                    }
                });
    }

    @Override
    public void updatePicture(final RequestCallback<UserTemp> requestCallback) {
        AndroidNetworking.upload(myURL.UPDATE_USER_PICTURE_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addMultipartFile("user_picture", imageUpload)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .getAsObject(TestResponse.class, new ParsedRequestListener<TestResponse>() {
                    @Override
                    public void onResponse(TestResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        } else {
                            requestCallback.requestSuccess(response.userTemp,"tes" );
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }


    @Override
    public void requestProfile(final RequestCallback<UserTemp> requestCallback) {
        AndroidNetworking.get(myURL.PROFILE_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .build()
                .getAsObject(TestResponse.class, new ParsedRequestListener<TestResponse>() {
                    @Override
                    public void onResponse(TestResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        } else {
                            requestCallback.requestSuccess(response.userTemp, "tes");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("tag", "error gan" + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    public void setProfile(UserTemp userTemp) {
        Glide.with(fragmentView)
                .load(myURL.getImageUrl()+ userTemp.getUser_picture())
                .signature(new ObjectKey(System.currentTimeMillis()))
                .error(R.drawable.ic_profile_picture)
                .into(civPhoto);
        usernameET.setText(userTemp.getUsername());
        nameET.setText(userTemp.getName());
        emailET.setText(userTemp.getEmail());
        telpET.setText(userTemp.getTelp());
        addressET.setText(userTemp.getAddress());
        password = userTemp.getPassword();

        if (userTemp.getGender() != null) {
            if (userTemp.getGender().equalsIgnoreCase("female")) {
                femaleRB.setChecked(true);
                gender = "female";
            }

            if (userTemp.getGender().equalsIgnoreCase("male")) {
                maleRB.setChecked(true);
                gender = "male";
            }
        }
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), "Register Success", Toast.LENGTH_SHORT).show();
        Log.e("tes", "success");
        redirectToProfile();
    }

    @Override
    public void setPicture(UserTemp userTemp) {
        Glide.with(fragmentView)
                .load(myURL.getImageUrl()+ userTemp.getUser_picture())
                .error(R.drawable.ic_profile_picture)
                .signature(new ObjectKey(System.currentTimeMillis()))
                .into(civPhoto);
        Log.e("setPict", imageUpload.getPath());
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("tes", "failed");
    }

    @Override
    public void setPresenter(ProfileEditContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
