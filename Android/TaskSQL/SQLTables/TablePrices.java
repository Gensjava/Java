package ua.com.it_st.deliveryman.SQLTables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gens on 27.04.2015.
 */
public class TablePrices {
    public static final String TABLE_NAME = "Prices";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VIEW_ID = "view_id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_COMPANY_ID = "company_id";//компания

    private static final String TAG = TablePrices.class.getSimpleName();

    public static void createTable(final SQLiteDatabase db) {
        Log.i(TAG, "createTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_NAME + " text"
                + " ," + COLUMN_VIEW_ID + " integer"
                + " ," + COLUMN_PRICE + " real"
                + " ," + COLUMN_PRODUCT_ID + " integer"
                + " ," + COLUMN_COMPANY_ID + " integer"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        Log.i(TAG, "upgradeTable, old: " + oldVersion + ", new: " + newVersion);

    }


}
