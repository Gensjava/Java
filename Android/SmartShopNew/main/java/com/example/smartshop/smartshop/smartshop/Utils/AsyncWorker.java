package ua.smartshop.Utils;

import android.content.Context;
import android.os.AsyncTask;

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
import ua.smartshop.Interface.IWorkerCallback;

/**
 * Created by Gens on 23.04.2015.
 */
public  class AsyncWorker<V> extends AsyncTask<Void, Void, JSONArray> {

    private IWorkerCallback<V> mCallback;
    private Throwable t;
    private HashMap<String, String> mParams;
    private TypeRequest mTypeRequest;
    private String mUrl;
    private JSONArray mJSONArray;    // массив товаров JSONArray
    private JSONParser mJParser = new JSONParser();   // Создаем объект JSON Parser
    private onSomeEventListener someEventListener ;
    private Context mContext;
    public static final String ERROR_JSON = "ERROR_JSON";

    //В конструктор передаём интерфейс
    protected AsyncWorker(IWorkerCallback<V> callback, final HashMap params, final String url, final TypeRequest typeRequest,Context context ) {
        mCallback = callback;
        mParams = params;
        mUrl = url;
        mTypeRequest = typeRequest;
        mContext = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mCallback != null) {
            mCallback.onBegin(); //Сообщаем через интерфейс о начале
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
           // Log.i("json999",""+json.toString());
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
        if (mCallback != null) {
            mCallback.onEnd(); //Сообщаем об окончании
        }
        generateCallback(v);
    }
    private void generateCallback(JSONArray data) { //Генерируем ответ
        if (mCallback == null) return;
        if (data != null) { //Есть данные - всё хорошо
            mCallback.onSuccess(data);
        } else if (t != null) {
            mCallback.onFailure(t); //Есть ошибка - вызываем onFailure
        } else { //А такая ситуация вообще не должна появляться)
            mCallback.onFailure(new NullPointerException("Result is empty but error empty too"));
        }
    }
    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }
}
