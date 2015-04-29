package ua.com.it_st.deliveryman.SQLTables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gens on 27.04.2015.
 */
public class TableInformations {
    public static final String TABLE_NAME = "Informations";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SORT_ID = "sort_id";
    public static final String COLUMN_TYPE_ID = "type_id";
    public static final String COLUMN_CONTRAGENT_ID = "counteragent_id";

    private static final String TAG = TableInformations.class.getSimpleName();

    public static void createTable(final SQLiteDatabase db) {
        Log.i(TAG, "createTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_NAME + " text"
                + " ," + COLUMN_SORT_ID + " integer"
                + " ," + COLUMN_TYPE_ID + " integer"
                + " ," + COLUMN_CONTRAGENT_ID + " integer"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        Log.i(TAG, "upgradeTable, old: " + oldVersion + ", new: " + newVersion);

    }


}
