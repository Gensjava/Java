package com.example.smartshop.smartshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.concurrent.ExecutionException;


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
    private TypeRequest mTypeRequest;
    // Progress Dialog
    private ProgressDialog pDialog;

    Context mContext;

    public UtilAsyncTask(HashMap params, String url , String tags[] ,Context context, TypeRequest TypeRequest) {
        mParams = params;
        mUrl = url;
        mTags = tags;
        mContext = context;
        mTypeRequest = TypeRequest;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
       // pDialog = new ProgressDialog(mContext);
        //pDialog.setMessage("Загружаем список. Подождите...");
        //pDialog.setIndeterminate(false);
        //pDialog.setCancelable(false);
        //pDialog.show();
    }
    @Override
    protected String doInBackground(String... args) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Map.Entry entry : mParams.entrySet()) {
             params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }

        try {
            // получим строку JSON из URL
           
            JSONObject json = mJParser.makeHttpRequest( mUrl, mTypeRequest.toString(),
                    params);

            if (json == null){
                return null;
            }
            Log.i("mJParser",""+json.toString());
            int success = json.getInt(Сonstants.TAG_SUCCESS);
           /// String cod = json.getString("dd");

            //Log.i("mJParser",""+cod.toString());

            if (success == 1) {     // товар найден

                // получаем массив товаров
                mProducts = json.getJSONArray(Сonstants.TAG_PRODUCT);

                // проходим в цикле через все товары
                for (int i = 0; i < mProducts.length(); i++) {

                    JSONObject c = mProducts.getJSONObject(i);

                     HashMap<String, String> mValues = new HashMap<String, String>();

                     for (int ig = 0; ig < mTags.length; ig++) {
                         
                         String mTag = mTags[ig];
                         String mTagValue = c.getString(mTag);

                         mValues.put(mTag, mTagValue);
                     }
                    //Добавляем в список позиции
                    mArrayValues.add(mValues);
                }

            } else {
                // не нашли товар по pid
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(String file_url) {

// закрываем диалоговое окно с индикатором прогресса
       // pDialog.dismiss();
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
