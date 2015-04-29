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
import ua.smartshop.AsyncWorker;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.IWorkerCallback;
import ua.smartshop.MainActivity;
import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 03.03.2015.
 */
public class ProductDiscriptionFragment extends android.support.v4.app.Fragment implements IWorkerCallback {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.product_discription, container,
                false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String idItem = bundle.getString(MainActivity.KEY_ITEM);
            if(!(idItem == null)){
                doSomethingAsyncOperaion(Product.getParamsUrl(idItem) , Сonstants.url_product_description,  TypeRequest.GET);
            }
        }
        return  rootView;
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
            for (int i = 0; i < mPJSONArray.length(); i++) {
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
    }

    @Override
    public void onFailure(final Throwable t) {

    }

    @Override
    public void onEnd() {

    }
}
