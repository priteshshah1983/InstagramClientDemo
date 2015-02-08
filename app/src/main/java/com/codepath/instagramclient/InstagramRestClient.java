package com.codepath.instagramclient;

import com.loopj.android.http.*;

public class InstagramRestClient {
    private static final String BASE_URL = "https://api.instagram.com/v1/";
    private static final String CLIENT_ID = "ecacb90fa9fc4928a7b10181e4ec0184";


    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        // Default parameters
        if (params == null) {
            params = new RequestParams();
        }
        params.put("client_id", CLIENT_ID);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
