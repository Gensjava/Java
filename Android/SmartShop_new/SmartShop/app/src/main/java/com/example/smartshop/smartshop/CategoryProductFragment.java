package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
 * Created by Gens on 07.03.2015.
 */
public class CategoryProductFragment extends android.support.v4.app.Fragment {

    ArrayList<CategoryProduct> mCategory = new ArrayList<CategoryProduct>();
    ArrayList<CategoryProduct> mCategory1 = new ArrayList<CategoryProduct>();

    CategoryAdapter mCategoryAdapter;
    private JSONArray productsArray;
    String id ;
    String name;
    onSomeEventListener someEventListener ;
    String idItem;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategory = (ArrayList<CategoryProduct>)  bundle.getSerializable("mCategory");
        }

        if (savedInstanceState != null && savedInstanceState.containsKey("mCategory")){
            mCategory = (ArrayList<CategoryProduct>) savedInstanceState.getSerializable("mCategory");
        }

        mCategoryAdapter = new CategoryAdapter(getActivity(), R.layout.main_list,  mCategory);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mCategoryAdapter);
        
        mCategoryAdapter.notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putSerializable("mCategory",  mCategory);
        super.onSaveInstanceState(outState);
    }

    public void getCategory(final String idItem){

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("idItem", idItem));

        String paramString = URLEncodedUtils.format(params, "utf-8");

        String url = Сonstants.url_get_category_products+"/?"+paramString;
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
                        productsArray = json.getJSONArray(Сonstants.TAG_PRODUCTS);

                        // проходим в цикле через все товары
                        for (int i = 0; i < productsArray.length(); i++) {
                            JSONObject c = productsArray.getJSONObject(i);
                            id = c.getString(Сonstants.TAG_PID);
                            name = c.getString(Сonstants.TAG_NAME);
                            mCategory.add(new CategoryProduct(id,name));

                        }
                        mCategoryAdapter.notifyDataSetChanged();
                    } else {

                        // не нашли товар по pid
                        someEventListener = (onSomeEventListener) getActivity();
                        someEventListener.someEvent("FragmentCategoryProduct", idItem);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        });
        queue.add(jsObjRequest);
    }

    public interface onSomeEventListener {

        public void someEvent(String id,String idItem );
    }

}
