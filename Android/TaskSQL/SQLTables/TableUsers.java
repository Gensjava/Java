package ua.com.it_st.deliveryman.SQLTables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gens on 27.04.2015.
 */
public class TableUsers {
    public static final String TABLE_NAME = "Users";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_KOD = "kod";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASSWORD = "password";

    private static final String TAG = TableUsers.class.getSimpleName();

    public static void createTable(final SQLiteDatabase db) {
        Log.i(TAG, "createTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_NAME + " text"
                + " ," + COLUMN_KOD + " text"
                + " ," + COLUMN_EMAIL + " text"
                + " ," + COLUMN_PHONE + " text"
                + " ," + COLUMN_PASSWORD + " text"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        Log.i(TAG, "upgradeTable, old: " + oldVersion + ", new: " + newVersion);

    }


}
