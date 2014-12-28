package com.example.gens.myapplication_sms;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private List<Note> items;
    private NoteAdapter adapter;
    private int listPosition;
    private Note item;
    private BroadcastReceiver broadcastReceiver;

    public static final String EXTRA_NOTE_KEY = "EXTRA_NOTE_KEY";
    public static final int EDIT_ACTIVITY_KEY = 101;
    public final static String BROADCAST_ACTION = "BROADCAST_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //создаем и регистррирем BroadcastReceiver с фильтром
        ActivBroadcastReceiver();
        //иницилизируем listView
        initializlListView ();
        //инициализируем ArrayList
        items = new ArrayList<Note>();
        //создаем свой адаптер
        adapter = new NoteAdapter(this, R.layout.list_item_item, items);
        //добавляем adapter
        listView.setAdapter(adapter);
    }
    private void ActivBroadcastReceiver(){

        // создаем BroadcastReceiver
        broadcastReceiver = new BroadcastReceiver() {
            // действия при получении сообщений
            public void onReceive(Context context, Intent intent) {

                final Bundle extras = intent.getExtras();
                if (extras != null) {
                    if (extras.containsKey(BROADCAST_ACTION)) {
                        final Note note = (Note) extras.get(BROADCAST_ACTION);
                        if(note != null){
                            items.add(note);
                            //обновляем список
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        };
        // создаем фильтр для BroadcastReceiver
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(broadcastReceiver, intFilt);
    }
    //иницилизируем listView + обработчик нажатия на listView
    private void initializlListView() {
        listView = (ListView) findViewById(R.id.listView);
        //Клик на листе
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listPosition = position;
                item = items.get(position);
                //открывем диалог выбор для выбора действий
                showAlertDialogEditDelete();
            }
        });
    }
    //Открываем активити для редактирования новой записи
    private void onNoteAddEditActivity(int key){

        Intent intent = new Intent(this, ViewingMessagesActivity.class);
        //если редактировать
        if(key == EDIT_ACTIVITY_KEY ){
            intent.putExtra(EXTRA_NOTE_KEY, item);
        }
        startActivityForResult(intent, key);
    }
    //Создаем и открываем диалог (редактируем или удаляем запись)
    private void  showAlertDialogEditDelete() {

        //создаем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //открываем диалог
        builder = new AlertDialog.Builder(this);
        builder.setTitle(item.getName());
        builder.setIcon(R.drawable.ic_launcher);
        builder .setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        //открываем активити для редактирвания
                        onNoteAddEditActivity(EDIT_ACTIVITY_KEY);
                    }
                })
                .setNeutralButton("Отмена",null
                )
                .setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                //Удаляем из ArrayList
                                items.remove(listPosition);
                                //обновляем список
                                adapter.notifyDataSetChanged();
                            }
                        });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_simple) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // дерегистрируем (выключаем) BroadcastReceiver
        unregisterReceiver(broadcastReceiver);
    }
}
