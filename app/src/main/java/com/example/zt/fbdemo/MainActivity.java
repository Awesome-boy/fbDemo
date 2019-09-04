package com.example.zt.fbdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_PROFILE_ACTIVITY = 1;
    private static final int RESULT_POSTS_ACTIVITY = 2;
    private static final int RESULT_PERMISSIONS_ACTIVITY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getKeyHashValue();
        if (AccessToken.getCurrentAccessToken() == null) {
            Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
            startActivity(loginIntent);
        }

        // Make a button which leads to profile information of the user
        Button gotoProfileButton = findViewById(R.id.btn_profile);
        gotoProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Intent profileIntent = new Intent(MainActivity.this, FacebookLoginActivity
                            .class);
                    startActivityForResult(profileIntent, RESULT_PROFILE_ACTIVITY);
                } else {
                    Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                }
            }
        });

        // Make a button which leads to posts made by the user
        Button gotoPostsFeedButton = findViewById(R.id.btn_posts);
        gotoPostsFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                    startActivityForResult(loginIntent, RESULT_POSTS_ACTIVITY);
                } else {
                    Intent postsFeedIntent = new Intent(MainActivity.this, PostFeedActivity.class);
                    startActivity(postsFeedIntent);
                }
            }
        });

        // Make a button which leads to request or revoke permissions
        Button gotoPermissionsButton = findViewById(R.id.btn_permissions);
        gotoPermissionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                    startActivityForResult(loginIntent, RESULT_PERMISSIONS_ACTIVITY);
                } else {
                    Intent permissionsIntent = new Intent(MainActivity.this, PermissionsActivity
                            .class);
                    startActivity(permissionsIntent);
                }
            }
        });

        // Make a logout button
        Button fbLogoutButton = findViewById(R.id.btn_fb_logout);
        fbLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_PROFILE_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                }
                break;
            case RESULT_POSTS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Intent postFeedIntent = new Intent(MainActivity.this, PostFeedActivity.class);
                    startActivity(postFeedIntent);
                }
                break;
            case RESULT_PERMISSIONS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Intent permissionsIntent = new Intent(MainActivity.this, PermissionsActivity
                            .class);
                    startActivity(permissionsIntent);
                }
                break;
            default:
                super.onActivityResult(requestCode,resultCode, data);
        }
    }
    @SuppressLint("NewApi")
    private void getKeyHashValue() {
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(
                    "com.example.zt.fbdemo",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.d("KeyHash:", e.toString());
        }
    }

}
