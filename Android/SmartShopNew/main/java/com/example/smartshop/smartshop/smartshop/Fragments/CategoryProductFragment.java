package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.Adapters.CategoryAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.SerializedPhpParser;
import ua.smartshop.Utils.Сonstants;
import ua.smartshop.interfaces.AsyncWorkerInterface;
import ua.smartshop.Activity.MainActivity;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.R;

public class CategoryProductFragment extends Fragment implements AsyncWorkerInterface {

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
                    doSomethingAsyncOperaion( CategoryProduct.getParamsUrl(mItem_id), getString(R.string.url_get_category_products), TypeRequest.GET);
                }
            }
        }

        mCategoryAdapter = new CategoryAdapter(getActivity(), R.layout.main_list,  mPoducts);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mCategoryAdapter);

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
            CategoryProduct categoryProduct;
            JSONObject json = null;

            for (int i = 0; i < mPJSONArray.length(); i++) {
                categoryProduct =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), CategoryProduct.class);
                mPoducts.add(categoryProduct);
                try {
                    json =  mPJSONArray.getJSONObject(i).getJSONObject("mFiltr");
                    if (!( json == null) ){

                       // JsonParser parser = new JsonParser ();
                       // JsonElement element = parser.parse( json.toString());
                       // JsonArray arrays = element.getAsJsonArray();
                        byte d = 1;
                        for(int t = 0; i < json.length(); t++){

                            JSONObject jSONArray = json.getJSONObject(""+ d);
                            d++;

//                    artikli k = new artikli();
//                    k=gson.fromJson(e,artikli.class);

                            //System.out.println(k.toString());
                            Log.i("mJSONArray",""+ jSONArray.toString());
                        }

                    }

                } catch (Exception e){

               }

               if(!(categoryProduct.getFiltr().length() == 0)){
                   SerializedPhpParser serializedPhpParser = new SerializedPhpParser(
                           categoryProduct.getFiltr());
                   LinkedHashMap linkedHashMap = (LinkedHashMap)serializedPhpParser.parse();

                   //JSONObject readableJson = new JSONObject(jSONArray.toString());

                   Log.i("mJSONArray",""+ linkedHashMap.toString());

                   Iterator<Map.Entry<Integer, String>> itr1 = linkedHashMap.entrySet().iterator();
                   while (itr1.hasNext()) {
                       Map.Entry<Integer, String> entry = itr1.next();
                       //Log.i("mJSONArray",""+entry.getKey() + " = " + entry.getValue());
                   }
               }

            }
            mCategoryAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(final Throwable t) {

        if (mPoducts.size() == 0 & getView()!= null){
            ((TextView)  getView().findViewById(R.id.lvMain_text)).setText(getString(R.string.no_data));
        }
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onEnd() {

    }

}
