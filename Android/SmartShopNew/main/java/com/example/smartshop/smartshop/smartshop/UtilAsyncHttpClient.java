package ua.smartshop;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 10.04.2015.
 */
public class UtilAsyncHttpClient extends AsyncHttpClient {

    private JSONArray mJSONArray;
    private String mUrl;
    private String  mTag;
    private HashMap<String, String> mParams;
    private TypeRequest mTypeRequest;
    private onSomeEventListenerAsync onSomeEventListenerAsync ;
    private Activity mContext;

    public UtilAsyncHttpClient(final HashMap<String, String> params, final String url, final String tag, final TypeRequest typeRequest ,final Activity mainActivity) {

        mParams = params;
        mUrl = url;
        mTag = tag;
        mContext = mainActivity;
        mTypeRequest = typeRequest;
    }

    void  getAsyncArrayValues()  {

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Map.Entry entry : mParams.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }

        String paramString = URLEncodedUtils.format(params, "utf-8");

        String url = mUrl+"/?"+paramString;

        get( url ,
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(JSONObject json) {

                        mContext.setProgressBarIndeterminateVisibility(true);
                        int success = 0;
                        try {
                            success = json.getInt(Сonstants.TAG_SUCCESS);

                            if (success == 1) {     // товар найден

                                  mJSONArray = json.getJSONArray(Сonstants.TAG_PRODUCT);

                                onSomeEventListenerAsync = (onSomeEventListenerAsync) mContext;
                                onSomeEventListenerAsync.someEventAsync(mTag ,mJSONArray );

                            } else {
                                // не нашли товар по pid
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                        mContext.setProgressBarIndeterminateVisibility(false);

                        Toast.makeText(mContext, "Error: " + statusCode + " " +
                                throwable.getMessage(), Toast.LENGTH_LONG).show();

                        Log.e("omg android", statusCode + " " + throwable.getMessage());
                    }
                }
        );

    }

    public interface onSomeEventListenerAsync {
        public void someEventAsync(String tag, JSONArray mPJSONArray );
    }

    public JSONArray getJSONArray() {
        return mJSONArray;
    }

    public void setJSONArray(final JSONArray JSONArray) {
        mJSONArray = JSONArray;
    }
}
