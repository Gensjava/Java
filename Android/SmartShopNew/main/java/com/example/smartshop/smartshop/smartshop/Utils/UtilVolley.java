package ua.smartshop.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ua.smartshop.R;

/**
 * Created by Gens on 14.03.2015.
 */
public class UtilVolley {

     Context mActivity;
    
    // массив товаров JSONArray
    private JSONArray mProducts;

    String mIdItem;


    public UtilVolley(Activity activity, String idItem) {
        mActivity = activity;
        mIdItem = idItem;
    }

    public void getCategory(){

        RequestQueue queue = Volley.newRequestQueue(mActivity);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("idItem", mIdItem));

        String paramString = URLEncodedUtils.format(params, "utf-8");
        String url = mActivity.getString(R.string.url_get_category_products)+"/?"+paramString;
        
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                JSONObject json = response;

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });
        queue.add(jsObjRequest);
    }

}
