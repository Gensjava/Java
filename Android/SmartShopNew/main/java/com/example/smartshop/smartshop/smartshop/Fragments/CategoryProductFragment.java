package ua.smartshop.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import ua.smartshop.AsyncWorker;
import ua.smartshop.Adapters.CategoryAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.IWorkerCallback;
import ua.smartshop.MainActivity;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.R;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 07.03.2015.
 */
public class CategoryProductFragment extends Fragment implements IWorkerCallback {

    private ArrayList<CategoryProduct> mPoducts = new ArrayList<CategoryProduct>();
    private CategoryAdapter mCategoryAdapter;
    private String mItem_id;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        if (mPoducts.size() == 0 ){
            Bundle bundle = getArguments();
            if (bundle != null) {
                 mItem_id = bundle.getString(MainActivity.KEY_ITEM);
                if(!(mItem_id == null)){
                    doSomethingAsyncOperaion( CategoryProduct.getParamsUrl(mItem_id),Сonstants.url_get_category_products, TypeRequest.GET);
                }
            }
        }

        mCategoryAdapter = new CategoryAdapter(getActivity(), R.layout.main_list,  mPoducts);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mCategoryAdapter);

        return rootView;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onDetach();
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest) {
        }.execute();
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            // проходим в цикле через все товары
            CategoryProduct categoryProduct;
            for (int i = 0; i < mPJSONArray.length(); i++) {
                categoryProduct =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), CategoryProduct.class);
                mPoducts.add(categoryProduct);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(final Throwable t) {

    }

    @Override
    public void onEnd() {
        mCategoryAdapter.notifyDataSetChanged();
    }

}
