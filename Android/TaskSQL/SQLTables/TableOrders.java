package ua.com.it_st.deliveryman.SQLTables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gens on 27.04.2015.
 */
public class TableOrders {
    public static final String TABLE_NAME = "Orders";

    public static final String COLUMN_NAMBER = "number";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_VIEW_ID = "view_id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SUM = "sum";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_CONTRAGENT_ID = "counteragent_id";
    public static final String COLUMN_AMOUT= "amount";//количество
    public static final String COLUMN_MEASURING_ID = "measuring_id";//единица измерения
    public static final String COLUMN_TYPE_ID = "type_id";//тип заказа
    public static final String COLUMN_COMPANY_ID = "company_id";//компания

    private static final String TAG = TableOrders.class.getSimpleName();

    public static void createTable(final SQLiteDatabase db) {
        Log.i(TAG, "createTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_NAMBER + " text"
                + " ," + COLUMN_DATE + " text"
                + " ," + COLUMN_VIEW_ID + " integer"
                + " ," + COLUMN_PRICE + " real"
                + " ," + COLUMN_SUM + " real"
                + " ," + COLUMN_PRODUCT_ID + " integer"
                + " ," + COLUMN_CONTRAGENT_ID + " integer"
                + " ," + COLUMN_AMOUT + " real"
                + " ," + COLUMN_MEASURING_ID + " integer"
                + " ," + COLUMN_TYPE_ID + " integer"
                + " ," + COLUMN_COMPANY_ID + " integer"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        Log.i(TAG, "upgradeTable, old: " + oldVersion + ", new: " + newVersion);

    }


}
