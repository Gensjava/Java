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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gens on 07.03.2015.
 */
public class ShareFragment extends android.support.v4.app.Fragment {

    private ArrayList<ProductDual> mMroducts = new ArrayList<ProductDual>();
    private List<HashMap> mArrayValues;
    private ProductAdapter adapterPagerShare;
    private int itemNumber = 1;
    private String mItem_id;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        if (mMroducts.size() == 0){
            Bundle bundle = getArguments();
            if (bundle != null) {
                mItem_id = bundle.getString("imageView_mValues");
            }
        }
        adapterPagerShare = new ProductAdapter(getActivity(), mMroducts);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(adapterPagerShare);

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {

                    itemNumber++;
                    onAsyncTask(itemNumber, mItem_id);
                    GetProductDetailsTask();
                    adapterPagerShare.notifyDataSetChanged();
                }
            }
        });

        return rootView;
    }
    // получения информации о товаре
    void GetProductDetailsTask () {

        HashMap<String, String> mValues;
        for (int i = 0; i < mArrayValues.size(); i++) {

            mValues = mArrayValues.get(i);
            Product ProductOne = null;
            Product ProductTwo = null;

            switch (i) {
                case 0:
                    ProductOne = new Product(
                            Сonstants.url_main_way_image + mValues.get("wayimage") ,
                            mValues.get("pid") ,
                            Double.parseDouble(mValues.get("price")) );
                    break;
                case 1:
                    ProductTwo = new Product(
                            Сonstants.url_main_way_image + mValues.get("wayimage") ,
                            mValues.get("pid") ,
                            Double.parseDouble(mValues.get("price")) );
                    break;
                default:
                    break;
            }
            mMroducts.add(new ProductDual(ProductOne, ProductTwo));

        }

    }

    private void onAsyncTask(int itemnumber, String item_id ){

        String tags[] = new String[4];
        tags[0] = Сonstants.TAG_PID;
        tags[1] = Сonstants.TAG_NAME;
        tags[2] = Сonstants.TAG_PRICE;
        tags[3] = Сonstants.TAG_WAY_IMAGE;
        //
        HashMap<String, String> params= new HashMap<String, String>();
        params.put("idItem", item_id);
        params.put("itemnumber",String.valueOf(itemnumber));

        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_get_slider_main_page_category, tags, getActivity());
        utilAsyncTask.execute();
        try {
            utilAsyncTask.get();

            mArrayValues  = utilAsyncTask.getArrayValues();

            if(!(mArrayValues.size() == 0)){

            } else {
                //Открываем фрагмент с ошибкой
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
