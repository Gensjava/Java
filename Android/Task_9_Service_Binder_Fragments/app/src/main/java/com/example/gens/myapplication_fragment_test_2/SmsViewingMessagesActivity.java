package com.example.gens.myapplication_fragment_test_2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Gens on 29.12.2014.
 */
public class SmsViewingMessagesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details);

        //Получаем поля из макета
        TextView nameTextView =  (TextView) findViewById(R.id.nameTextViewfragment);
        TextView descriptionTextView =  (TextView) findViewById(R.id.descriptionTextViewfragment);
        TextView currentTimeTextView =  (TextView) findViewById(R.id.dateViewfragment);

        //получаем  параметры из сервиса
        final Bundle extras = getIntent().getExtras();
        //
        if (extras != null) {
            if (extras.containsKey(SmsService.ACTION_SERVICE_SMS) || extras.containsKey(SmsMainActivityBinder.EXTRA_NOTE_KEY)) {
                Note note = null;

                if(extras.containsKey(SmsService.ACTION_SERVICE_SMS)){
                    note = (Note) extras.get(SmsService.ACTION_SERVICE_SMS);
                }
                else
                if(extras.containsKey(SmsMainActivityBinder.EXTRA_NOTE_KEY)){
                    note = (Note) extras.get(SmsMainActivityBinder.EXTRA_NOTE_KEY);
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
