package com.example.gens.myapplication_fragment_test_2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

public class SmsMainActivityBinder extends Activity implements FragmentTitle.onSomeEventListener {
    public static final String EXTRA_NOTE_KEY = "EXTRA_NOTE_KEY";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public void someEvent(Note note) {
        Fragment fragmentDetalis = getFragmentManager().findFragmentById(R.id.detalis_fragment);
        //поля
        ((TextView) fragmentDetalis.getView().findViewById(R.id.nameTextViewfragment)).setText(note.getName());
        ((TextView) fragmentDetalis.getView().findViewById(R.id.descriptionTextViewfragment)).setText(note.getDescription());
        ((TextView) fragmentDetalis.getView().findViewById(R.id.dateViewfragment)).setText(note.getDateFormat());

    }
}
