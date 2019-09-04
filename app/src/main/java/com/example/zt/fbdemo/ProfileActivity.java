package com.example.zt.fbdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zt.fbdemo.callbacks.GetUserCallback;
import com.example.zt.fbdemo.entities.User;
import com.example.zt.fbdemo.requests.UserRequest;
import com.facebook.drawee.view.SimpleDraweeView;


public class ProfileActivity extends Activity implements GetUserCallback.IGetUserResponse {
    private SimpleDraweeView mProfilePhotoView;
    private TextView mName;
    private TextView mId;
    private TextView mEmail;
    private TextView mPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfilePhotoView = findViewById(R.id.profile_photo);
        mName = findViewById(R.id.name);
        mId = findViewById(R.id.id);
        mEmail = findViewById(R.id.email);
        mPermissions = findViewById(R.id.permissions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserRequest.makeUserRequest(new GetUserCallback(ProfileActivity.this).getCallback());
    }

    @Override
    public void onCompleted(User user) {
        mProfilePhotoView.setImageURI(user.getPicture());
        mName.setText(user.getName());
        mId.setText(user.getId());
        if (user.getEmail() == null) {
            mEmail.setText(R.string.no_email_perm);
            mEmail.setTextColor(Color.RED);
        } else {
            mEmail.setText(user.getEmail());
            mEmail.setTextColor(Color.BLACK);
        }
        mPermissions.setText(user.getPermissions());
    }
}
