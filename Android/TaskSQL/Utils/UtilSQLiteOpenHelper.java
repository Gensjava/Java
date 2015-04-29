package ua.com.it_st.deliveryman.Utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ua.com.it_st.deliveryman.SQLTables.TableCounteragents;
import ua.com.it_st.deliveryman.SQLTables.TableInformations;
import ua.com.it_st.deliveryman.SQLTables.TableOrders;
import ua.com.it_st.deliveryman.SQLTables.TablePrices;
import ua.com.it_st.deliveryman.SQLTables.TableProducts;
import ua.com.it_st.deliveryman.SQLTables.TableTasks;
import ua.com.it_st.deliveryman.SQLTables.TableTypeInformations;
import ua.com.it_st.deliveryman.SQLTables.TableTypeMeasuring;
import ua.com.it_st.deliveryman.SQLTables.TableTypeOrders;
import ua.com.it_st.deliveryman.SQLTables.TableTypePrioritiesTasks;
import ua.com.it_st.deliveryman.SQLTables.TableUsers;
import ua.com.it_st.deliveryman.SQLTables.TableViewPrices;
import ua.com.it_st.deliveryman.SQLTables.TableСhat;
import ua.com.it_st.deliveryman.SQLTables.TableСompanys;

/**
 * Created by Gens on 27.04.2015.
 */
public class UtilSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_courier.db";
    public static final int DATABASE_VERSION = 1;

    private static volatile UtilSQLiteOpenHelper sInstance = null;

    private SQLiteDatabase mDatabase;

    public UtilSQLiteOpenHelper(final Context context, final String name, final SQLiteDatabase.CursorFactory factory, final int version) {
        super(context, name, factory, version);
    }

    public UtilSQLiteOpenHelper(final Context context, final String name, final SQLiteDatabase.CursorFactory factory, final int version, final DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public UtilSQLiteOpenHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mDatabase = getWritableDatabase();
    }
    public static UtilSQLiteOpenHelper getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("init not called");
        }
        return sInstance;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        TableCounteragents.createTable(db);
        TableInformations.createTable(db);
        TableOrders.createTable(db);
        TablePrices.createTable(db);
        TableProducts.createTable(db);
        TableTasks.createTable(db);
        TableTypeInformations.createTable(db);
        TableTypeMeasuring.createTable(db);
        TableTypeOrders.createTable(db);
        TableTypePrioritiesTasks.createTable(db);
        TableUsers.createTable(db);
        TableViewPrices.createTable(db);
        TableСhat.createTable(db);
        TableСompanys.createTable(db);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

        TableUsers.upgradeTable(db, oldVersion, newVersion);

    }
    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    public static void init(final Context context) {

        if (sInstance == null) {
            synchronized (UtilSQLiteOpenHelper.class) {
                if (sInstance == null) {
                    sInstance = new UtilSQLiteOpenHelper(context);
                }
            }
        }
    }
}
