package com.example.zt.fbdemo.callbacks;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.share.Sharer;

public class PublishPostCallback {
    public interface IPublishPostResponse {
        void onPublishCompleted();
    }

    private GraphRequest.Callback mCallback;

    private FacebookCallback<Sharer.Result> mShareCallback;

    public PublishPostCallback(final IPublishPostResponse caller) {

        mCallback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                // Handled by PostFeedActivity
                caller.onPublishCompleted();
            }
        };

        mShareCallback = new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                // Handled by PostFeedActivity
                caller.onPublishCompleted();
            }

            @Override
            public void onCancel() {
                // Handle user cancel ...
            }

            @Override
            public void onError(FacebookException error) {
                // Handle exception ...
            }
        };
    }

    public GraphRequest.Callback getGraphRequestCallback() {
        return mCallback;
    }

    public FacebookCallback<Sharer.Result> getShareCallback() {
        return mShareCallback;
    }
}
