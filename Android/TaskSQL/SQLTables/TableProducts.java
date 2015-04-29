package ua.com.it_st.deliveryman.SQLTables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gens on 27.04.2015.
 */
public class TableProducts {
    public static final String TABLE_NAME = "Products";

    public static final String COLUMN_ID_CATEGORY = "id_category";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NAME_FULL = "name_full";
    public static final String COLUMN_KOD = "kod";
    public static final String COLUMN_MEASURING_ID = "measuring_id";//единица измерения

    private static final String TAG = TableProducts.class.getSimpleName();

    public static void createTable(final SQLiteDatabase db) {
        Log.i(TAG, "createTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_ID_CATEGORY + " integer"
                + " ," + COLUMN_NAME + " text"
                + " ," + COLUMN_NAME_FULL + " text"
                + " ," + COLUMN_KOD + " text"
                + " ," + COLUMN_MEASURING_ID + " integer"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        Log.i(TAG, "upgradeTable, old: " + oldVersion + ", new: " + newVersion);

    }


}
