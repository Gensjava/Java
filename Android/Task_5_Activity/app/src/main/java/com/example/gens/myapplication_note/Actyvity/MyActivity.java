package com.example.gens.myapplication_note.Actyvity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gens.myapplication_note.Model.Note;
import com.example.gens.myapplication_note.Adapter.NoteAdapter;
import com.example.gens.myapplication_note.R;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    private ListView listView;
    private List<Note> items;
    private NoteAdapter adapter;
    private int listPosition;
    private Note item;

    public static final String EXTRA_MY_KEY = "EXTRA_MY_KEY";
    public static final int NEW_ACTIVITY_KEY = 100;
    public static final int EDIT_ACTIVITY_KEY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //иницилизируем listView
        initializlListView ();
        //инициализируем ArrayList
        items = new ArrayList<Note>();
        //создаем свой адаптер
        adapter = new NoteAdapter(this, R.layout.list_item_item, items);
        //добавляем adapter
        listView.setAdapter(adapter);
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
    //Открываем активити для редактирования  или создания новой записи
    private void onNewEditNoteActivity(int key){

       Intent intent = new Intent(MyActivity.this, NoteEditActivity.class);
        //если редактировать
        if(key == EDIT_ACTIVITY_KEY ){
            intent.putExtra(EXTRA_MY_KEY, item);
        }
       startActivityForResult(intent, key);
 }
     // обрабытываем результат полученный от активити
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode,
                                    final Intent data) {
        Note note = null;
        if(data != null){
           note = (Note) data.getSerializableExtra(EXTRA_MY_KEY);
         }
        if(note == null){
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case NEW_ACTIVITY_KEY:
                //Добавляем в список
                items.add(note);
                //обновляем список
                adapter.notifyDataSetChanged();
                break;
            case EDIT_ACTIVITY_KEY:
                //редактируем список
                items.set(listPosition, note);
                //обновляем список
                adapter.notifyDataSetChanged();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
    //Создаем и открываем диалог (редактируем или удаляем запись)
    private void  showAlertDialogEditDelete() {

        //создаем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //открываем диалог
        builder = new AlertDialog.Builder(this);
        builder.setTitle(item.getmName());
        builder.setIcon(R.drawable.ic_launcher);
        builder .setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        //открываем активити для редактирвания
                       //openEditNoteActivity();
                        onNewEditNoteActivity(EDIT_ACTIVITY_KEY);
                    }
                })
                .setNeutralButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                            }
                        })
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
            onNewEditNoteActivity(NEW_ACTIVITY_KEY);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
