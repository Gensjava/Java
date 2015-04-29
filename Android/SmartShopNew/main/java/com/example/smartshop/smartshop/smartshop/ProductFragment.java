package ua.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import ua.smartshop.Adapters.ProductAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Models.Product;

/**
 * Created by Gens on 07.03.2015.
 */
public class ProductFragment extends android.support.v4.app.Fragment implements IWorkerCallback {

    private int mItemNumber = 1;
    private ArrayList<Product[]> mPoducts = new ArrayList<>();
    private ProductAdapter mMainAdapter;
    private String mItem_id;
    private String mUrl;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        if (mPoducts.size() == 0){
            Bundle bundle = getArguments();
            if (bundle != null) {
                mItem_id = bundle.getString(MainActivity.KEY_ITEM);
                mUrl = bundle.getString(MainActivity.URL_KEY);
            }
        }
        mMainAdapter = new ProductAdapter(getActivity(), mPoducts);
        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mMainAdapter);

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    mItemNumber++;
                    //
                    doSomethingAsyncOperaion(Product.getParamsUrlNumberItem(mItemNumber, mItem_id), mUrl,  TypeRequest.GET);
                }
            }
        });
        return rootView;
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest) {
        }.execute();
    }

    @Override
    public void onBegin() { }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            // проходим в цикле через все товары
            Product[] Products  = new Product[2];
            for (int i = 0; i < mPJSONArray.length(); i++) {
                Products[i] =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), Product.class);
            }
            mPoducts.add(Products);
            mMainAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(final Throwable t) {}

    @Override
    public void onEnd() { }
}
