package ua.smartshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.interfaces.AsyncWorkerInterface;
import ua.smartshop.Activity.MainActivity;
import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Utils.Сonstants;


public class ProductDiscriptionFragment extends android.support.v4.app.Fragment implements AsyncWorkerInterface {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.product_discription, container,
                false);

            if(!(ProducttItemRootFragment.mItem == null)){
                doSomethingAsyncOperaion(Product.getParamsUrl(ProducttItemRootFragment.mItem) , getString(R.string.url_product_description),  TypeRequest.GET);
            }
        return  rootView;
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
            for (byte i = 0; i < mPJSONArray.length(); i++) {
                JSONObject p = mPJSONArray.getJSONObject(i);
                String description = p.getString(Сonstants.TAG_DISCRIPTION);
                //
                WebView webDescription = (WebView) rootView.findViewById(R.id.textView_full_discription);
                WebSettings settings = webDescription.getSettings();
                // включаем поддержку JavaScript
                settings.setJavaScriptEnabled (true);
                settings.setDefaultTextEncodingName("UTF-8");
                webDescription.loadDataWithBaseURL(null, description, "text/html", "en_US", null);
            }

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
