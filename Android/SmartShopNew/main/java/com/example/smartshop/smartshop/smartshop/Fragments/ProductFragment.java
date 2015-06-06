package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;

import ua.smartshop.Activity.MainActivity;
import ua.smartshop.Adapters.ProductAdapter;
import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.interfaces.AsyncWorkerInterface;
import ua.smartshop.Models.Product;
import ua.smartshop.R;

public class ProductFragment extends android.support.v4.app.Fragment implements AsyncWorkerInterface {

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
                    //
                    doSomethingAsyncOperaion(Product.getParamsUrlNumberItem(mItemNumber, mItem_id), mUrl,  TypeRequest.GET);
                    mItemNumber  += 2;
                }
            }
        });
        return rootView;
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker(this, paramsUrl, url, typeRequest, getActivity()) {
        }.execute();
    }

    @Override
    public void onBegin() {

    MainActivity.ui_bar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            // проходим в цикле через все товары
            Product[] Products  = new Product[mPJSONArray.length()];
            for (byte i = 0; i < mPJSONArray.length(); i++) {
                Products[i] =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), Product.class);
            }
            mPoducts.add(Products);
            mMainAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(final Throwable t) {
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);

        if (mPoducts.size() == 0 & getView()!= null){
            ((TextView)  getView().findViewById(R.id.lvMain_text)).setText(getString(R.string.no_data));
        }
    }

    @Override
    public void onEnd() { MainActivity.ui_bar.setVisibility(View.INVISIBLE); }
}
