package ua.com.it_st.deliveryman.SQLTables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gens on 27.04.2015.
 */
public class TableTasks {
    public static final String TABLE_NAME = "Tasks";

    public static final String COLUMN_NAMBER = "number";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SUM = "sum";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_CONTRAGENT_ID = "counteragent_id";
    public static final String COLUMN_AMOUT= "amount";//количество
    public static final String COLUMN_PRIORITET_ID = "priorities_id";//приоритет задач
    public static final String COLUMN_COMPANY_ID = "company_id";//компания

    private static final String TAG = TableTasks.class.getSimpleName();

    public static void createTable(final SQLiteDatabase db) {
        Log.i(TAG, "createTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_NAMBER + " text"
                + " ," + COLUMN_DATE + " text"
                + " ," + COLUMN_PRICE + " real"
                + " ," + COLUMN_SUM + " real"
                + " ," + COLUMN_PRODUCT_ID + " integer"
                + " ," + COLUMN_CONTRAGENT_ID + " integer"
                + " ," + COLUMN_AMOUT + " real"
                + " ," + COLUMN_PRIORITET_ID + " integer"
                + " ," + COLUMN_COMPANY_ID + " integer"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        Log.i(TAG, "upgradeTable, old: " + oldVersion + ", new: " + newVersion);

    }


}
