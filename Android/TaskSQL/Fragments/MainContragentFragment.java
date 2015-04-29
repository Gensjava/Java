package ua.com.it_st.deliveryman.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.concurrent.TimeUnit;

import ua.com.it_st.deliveryman.Adapters.ItemListBaseAdapter;
import ua.com.it_st.deliveryman.R;
import ua.com.it_st.deliveryman.SQLTables.TableCounteragents;
import ua.com.it_st.deliveryman.Utils.UtilSQLiteOpenHelper;

/**
 * Created by Gens on 21.04.2015.
 */
public class MainContragentFragment extends  android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView lvData;
    private static SQLiteDatabase DB;
    private SimpleCursorAdapter scAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container,
                false);

        // открываем подключение к БД
        DB = UtilSQLiteOpenHelper.getInstance().getDatabase();

        // формируем столбцы сопоставления
        String[] from = new String[] { TableCounteragents.COLUMN_NAME };
        int[] to = new int[] { R.id.main_item_title };

        // создааем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(getActivity(), R.layout.main_list_item, null, from, to, 0);
        lvData = (ListView) rootView.findViewById(R.id.main_list);
        lvData.setAdapter(scAdapter);

        // добавляем контекстное меню к списку
        registerForContextMenu(lvData);

        // создаем лоадер для чтения данных
        getActivity().getSupportLoaderManager().initLoader(0, null, this);

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        return  new MyCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // закрываем подключение при выходе
        DB.close();
    }

    private static class MyCursorLoader extends CursorLoader {

        public MyCursorLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return DB
                    .query(TableCounteragents.TABLE_NAME, // table name
                            null, // columns
                            null, // selection
                            null, // selectionArgs
                            null, // groupBy
                            null, // having
                            null);// orderBy
        }
    }
}
