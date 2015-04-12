package com.example.smartshop.smartshop;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gens on 10.04.2015.
 */
public class UtilAsyncHttpClient extends AsyncHttpClient {

    private JSONArray mJSONArray;   // массив товаров JSONArray
    private String mUrl;
    private String  mTag;
    private HashMap<String, String> mParams;
    private List<HashMap> mArrayValues = new ArrayList<HashMap>();
    private TypeRequest mTypeRequest;
    private onSomeEventListenerAsync onSomeEventListenerAsync ;
    private Activity mContext;

    public UtilAsyncHttpClient(final HashMap<String, String> params, final String url, final String tag,  final TypeRequest typeRequest ,final Activity mainActivity) {

        mParams = params;
        mUrl = url;
        mTag = tag;
        mContext = mainActivity;
        mTypeRequest = typeRequest;
    }

    void  getAsyncArrayValues()  {

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Map.Entry entry : mParams.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }

        String paramString = URLEncodedUtils.format(params, "utf-8");

        String url = mUrl+"/?"+paramString;

        get( url ,
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(JSONObject json) {

                        mContext.setProgressBarIndeterminateVisibility(true);
                        // Display a "Toast" message
                        // to announce your success
                        //Toast.makeText(mContext, "Success!",
                            //    Toast.LENGTH_LONG).show();

                       // получаем массив товаров
                        //Log.i("UtilAsyncHttpClient 1 ",""+json.toString());
                        int success = 0;
                        try {
                            success = json.getInt(Сonstants.TAG_SUCCESS);

                            if (success == 1) {     // товар найден

                                  mJSONArray = json.getJSONArray(Сonstants.TAG_PRODUCT);

                                onSomeEventListenerAsync = (onSomeEventListenerAsync) mContext;
                                onSomeEventListenerAsync.someEventAsync(mTag ,mJSONArray );

                            } else {
                                // не нашли товар по pid
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                        mContext.setProgressBarIndeterminateVisibility(false);
                        // Display a "Toast" message
                        // to announce the failure
                        Toast.makeText(mContext, "Error: " + statusCode + " " +
                                throwable.getMessage(), Toast.LENGTH_LONG).show();

                        // Log error message
                        // to help solve any problems
                        Log.e("omg android", statusCode + " " + throwable.getMessage());
                    }
                }
        );


    }

    public interface onSomeEventListenerAsync {
        public void someEventAsync(String tag, JSONArray mPJSONArray );
    }

    public List<HashMap> getArrayValues() {
        return mArrayValues;
    }

    public JSONArray getJSONArray() {
        return mJSONArray;
    }

    public void setJSONArray(final JSONArray JSONArray) {
        mJSONArray = JSONArray;
    }
}
