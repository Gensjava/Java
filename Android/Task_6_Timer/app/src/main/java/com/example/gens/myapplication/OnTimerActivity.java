package com.example.gens.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class OnTimerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.list_item_item);
        super.onCreate(savedInstanceState);

        //Получаем поля из макета
        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView descriptionTextView =  (TextView) findViewById(R.id.descriptionTextView);
        TextView currentTimeTextView =  (TextView) findViewById(R.id.dateView);

        //получаем  параметры из главного активити
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
           if (extras.containsKey(NoteEditActivity.EXTRA_NOTE_KEY_TIMER)) {
                final Note note = (Note) extras.get(NoteEditActivity.EXTRA_NOTE_KEY_TIMER);
                if(note == null){
                    return;
               }
               nameTextView.setText(note.getName().toString());
               descriptionTextView.setText(note.getDescription().toString());
               currentTimeTextView.setText(note.getDate().toString());
            }
        }
    }
}
