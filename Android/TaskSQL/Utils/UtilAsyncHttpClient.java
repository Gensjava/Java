package ua.com.it_st.deliveryman.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ua.com.it_st.deliveryman.SQLTables.TableCounteragents;

public class UtilAsyncHttpClient extends AsyncHttpClient {

    public UtilAsyncHttpClient() {

    }

    private void addRecord(String description) {

        final ContentValues data = new ContentValues();
        data.put(TableCounteragents.COLUMN_NAME, description);

        SQLiteDatabase db =  UtilSQLiteOpenHelper.getInstance().getDatabase();

        db.beginTransaction();
        try {
            db.insert(TableCounteragents.TABLE_NAME, null, data);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        Cursor cursor = db
                .query(TableCounteragents.TABLE_NAME, // table name
                        null, // columns
                        null, // selection
                        null, // selectionArgs
                        null, // groupBy
                        null, // having
                        null);// orderBy

        while (cursor.moveToFirst()){

        }

        db.close();
    }

    public void getPublicTimeline() throws JSONException {

        get("http://10.0.3.2/Pekin/odata/standard.odata/Catalog_Контрагенты?$format=json",  new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

                try {
                    JSONArray jsonArray = response.getJSONArray("value");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject mPJSONArray =  jsonArray.getJSONObject(i);

                        Log.i("AsyncHttpClient1", jsonArray.toString());

                        String description = mPJSONArray.getString("Description");

                        addRecord( description);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
            }

            @Override
            public void onFailure(final int statusCode, final Header[] headers, final String responseString, final Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    @Override
    public void setBasicAuth(final String username, final String password) {
        super.setBasicAuth(username, password);
    }

}
