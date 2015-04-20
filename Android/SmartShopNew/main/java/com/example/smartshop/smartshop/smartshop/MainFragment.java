package ua.smartshop;

import android.os.Bundle;
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

import ua.smartshop.Adapters.MainAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Models.Product;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 02.03.2015.
 */

public class MainFragment extends android.support.v4.app.Fragment {

    private int itemNumber = 1;
    private ArrayList<Product[]> mPoducts = new ArrayList<>();
    private MainAdapter mMainAdapter;
    private ListView lvMain;
    public static String GET_TEG_MAIN = "class ua.smartshop.MainFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        mMainAdapter = new  MainAdapter(getActivity(), mPoducts);
        // настраиваем список
        lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mMainAdapter);

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount ) {
                    itemNumber  ++;
                    //
                    String mUrl = Сonstants.url_all_products;
                    HashMap<String, String> mParamsUrl = Product.getParamsUrlNumber(itemNumber);
                    //
                    UtilAsyncHttpClient utilAsyncHttpClient= new UtilAsyncHttpClient( mParamsUrl, mUrl, GET_TEG_MAIN, TypeRequest.GET ,getActivity()) ;
                    utilAsyncHttpClient.getAsyncArrayValues();
                }
            }
        });
        return rootView;
    }

    void executeArrayValues(final JSONArray mPJSONArray)  {

        try {
            // проходим в цикле через все товары
            Product[] Products  = new Product[2];

            for (int i = 0; i < mPJSONArray.length(); i++) {
                JSONObject p = mPJSONArray.getJSONObject(i);

                String id = p.getString(Сonstants.TAG_PID);
                String wayImage = p.getString(Сonstants.TAG_WAY_IMAGE);
                String fullImage = Сonstants.url_main_way_image + wayImage;

                double price = Double.parseDouble(p.getString(Сonstants.TAG_PRICE));
                Products[i] = new Product(price, id, fullImage);
            }
            mPoducts.add(Products);
            mMainAdapter.notifyDataSetChanged();
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
}
