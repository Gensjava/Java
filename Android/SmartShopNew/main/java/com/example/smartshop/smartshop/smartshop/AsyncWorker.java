package ua.smartshop;

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

import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.JSONParser;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 23.04.2015.
 */
public  class AsyncWorker<V> extends AsyncTask<Void, Void, JSONArray> {

    private IWorkerCallback<V> callback;
    private Throwable t;
    private HashMap<String, String> mParams;
    private TypeRequest mTypeRequest;
    private String mUrl;
    private JSONArray mJSONArray;    // массив товаров JSONArray
    private JSONParser mJParser = new JSONParser();   // Создаем объект JSON Parser

    //В конструктор передаём интерфейс
    protected AsyncWorker(IWorkerCallback<V> callback, final HashMap params, final String url, final TypeRequest typeRequest) {
        this.callback = callback;
        mParams = params;
        mUrl = url;
        mTypeRequest = typeRequest;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onBegin(); //Сообщаем через интерфейс о начале
        }
    }
    protected  JSONArray doAction() {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry entry : mParams.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }
        try {
            // получим строку JSON из URL
            JSONObject json = mJParser.makeHttpRequest( mUrl, mTypeRequest.toString(),
                    params);
            //Log.i("json",""+json.toString());
            if (json == null){
                return null;
            }
            int success = json.getInt(Сonstants.TAG_SUCCESS);
            if (success == 1) {     // товар найден
                // получаем массив товаров
                mJSONArray = json.getJSONArray(Сonstants.TAG_PRODUCT);
            } else {
                // не нашли товар
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJSONArray;
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        try {
            return doAction(); //В параллельном потоке вызываем метод.
        } catch (Exception e) {
            t = e;
            return null;
        }
    }
    @Override
    protected void onPostExecute(JSONArray v) {
        super.onPostExecute(v);
        if (callback != null) {
            callback.onEnd(); //Сообщаем об окончании
        }
        generateCallback(v);
    }
    private void generateCallback(JSONArray data) { //Генерируем ответ
        if (callback == null) return;
        if (data != null) { //Есть данные - всё хорошо
            callback.onSuccess(data);
        } else if (t != null) {
            callback.onFailure(t); //Есть ошибка - вызываем onFailure
        } else { //А такая ситуация вообще не должна появляться)
            callback.onFailure(new NullPointerException("Result is empty but error empty too"));
        }
    }
}
