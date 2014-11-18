package com.example.gens.myapplication_dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;



public class MyActivity extends Activity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ArrayList<Event> items = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Вкл.таймер
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //делаем поиск события
                showEvent();
            }
        }, 0, 1000); //тикаем каждую секунду без задержки

    }
   //добавляем в список новое сообщение
    public void addValueListView(String name, String description, String date, String time, Status status){

        Event event = new Event(name, description, date, time, status);
        //Добавляем в ArrayList
        items.add(event);

        //инициализируем адаптер
        final MyAdapter adapter = new MyAdapter(this, R.layout.list_item_item, items);
        adapter.setNotifyOnChange(true);

        //добавляем адаптер в ListView
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
      // adapter.notifyDataSetChanged();

    }

    //открываем окно сообщения
    public void  showAlertDialog(String sDateTime, String sName, String sStatus) {
        //создаем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        //получаем каст макет
        final View messageView = layoutInflater.inflate(R.layout.custom_view, null);
        //Получаем поля
        final TextView eventTextView =  (TextView) messageView.findViewById(R.id.TextViewEvent);
        final TextView dateTextView =  (TextView) messageView.findViewById(R.id.TextViewData);
        final TextView timeTextView =  (TextView) messageView.findViewById(R.id.TextViewStatus);
        //
        eventTextView.setText(eventTextView.getText()+sName);
        dateTextView.setText(dateTextView.getText()+ sDateTime);
        timeTextView.setText(timeTextView.getText()+sStatus);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Событие");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setView(messageView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alert = builder.create();
        alert.show();
    }
    //показывем окно время
    private void showAlertDialogTime() {
        final int hour = 20;
        final int minute = 30;

        final TimePickerDialog timePickerDialog
                = new TimePickerDialog(this,  this, hour, minute, false);
        timePickerDialog.show();
    }
//показываем окно дата
    private void showAlertDialogDate() {
        final int year = 2014;
        final int month = 11;
        final int day = 19;

        final DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, this, year, month, day);
        datePickerDialog.show();
    }
//событие нажатия кнопок
    @Override
    public void onClick(View view) {

       switch (view.getId()) {
           case R.id.btDate:
               showAlertDialogDate();
               break;
            case R.id.btTime:
                showAlertDialogTime();
                break;
            case R.id.add:
                final TextView editTextView =  (TextView) findViewById(R.id.editText);
                final TextView dateTextView =  (TextView) findViewById(R.id.tvDate);
                final TextView timeTextView =  (TextView) findViewById(R.id.tvTime);

                String event = editTextView.getText().toString();
                String date  = dateTextView.getText().toString();
                String time  = timeTextView.getText().toString();

                //Проверка
                boolean checkValues = checkValues( event,  date,  time);
                if (!checkValues) {
                    Toast.makeText(getApplicationContext(), "Error: Не заполнены все поля!", Toast.LENGTH_SHORT).show();
                } else {
                    addValueListView(event , event, date, time, Status.PLAN);
                }

               break;
           default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//проверяем поля
    private  boolean checkValues(String event, String date, String time){
        boolean checkValues = false;

        if (event == "" || date == "" || time == "") {
            return checkValues;
        }
        checkValues = true;

        return checkValues;
    }
//записываем данные даты
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
        final String date = String.format("%d.%d.%d", dayOfMonth, monthOfYear + 1, year);
        final TextView dateTextView =  (TextView) findViewById(R.id.tvDate);
        dateTextView.setText(date);
    }
    //записываем данные по времени
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final String time = String.format("%d:%d", hourOfDay, minute);

        final TextView timeTextView =  (TextView) findViewById(R.id.tvTime);
        timeTextView.setText(time);
    }
    // Получаем текущее врямя системы
    // Возвращаем строку "текущее время"
    private static String getTime() {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        return "" + dateFormat.format(time);

    }
    // Получаем текущее дату системы
    // Возвращаем строку "текущюю дату"
    private static String getDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        return "" + dateFormat.format(date);

    }
    //делаем поиск сообщения и показывеем окно
    public void showEvent(){

    this.runOnUiThread(new Runnable() {
    @Override
    public void run() {
        String getDate = getDate();
        String getTime = getTime();
        //создаем индекс для поиска
        String indexTimeDate = getDate+getTime;

        for (Event e: items) {
            //создаем индекс для поиска
            String indexTimeDateEvent  = e.getDate()+e.getTime();
            if (indexTimeDate.equals(indexTimeDateEvent)){
                //
                String sDateTime  = e.getDate().toString() + " / " +e.getTime().toString();
                String sName  = e.getName().toString();
                String sStatus  = e.getStatus().toString();
                //назначаем статус
                e.setStatus(Status.PAST);
                //открываем окно
                showAlertDialog(sDateTime, sName, sStatus);
            }
        }
    }
});



    }


}
