package com.example.smartshop.smartshop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gens on 10.04.2015.
 */
public class UtilSyncHttpClient extends SyncHttpClient {

    private static final String QUERY_URL = "QUERY_URL" ;
    public  ArrayList<Object> mCategory = new ArrayList<Object>();
    //http://loopj.com/android-async-http/doc/

    // массив товаров JSONArray
    //private JSONArray mProducts;
    private JSONArray mJSONArray;
    // Создаем объект JSON Parser
    private JSONParser mJParser = new JSONParser();
    private String mUrl;
    private String  mTag;
    private HashMap<String, String> mParams;
    private List<HashMap> mArrayValues = new ArrayList<HashMap>();
    private TypeRequest mTypeRequest;
    // Progress Dialog
    private ProgressDialog pDialog;
    private onSomeEventListenerAsync onSomeEventListenerAsync ;

    Activity mContext;

    public UtilSyncHttpClient(final HashMap<String, String> params, final String url, final String tag, final TypeRequest typeRequest, final Activity mainActivity) {

        mParams = params;
        mUrl = url;
        mTag = tag;
        mContext = mainActivity;
        mTypeRequest = typeRequest;
    }

    public JSONArray getSyncArrayValues()  {

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
                        Toast.makeText(mContext, "Success!",
                                Toast.LENGTH_LONG).show();

                        // получаем массив товаров
                        //Log.i("UtilAsyncHttpClient 1 ",""+json.toString());
                        int success = 0;
                        try {
                            success = json.getInt(Сonstants.TAG_SUCCESS);

                            if (success == 1) {     // товар найден

                                mJSONArray = json.getJSONArray(Сonstants.TAG_PRODUCT);


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

        return mJSONArray;
    }

    public interface onSomeEventListenerAsync {
        public void someEventAsync(String key, String tag, JSONArray mPJSONArray);
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
