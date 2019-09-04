package com.example.zt.fbdemo.callbacks;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
public class PermissionCallback {
    public interface IPermissionResponse {
        void onCompleted(GraphResponse response);
    }

    private GraphRequest.Callback mCallback;

    public PermissionCallback(final IPermissionResponse caller) {

        mCallback = new GraphRequest.Callback() {

            // Handled by PermissionsActivity
            @Override
            public void onCompleted(GraphResponse response) {
                caller.onCompleted(response);
            }
        };
    }

    public GraphRequest.Callback getCallback() {
        return mCallback;
    }
}
