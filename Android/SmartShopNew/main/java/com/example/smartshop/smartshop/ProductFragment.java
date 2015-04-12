package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gens on 07.03.2015.
 */
public class ProductFragment extends android.support.v4.app.Fragment {

    private ArrayList<ProductDual> mPoducts = new ArrayList<ProductDual>();
    private List<HashMap> mArrayValues;
    private ProductAdapter adapterPagerShare;
    private int itemNumber = 1;
    private String mItem_id;
    private String mUrl;
    private Product ProductOne;
    private Product ProductTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        if (mPoducts.size() == 0){
            Bundle bundle = getArguments();
            if (bundle != null) {
                mItem_id = bundle.getString(Сonstants.VALUE_KEY_ITEM_ID);
                mUrl = bundle.getString(MainActivity.URL_KEY);
            }
        }
        adapterPagerShare = new ProductAdapter(getActivity(), mPoducts);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(adapterPagerShare);

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {

                    itemNumber  ++;
                    //
                    HashMap<String, String> params= new HashMap<String, String>();
                    params.put(Сonstants.VALUE_KEY_ITEM_ID, mItem_id);
                    params.put(Сonstants.VALUE_KEY_ITEM_NUMBER, String.valueOf(itemNumber));
                    //
                    UtilAsyncHttpClient utilAsyncTask = new UtilAsyncHttpClient( params, mUrl, getTag(), TypeRequest.GET ,getActivity()) ;
                    utilAsyncTask.getAsyncArrayValues();
                }
            }
        });

        return rootView;
    }
    void executeArrayValues(final JSONArray mPJSONArray)  {

        try {
            // проходим в цикле через все товары
            for (int i = 0; i < mPJSONArray.length(); i++) {
                JSONObject c = mPJSONArray.getJSONObject(i);

                String id = c.getString(Сonstants.TAG_PID);
                String wayImage = c.getString(Сonstants.TAG_WAY_IMAGE);
                String fullImage = Сonstants.url_main_way_image + wayImage;

                double price = 0.00;

                price = Double.parseDouble(c.getString(Сonstants.TAG_PRICE));
                switch (i) {
                    case 0:
                        ProductOne = new Product(price, R.drawable.flatscreen, id,fullImage);
                        break;
                    case 1:
                        ProductTwo = new Product(price, R.drawable.flatscreen, id, fullImage);
                        break;
                    default:
                        break;
                }
            }
            mPoducts.add(new ProductDual(ProductOne, ProductTwo));
            adapterPagerShare.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
