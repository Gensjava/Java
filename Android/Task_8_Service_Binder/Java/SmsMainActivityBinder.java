package com.example.gens.myapplication_sms_binder_2;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SmsMainActivityBinder extends ActionBarActivity  {

    private ServiceConnection sConnection;
    private Intent intent;

    public static final String EXTRA_NOTE_KEY = "EXTRA_NOTE_KEY";
    public static final int EDIT_ACTIVITY_KEY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //Создаем Intent для связи с сервисом
        intent = new Intent(this, SmsService.class);
        //получаем SmsServiceBinder
        final SmsService myService  = ServiceConnected();
        //инициализируем ArrayList
        final List<Note> itemsSms = new ArrayList<Note>();
        //создаем свой адаптер
        final NoteAdapter adapter = new NoteAdapter(this, R.layout.list_item_item, itemsSms);
        //иницилизируем listView
        ListView listView = (ListView) findViewById(R.id.listView);
        //добавляем adapter
        listView.setAdapter(adapter);
        //клик
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //открывем диалог выбор для выбора действий
                showAlertDialogEditDelete(itemsSms,adapter,position,itemsSms.get(position));
            }
        });
        //Вкл.таймер
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //делаем поиск события
                updateListSms(false, itemsSms, adapter, myService);
            }
        }, 10000, 10000); //
    }
    //связь активити и сервисом
    public SmsService  ServiceConnected(){
        SmsService myService = null;

        sConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                SmsService myService = ((SmsService.MyBinder) binder).getService();
            }
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        return  myService;
    }
    //Открываем активити для редактирования новой записи
    private void onNoteAddEditActivity(int key, Note itemSms){

        Intent intent = new Intent(this, SmsViewingMessagesActivity.class);
        //если редактировать
        if(key == EDIT_ACTIVITY_KEY ){
            intent.putExtra(EXTRA_NOTE_KEY, itemSms);
        }
        startActivityForResult(intent, key);
    }
    //Создаем и открываем диалог (редактируем или удаляем запись)
    private void  showAlertDialogEditDelete(final List<Note> itemsSms,final NoteAdapter adapter, final int listPosition, final Note itemSms) {

        //создаем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //открываем диалог
        builder = new AlertDialog.Builder(this);
        builder.setTitle(itemSms.getName());
        builder.setIcon(R.drawable.ic_launcher);
        builder .setPositiveButton("Viewing",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        //открываем активити для редактирвания
                        onNoteAddEditActivity(EDIT_ACTIVITY_KEY, itemSms);
                    }
                })
                .setNeutralButton("Отмена",null
                )
                .setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                //Удаляем из ArrayList
                                itemsSms.remove(listPosition);
                                //обновляем список
                                adapter.notifyDataSetChanged();
                            }
                        });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, sConnection,BIND_AUTO_CREATE);
    }
    //Обновляем список СМС
    public void updateListSms(final boolean client,final List<Note> itemsSms, final NoteAdapter adapter, final SmsService myService){

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (myService == null){
                    return;
                }
                // получаем последнии СМС
                List<Note> mItemsNote  = myService.getmItemsNote();
                //проверка на кво СМС
                if(mItemsNote.size() != 0) {
                    for (Note item : mItemsNote) {
                        itemsSms.add(item);
                    }
                    //обновляем список
                    adapter.notifyDataSetChanged();
                    //очистка массива
                    myService.clearItemsNote();
                } else {
                    if(client){
                        showErrorAlertDialog("No mesgg." );
                    } else {
                        Log.d("SMSupdate","No mesgg.");
                    }
                }
            }
        });
    }
    //диалог для ошибок
    public void showErrorAlertDialog(String errMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error:")
                .setMessage(errMessage)
                .setIcon(R.drawable.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}