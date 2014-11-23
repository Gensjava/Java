package com.example.gens.myapplication_note.Actyvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gens.myapplication_note.Model.Note;
import com.example.gens.myapplication_note.R;

import java.util.Date;

/**
 * Created by Gens on 22.11.2014.
 */
public class NoteEditActivity  extends Activity implements View.OnClickListener {

    private static final String TAG = NoteEditActivity.class.getSimpleName();
    private TextView nameTextView,descriptionTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_note);

        //Получаем поля из макета
        nameTextView =  (TextView) findViewById(R.id.EditVieName);
        descriptionTextView =  (TextView) findViewById(R.id.EditViewDescription);

        //получаем  параметры из главного активити
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(MyActivity.EXTRA_MY_KEY)) {
                final Note note = (Note) getIntent().getSerializableExtra(MyActivity.EXTRA_MY_KEY);
                nameTextView.setText(note.getmName().toString());
                descriptionTextView.setText(note.getmDescription().toString());
            }
        }
    }

    // Получаем текущее дату системы
    // Возвращаем дату "текущюю дату"
    private Date getDate() {
        //текущая дата
        long curTime = System.currentTimeMillis();
        Date date = new Date(curTime);
        return date;
    }
    //Сохраняем запись заметки
    private void saveMenuNot(){

        //получаем поля
        //имя
        final String name = nameTextView.getText().toString();
        //описание
        final String description  = descriptionTextView.getText().toString();
        //Текущая дата
        final Date date = getDate();

        //проверка полей
        if(fieldСheck(name, description)){
            return;
        }
        // создаем новую запись
        Note note = new Note(name,description, date);
        //передаем данные
        Intent intent = new Intent();
        intent.putExtra(MyActivity.EXTRA_MY_KEY, note);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    //Проверка полей на заполнения
    public boolean fieldСheck(String name,String description){
        boolean Сheck = false;
        if (name.length() == 0 || description.length() == 0 ){
            Toast.makeText(this, "Error: Не все поля заполнены!", Toast.LENGTH_SHORT).show();
            Сheck = true;
        }
        return Сheck;
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        //создаем свое меню с двумя кнопками сохранить и отмена
        final MenuItem custom = menu.add(0, R.id.menu_custom, 0,"Custom");
        custom.setActionView(R.layout.menu_custom);
        custom.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        final MenuItem menuItem = menu.findItem(R.id.menu_custom);
        final View actionView = menuItem.getActionView();

        final View ButtonOK = actionView.findViewById(R.id.menuButtonOK);
        ButtonOK.setOnClickListener(this);

        final View ButtonСancel = actionView.findViewById(R.id.menuButtonСancel);
        ButtonСancel.setOnClickListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.menuButtonOK:
                saveMenuNot();
                break;
            case R.id.menuButtonСancel:
                finish();
                break;
            default:
                Log.w(TAG, "Error: " + view.getId());
                break;
        }
    }

}
