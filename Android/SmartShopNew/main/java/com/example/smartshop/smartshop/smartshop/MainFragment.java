package ua.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;


import com.google.gson.Gson;
import com.nispok.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import ua.smartshop.Adapters.MainAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Models.Product;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 02.03.2015.
 */

public class MainFragment extends Fragment implements IWorkerCallback{

    private int mItemNumber = 1;
    private ArrayList<Product[]> mPoducts = new ArrayList<>();
    private MainAdapter mMainAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        mMainAdapter = new MainAdapter(getActivity(), mPoducts);
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
                    doSomethingAsyncOperaion(Product.getParamsUrlNumber(mItemNumber) , Сonstants.url_all_products,  TypeRequest.GET);

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
    public void onFailure(final Throwable t) {

    }

    @Override
    public void onEnd() {

    }
}
