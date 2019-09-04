package com.example.zt.fbdemo.requests;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;

public class PermissionRequest {
    private static final String PERMISSIONS_ENDPOINT = "/me/permissions";
    private static final String APP = "app";

    public static void makeRevokePermRequest(String permission, GraphRequest.Callback callback) {
        String graphPath;
        if (permission.equals(APP)) {
            graphPath = PERMISSIONS_ENDPOINT;
        } else {
            graphPath = PERMISSIONS_ENDPOINT + "/" + permission;
        }

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                graphPath,
                callback
        );
        request.setHttpMethod(HttpMethod.DELETE);
        request.executeAsync();
    }
}
