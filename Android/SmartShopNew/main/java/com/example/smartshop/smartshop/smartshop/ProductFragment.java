package ua.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ua.smartshop.Adapters.ProductAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Models.Product;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 07.03.2015.
 */
public class ProductFragment extends android.support.v4.app.Fragment {

    private ArrayList<Product[]> mPoducts = new ArrayList<Product[]>();
    private ProductAdapter adapterPagerShare;
    private int itemNumber = 1;
    private String mItem_id;
    private String mUrl;

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
                    UtilAsyncHttpClient utilAsyncTask = new UtilAsyncHttpClient(Product.getParamsUrlNumberItem(itemNumber, mItem_id), mUrl, getTag(), TypeRequest.GET ,getActivity()) ;
                    utilAsyncTask.getAsyncArrayValues();
                }
            }
        });

        return rootView;
    }
    void executeArrayValues(final JSONArray mPJSONArray)  {

        try {
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
            adapterPagerShare.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
