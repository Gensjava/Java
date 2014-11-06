package com.example.gens.myapplication_animal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MyActivity extends Activity {
    private static final int ITEMS_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Получаем объекты Animal
        Cat cat = new Cat(0,"cat") ;
        //
        Doc doc = new Doc(1,"doc") ;
        //создаем ArrayList
        final ArrayList<Animal> items = new ArrayList<Animal>(ITEMS_COUNT);
        //ссылки
        Animal item_cat;
        Animal item_doc;

        //инициализация
        item_cat = cat;
        item_doc = doc;

        //добавляем
        items.add(item_cat);
        items.add(item_doc);

        //инициализируем адаптер
        final MyAdapter adapter = new MyAdapter(this, R.layout.list_item_item, items);

      //добавляем адаптер в ListView
        final ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setAdapter(adapter);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
