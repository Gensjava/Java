package ua.smartshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 03.03.2015.
 */
public class ProductDiscriptionFragment extends android.support.v4.app.Fragment {

    JSONArray productsArray = null;
    // адрес
    View rootView;

    String idItem;
    String description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.product_discription, container,
                false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            idItem = bundle.getString(Сonstants.VALUE_KEY_ITEM_ID);
            GetProductDescriptionTask();
        }
        return  rootView;
    }
    // информации о товаре
    void GetProductDescriptionTask (){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair(Сonstants.VALUE_KEY_ITEM_ID, idItem));
        String paramString = URLEncodedUtils.format(params, "utf-8");

        String url = Сonstants.url_product_description+"/?"+paramString;
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
                        productsArray = json.getJSONArray(Сonstants.TAG_PRODUCT);

                        // получим первый объект из массива JSON Array
                        JSONObject p = productsArray.getJSONObject(0);
                        description = p.getString(Сonstants.TAG_DISCRIPTION);
                    } else {
                        // не нашли товар по pid
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                WebView webDescription = (WebView) rootView.findViewById(R.id.textView_full_discription);
                WebSettings settings = webDescription.getSettings();
                // включаем поддержку JavaScript
                settings.setJavaScriptEnabled (true);
                settings.setDefaultTextEncodingName("UTF-8");
                webDescription.loadDataWithBaseURL(null, description, "text/html", "en_US", null);
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
