package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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

/**
 * Created by Gens on 03.03.2015.
 */
public class FragmentItem extends android.support.v4.app.Fragment  {

    JSONArray productsArray = null;
    ArrayList<Product> mMroducts = new ArrayList<Product>();

    AdapterItem itemAdapter;
    // адрес
    View rootView;

    String name;
    double price;
    public static String idItem;
    String kodItem;
    String description;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.actyvity_item, container,
                false);
        mMroducts.clear();
        fillData();
        //
        itemAdapter = new AdapterItem(getActivity(), mMroducts);
        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.listItem);
        lvMain.setAdapter(itemAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            idItem = bundle.getString("idItem");
            GetProductDetailsTask();
        }
        return  rootView;
    }
    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 5; i++) {
            mMroducts.add(new Product("Product " + i, i * 1000,
                    R.drawable.flatscreen));
        }
    }

    // получения информации о товаре
    void GetProductDetailsTask () {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("idItem", idItem));
        String paramString = URLEncodedUtils.format(params, "utf-8");

        String url = Сonstants.url_details_product+"/?"+paramString;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub

                JSONObject json = response;
                try {
                    int success = json.getInt(Сonstants.TAG_SUCCESS);

                    if (success == 1) {
                        // если получили информацию о товаре
                        productsArray = json.getJSONArray(Сonstants.TAG_PRODUCT);
                        // получим первый объект из массива JSON Array
                        JSONObject p = productsArray.getJSONObject(0);

                        name = p.getString(Сonstants.TAG_NAME);
                        price = p.getDouble(Сonstants.TAG_PRICE);
                        kodItem = p.getString(Сonstants.TAG_KOD);
                        description = p.getString(Сonstants.TAG_DISCRIPTION);
                        //Текущий товар
                        Product currentProduct = new Product(name, description, idItem, kodItem, price);
                        //заказ
                        Сonstants.currentOrder = new Order("ID", currentProduct, Сonstants.currentProfile, "date", price, 1, 1 * price, StatusOrder.NEW);

                    } else {
                        // не нашли товар по pid
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                TextView txtKod = (TextView) rootView.findViewById(R.id.item_text_kod);
                TextView txtName = (TextView) rootView.findViewById(R.id.item_text_name_product);
                TextView txtPrice = (TextView) rootView.findViewById(R.id.item_text_price);
                TextView txtDescription = (TextView) rootView.findViewById(R.id.item_text_full_discription);
                //
                txtKod.setText(kodItem);
                txtName.setText(name);
                txtPrice.setText(price+".0 грн.");
                txtDescription.setText(description);
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
