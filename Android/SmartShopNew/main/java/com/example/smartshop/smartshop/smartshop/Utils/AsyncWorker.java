package ua.smartshop.Utils;

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
import ua.smartshop.interfaces.AsyncWorkerInterface;

/**
 * Created by Gens on 23.04.2015.
 */
public  class AsyncWorker extends AsyncTask <Void, Void, JSONArray> {

    private AsyncWorkerInterface mAsyncWorkerInterface;
    private Throwable t;
    private HashMap<String, String> mParams;
    private TypeRequest mTypeRequest;
    private String mUrl;
    private JSONArray mJSONArray;    // массив товаров JSONArray
    private JSONParser mJParser = new JSONParser();   // Создаем объект JSON Parser
    private onSomeEventListener someEventListener ;
    private Context mContext;
    public static final String ERROR_JSON = "ERROR_JSON";

    protected AsyncWorker(AsyncWorkerInterface asyncWorkerInterface, final HashMap params, final String url, final TypeRequest typeRequest,Context context ) {
        mAsyncWorkerInterface = asyncWorkerInterface;
        mParams = params;
        mUrl = url;
        mTypeRequest = typeRequest;
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mAsyncWorkerInterface != null) {
            mAsyncWorkerInterface.onBegin(); //Сообщаем через интерфейс о начале
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
            someEventListener = (onSomeEventListener) mContext;
            someEventListener.someEvent(ERROR_JSON, null);

            return null;
        }
    }
    @Override
    protected void onPostExecute(JSONArray v) {
        super.onPostExecute(v);
        if (mAsyncWorkerInterface != null) {
            mAsyncWorkerInterface.onEnd(); //Сообщаем об окончании
        }
        generateCallback(v);
    }
    private void generateCallback(JSONArray data) {
        if (mAsyncWorkerInterface == null) return;
        if (data != null) { //Есть данные
            mAsyncWorkerInterface.onSuccess(data);
        } else if (t != null) {
            mAsyncWorkerInterface.onFailure(t); //Есть ошибка
        } else {
            mAsyncWorkerInterface.onFailure(new NullPointerException("Result is empty but error empty too"));
        }
    }
    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }
}
