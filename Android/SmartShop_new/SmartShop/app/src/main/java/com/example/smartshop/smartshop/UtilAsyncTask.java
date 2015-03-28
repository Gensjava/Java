package com.example.smartshop.smartshop;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Gens on 14.03.2015.
 */
public class UtilAsyncTask extends AsyncTask<String, String, String> {

    public  ArrayList<Object> mCategory = new ArrayList<Object>();

    // массив товаров JSONArray
    private JSONArray mProducts;
    // Создаем объект JSON Parser
    private JSONParser mJParser = new JSONParser();
    private String mUrl;
    private String [] mTags;
    private HashMap<String, String> mParams;
    private List<HashMap> mArrayValues = new ArrayList<HashMap>();

    Context mContext;
    onSomeEventListener someEventListener ;
    onSomeEventListenerUtilAsyncTask SomeEventListenerUtilAsyncTask;


    public UtilAsyncTask(HashMap params, String url , String tags[] ,Context context) {
        mParams = params;
        mUrl = url;
        mTags = tags;
        mContext = context;
    }

    @Override
    protected String doInBackground(String... args) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Map.Entry entry : mParams.entrySet()) {
             params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }

        someEventListener = (onSomeEventListener) mContext;
        SomeEventListenerUtilAsyncTask = (onSomeEventListenerUtilAsyncTask) mContext;

        try {
            // получим строку JSON из URL
           
            JSONObject json = mJParser.makeHttpRequest( mUrl, "GET",
                    params);
            //Log.i("11111111111","json = 1111   " +json);
            if (json == null){
                Log.i("json","json = null");
                return null;
            }
            //Log.i("22222222222222","json = 222222222222222222222222");
            int success = json.getInt(Сonstants.TAG_SUCCESS);

            if (success == 1) {     // товар найден
                // получаем массив товаров
                mProducts = json.getJSONArray(Сonstants.TAG_PRODUCT);
               // Log.i("33333333","json = 33333333  "+mProducts.length());

                // проходим в цикле через все товары
                for (int i = 0; i < mProducts.length(); i++) {
                    JSONObject c = mProducts.getJSONObject(i);
                     HashMap<String, String> mValues = new HashMap<String, String>();

                   // Log.i("4444444","json = 444444  "+mProducts.length());
                     for (int ig = 0; ig < mTags.length; ig++) {
                         
                         String mTag = mTags[ig];
                         String mTagValue = c.getString(mTag);

                         mValues.put(mTag, mTagValue);
                     }
                    //Добавляем в список позиции
                    mArrayValues.add(mValues);
                   // Log.i("5555555","json = 5555555555 "+mValues);

                }

                //SomeEventListenerUtilAsyncTask.someEvent(mCategory);
            } else {
                // не нашли товар по pid
               //someEventListener.someEvent("FragmentCategoryProduct",mIdItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(String file_url) {

        //mainAdapter.notifyDataSetChanged();
    }
    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }
    public interface onSomeEventListenerUtilAsyncTask {
        public void someEvent(ArrayList<Object> mCategory);
    }

    public ArrayList<Object> getCategory() {
        return mCategory;
    }

    public void setCategory(ArrayList<Object> category) {
        mCategory = category;
    }

    public List<HashMap> getArrayValues() {
        return mArrayValues;
    }

    public void setArrayValues(final List<HashMap> arrayValues) {
        mArrayValues = arrayValues;
    }
}
