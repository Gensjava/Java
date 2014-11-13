package com.example.gens.myapplication_munu2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity  {
     int iCheckable = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
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

        switch (item.getGroupId()) {
            case R.id.groupCheckable:

                final TextView textViewCheckable = (TextView) findViewById(R.id.TextViewCheckable);
                iCheckable++;
                textViewCheckable.setText("iCheckable = "+iCheckable);

                return true;
            case R.id.groupRadio:

                final TextView textViewRadio = (TextView) findViewById(R.id.TextViewRadio);
                textViewRadio.setText("sRadioTitle: "+item.getTitle().toString());

                return true;
            case R.id.groupStandart:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    }

