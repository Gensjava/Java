package com.example.gens.myapplication_sms;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Gens on 27.12.2014.
 */
public class ViewingMessagesActivity extends ActionBarActivity {

    public static final String ACTION_SMS = "ACTION_SMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_item);

        //Получаем поля из макета
        TextView nameTextView =  (TextView) findViewById(R.id.nameTextView);
        TextView descriptionTextView =  (TextView) findViewById(R.id.descriptionTextView);
        TextView currentTimeTextView =  (TextView) findViewById(R.id.dateView);

        //получаем  параметры из сервиса
        final Bundle extras = getIntent().getExtras();
        //
        if (extras != null) {
            if (extras.containsKey(SmsService.ACTION_SERVICE_SMS) || extras.containsKey(MainActivity.EXTRA_NOTE_KEY)) {
                Note note = null;

                if(extras.containsKey(SmsService.ACTION_SERVICE_SMS)){
                    note = (Note) extras.get(SmsService.ACTION_SERVICE_SMS);
                }
                else
                if(extras.containsKey(MainActivity.EXTRA_NOTE_KEY)){
                    note = (Note) extras.get(MainActivity.EXTRA_NOTE_KEY);
                }
                if(note == null){
                    return;
                }
                nameTextView.setText(note.getName().toString());
                descriptionTextView.setText(note.getDescription().toString());
                currentTimeTextView.setText(note.getDateFormat().toString());
            }
        }
    }
}
