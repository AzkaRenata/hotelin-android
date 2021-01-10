package com.example.hotelin_android.modul.profile_edit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.hotelin_android.R;
import com.example.hotelin_android.base.BaseFragment;
import com.example.hotelin_android.model.User;
import com.example.hotelin_android.modul.profile.ProfileActivity;
import com.example.hotelin_android.util.AsyncTaskLoadImage;
import com.example.hotelin_android.util.RequestCallback;
import com.example.hotelin_android.util.SharedPreferences.TokenSharedUtil;
import com.example.hotelin_android.util.SharedPreferences.UserSharedUtil;
import com.example.hotelin_android.util.UtilProvider;
import com.example.hotelin_android.util.myURL;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.hotelin_android.R.id.female_edit_rb;
import static com.example.hotelin_android.R.id.male_edit_rb;

public class ProfileEditFragment extends BaseFragment<ProfileEditActivity, ProfileEditContract.Presenter> implements ProfileEditContract.View, View.OnClickListener {
    private EditText etUsername;
    private EditText etName;
    private EditText etEmail;
    private EditText etTelp;
    private EditText etAddress;
    private RadioButton maleRB;
    private RadioButton femaleRB;
    private Button btnConfirm;
    private CircleImageView civPhoto;

    private String gender;
    private File imageUpload;

    private final TokenSharedUtil tokenSharedUtil;
    private final UserSharedUtil userSharedUtil;

    public ProfileEditFragment() {
        tokenSharedUtil = UtilProvider.getTokenSharedUtil();
        userSharedUtil = UtilProvider.getUserSharedUtil();
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        mPresenter = new ProfileEditPresenter(this, activity);
        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit_profile_photo_civ) choosePicture();
        if (v.getId() == R.id.edit_profile_save_edit_btn) saveBtnClick();
        if (v.getId() == R.id.edit_profile_photoConfirm_btn) uploadImage();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            imageUpload = ImagePicker.Companion.getFile(data);
            civPhoto.setImageURI(Uri.parse(imageUpload.getPath()));
            btnConfirm.setVisibility(View.VISIBLE);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setItems() {
        etUsername = fragmentView.findViewById(R.id.username_edit_et);
        etName = fragmentView.findViewById(R.id.name_edit_et);
        etEmail = fragmentView.findViewById(R.id.email_edit_et);
        etTelp = fragmentView.findViewById(R.id.telp_edit_et);
        etAddress = fragmentView.findViewById(R.id.address_edit_et);
        RadioGroup rgGender = fragmentView.findViewById(R.id.gender_edit_rg);
        maleRB = fragmentView.findViewById(male_edit_rb);
        femaleRB = fragmentView.findViewById(female_edit_rb);
        Button btnSave = fragmentView.findViewById(R.id.edit_profile_save_edit_btn);
        civPhoto = fragmentView.findViewById(R.id.edit_profile_photo_civ);
        btnConfirm = fragmentView.findViewById(R.id.edit_profile_photoConfirm_btn);

        civPhoto.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkGender(checkedId);
            }
        });
    }

    @Override
    public void setProfile() {
        etUsername.setText(userSharedUtil.getUser().getUsername());
        etName.setText(userSharedUtil.getUser().getName());
        etEmail.setText(userSharedUtil.getUser().getEmail());
        etTelp.setText(userSharedUtil.getUser().getTelp());
        etAddress.setText(userSharedUtil.getUser().getAddress());

        if (userSharedUtil.getUser().getGender() != null) {
            if (userSharedUtil.getUser().getGender().equalsIgnoreCase("female")) {
                femaleRB.setChecked(true);
                gender = "female";
            }

            if (userSharedUtil.getUser().getGender().equalsIgnoreCase("male")) {
                maleRB.setChecked(true);
                gender = "male";
            }
        }
    }

    @Override
    public void setPicture() {
        if(userSharedUtil.getUser().getUser_picture() != null){
            String url = myURL.getImageUrl() + userSharedUtil.getUser().getUser_picture();
            new AsyncTaskLoadImage(civPhoto).execute(url);
        }
    }

    private void uploadImage() {
        mPresenter.performUpdatePicture();
        btnConfirm.setVisibility(View.INVISIBLE);
    }

    private void choosePicture() {
        ImagePicker.Companion.with(this)
                .cropSquare()
                .compress(2048)
                .maxResultSize(1080, 1080)
                .start();
    }

    @SuppressLint("NonConstantResourceId")
    public void checkGender(int checkedId) {
        switch (checkedId) {
            case male_edit_rb:
                gender = "male";
                break;
            case female_edit_rb:
                gender = "female";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + checkedId);
        }
    }

    public void saveBtnClick() {
        String username = etUsername.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String address = etAddress.getText().toString();
        String telp = etTelp.getText().toString();

        User user = new User(username, name, email, gender, telp, address);

        mPresenter.performProfileEdit(user);
    }

    @Override
    public void redirectToProfile() {
        Intent intent = new Intent(activity, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestEditProfile(final User user, final RequestCallback<ProfileEditResponse> requestCallback) {
        AndroidNetworking.post(myURL.EDIT_PROFILE_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addBodyParameter("username", user.getUsername())
                .addBodyParameter("name", user.getName())
                .addBodyParameter("email", user.getEmail())
                .addBodyParameter("gender", user.getGender())
                .addBodyParameter("telp", user.getTelp())
                .addBodyParameter("address", user.getAddress())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProfileEditResponse.class, new ParsedRequestListener<ProfileEditResponse>() {
                    @Override
                    public void onResponse(ProfileEditResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        } else {
                            requestCallback.requestSuccess(response, getString(R.string.edit_profile_message));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() == 401) {
                            requestCallback.requestFailed(getString(R.string.error_email_valid));
                        } else {
                            requestCallback.requestFailed(anError.getMessage());
                        }
                    }
                });
    }

    @Override
    public void requestUpdatePicture(final RequestCallback<ProfileEditResponse> requestCallback) {
        AndroidNetworking.upload(myURL.EDIT_PROFILE_PICTURE_URL)
                .addHeaders("Authorization", "Bearer " + tokenSharedUtil.getToken())
                .addMultipartFile("user_picture", imageUpload)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {}
                })
                .getAsObject(ProfileEditResponse.class, new ParsedRequestListener<ProfileEditResponse>() {
                    @Override
                    public void onResponse(ProfileEditResponse response) {
                        if (response == null) {
                            requestCallback.requestFailed(getString(R.string.error_null_response));
                        } else {
                            requestCallback.requestSuccess(response,getString(R.string.edit_profile_pic_message) );
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
    }

    @Override
    public void saveUser(User user) {
        userSharedUtil.clear();
        userSharedUtil.setUser(user);
    }

    @Override
    public void setPresenter(ProfileEditContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
