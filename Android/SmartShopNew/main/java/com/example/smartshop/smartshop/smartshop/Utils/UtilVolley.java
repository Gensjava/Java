package ua.smartshop.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gens on 14.03.2015.
 */
public class UtilVolley {

    private JSONArray mJSONArray;
    private String mUrl;
    private HashMap<String, String> mParams;
    private Context mContext;

    public UtilVolley(HashMap params, String url ,Context context) {
        mParams = params;
        mUrl = url;
        mContext = context;
    }

    public JSONArray getCategory(){

        RequestQueue queue = Volley.newRequestQueue(mContext);

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Map.Entry entry : mParams.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }

        String paramString = URLEncodedUtils.format(params, "utf-8");

        String url = mUrl+"/?"+paramString;
        
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                JSONObject json = response;

                int success;
                // json success tag
                try {
                    success = json.getInt(Сonstants.TAG_SUCCESS);
                 
                    if (success == 1) {
                        // если получили информацию о товаре
                         mJSONArray = json.getJSONArray(Сonstants.TAG_PRODUCT);
                        Log.i("mJSONArray VVV  ",mJSONArray.toString());

                    } else {
                        // не нашли товар по pid
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(mContext, "" + error
                      , Toast.LENGTH_LONG).show();

                Log.e("omg android"," "+ error );
            }
        });
        queue.add(jsObjRequest);

        return mJSONArray;
    }
}
