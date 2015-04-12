package com.example.smartshop.smartshop;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.Header;

/**
 * Created by Gens on 11.04.2015.
 */
public class AsyncHttp extends AsyncHttpResponseHandler {
    public AsyncHttp() {
        super();
    }
    private static final String BASE_URL = "http://api.twitter.com/1/";

    private static AsyncHttpClient client = new SyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    @Override
    public void onSuccess(final int statusCode, final Header[] headers, final byte[] responseBody) {
        super.onSuccess(statusCode, headers, responseBody);
    }
}
