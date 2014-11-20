package com.example.gens.myapplication_notepad;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Date;

import java.util.ArrayList;

public class MyActivity extends Activity {

    private ArrayList<Notepad> items = new ArrayList<Notepad>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //иницилизируем listView
        initializllistView ();
    }

    // Иницилизируем listView + событие клик на список
    private void initializllistView () {
        //иницилизируем listView
        final ListView listView = (ListView) findViewById(R.id.listView);

        //Клик на листе
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //открываем диало для редактирвоания
                showAlertDialogEditDelete(position);
            }
        });
    }
    //добавляем в список новую запись
    private void addValueListView(String name, String description, Date date){
        //создаем запись
        Notepad event = new Notepad( name,  description,  date);
        //Добавляем в ArrayList
        items.add(event);

        //обновляем список
        updateValueListView();
    }
    //редактируем запись в блокноте
    private void editValueListView(String name, String description, Date date,  int positionListView){
        //создаем запись
        Notepad event = new Notepad( name,  description,  date);
        //редактируем в ArrayList
        items.set(positionListView, event);
        //обновляем список
        updateValueListView();
    }
    //удаляем из из списка запись
    private void removedValueListView(int index){

        //Удаляем из ArrayList
        items.remove(index);
        //обновляем список
        updateValueListView();
    }
    //обновляем список
    private void  updateValueListView () {
        //инициализируем адаптер
        final MyAdapter adapter = new MyAdapter(this, R.layout.list_item_item, items);

        //добавляем адаптер в ListView
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_simple) {
            //добавляем запись
            showAlertDialogNew(true, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Получаем текущее дату системы
    // Возвращаем дату "текущюю дату"
    private Date getDate() {
        //текущая дата
        long curTime = System.currentTimeMillis();
        Date date = new Date(curTime);
        return date;
    }
    //Создаем и открываем диалог (добавляем запись или редактируем)
    // newNote - boolean, если true тогда новый инане редактируем запись
    private void  showAlertDialogNew(final boolean newNote, final int positionListView) {

        //создаем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        //получаем каст макет
        final View messageView = layoutInflater.inflate(R.layout.custom_view, null);

        //Получаем поля из макета
        final TextView NameTextView =  (TextView) messageView.findViewById(R.id.TextVieName);
        final TextView descriptionTextView =  (TextView) messageView.findViewById(R.id.TextViewdescription);

        String Title; //заголовок

        if(newNote){ //добавление
            //Текущий Title
            Title = "Add note";
        } else {//редактирвоание
            //Текущий Title
            Title = "Edit note";
            //получаем item  листа
            final Notepad item = items.get(positionListView);
            //подставляем запись из блокнота в диалог
            NameTextView.setText(item.getName().toString());
            descriptionTextView.setText(item.getDescription().toString());
        }

        //открываем диалог
        builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setView(messageView);
        //кнопки
        builder .setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        //получаем поля
                        //имя
                        final String name = NameTextView.getText().toString();
                        //описание
                        final String description  = descriptionTextView.getText().toString();
                        //Текущая дата
                        final Date date = getDate();

                        //добавляем запись
                        if (newNote){
                            //добавляем запись в блокнот
                            addValueListView(name, description, date);
                        } else {
                            //редактируем запись в блокноте
                            editValueListView(name, description, date, positionListView);
                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    //Создаем и открываем диалог (редактируем или удаляем запись)
    //positionListView - позиция в ListView
    private void  showAlertDialogEditDelete(final int positionListView) {

        //создаем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        //получаем item в листе
        final Notepad item = items.get(positionListView);

        //открываем диалог
        builder = new AlertDialog.Builder(this);
        builder.setTitle(item.getName());
        builder.setIcon(R.drawable.ic_launcher);
        builder .setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        showAlertDialogNew(false, positionListView);
                        dialog.cancel();
                    }
                })
                .setNeutralButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                removedValueListView(positionListView);
                                dialog.cancel();
                            }
                        });
        final AlertDialog alert = builder.create();
        alert.show();
    }

}

