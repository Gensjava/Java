package ua.smartshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.Adapters.ProductItemAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Interface.IWorkerCallback;
import ua.smartshop.Activitys.MainActivity;
import ua.smartshop.Models.Product;
import ua.smartshop.R;

/**
 * Created by Gens on 03.03.2015.
 */
public class ProducttItemFragment extends android.support.v4.app.Fragment implements IWorkerCallback  {

    private ArrayList<Product> mProducts = new ArrayList<Product>();
    public static Product mProduct;
    private ProductItemAdapter mItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.product_list, container,
                false);
        //
        mItemAdapter = new ProductItemAdapter(getActivity(), mProducts);
        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.listItem);
        lvMain.setAdapter(mItemAdapter);

        if(!(ProducttItemRootFragment.mItem == null)){
            doSomethingAsyncOperaion(Product.getParamsUrl(ProducttItemRootFragment.mItem), getString(R.string.url_details_product),  TypeRequest.GET);
        }

        return  rootView;
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest, getActivity()) {
        }.execute();
    }

    @Override
    public void onBegin()
    {
        MainActivity.ui_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            for (byte i = 0; i < mPJSONArray.length(); i++) {
                mProduct =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), Product.class);
            }
            mProducts.add(mProduct);
            mItemAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(final Throwable t) {
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onEnd() {

    }

}
