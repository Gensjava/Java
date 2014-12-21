package com.example.gens.myapplication;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NoteEditActivity  extends Activity implements View.OnClickListener {

    public static final String EXTRA_NOTE_KEY_TIMER = "EXTRA_NOTE_KEY_TIMER";
    private static final int NOTIFY_ID = 103;
    private TextView nameTextView, descriptionTextView, currentTimeTextView;
    private NotificationManager notificationManager;
    private Calendar calendar;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        //Получаем поля из макета
        nameTextView =  (TextView) findViewById(R.id.editVieName);
        descriptionTextView =  (TextView) findViewById(R.id.editViewDescription);
        currentTimeTextView =  (TextView) findViewById(R.id.editViewDate);
        //получаем менеджер заданий
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //создаем объект календарь
        calendar = new GregorianCalendar();
        //получаем менеджер Notifications
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //получаем  параметры из главного активити
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(MyActivity.EXTRA_NOTE_KEY)) {
                final Note note = (Note) extras.get(MyActivity.EXTRA_NOTE_KEY);
                if(note == null){
                    return;
                }
                nameTextView.setText(note.getName().toString());
                descriptionTextView.setText(note.getDescription().toString());
                currentTimeTextView.setText(note.getDate().toString());
            }
        }
    }
    //Сохраняем запись заметки
    private void addNote(){
        //получаем поля
        //имя
        final String name = nameTextView.getText().toString();
        //описание
        final String description  = descriptionTextView.getText().toString();
        //Текущая дата
        final Date date = calendar.getTime();
        //проверка полей
        if(fieldСheck(name, description, date)){
            return;
        }
        // создаем новую запись
        Note note = new Note(name,description, date);
        //добавляем сообщение в бар
        onCompareIntentClick(note);
        //передаем данные
        Intent intent = new Intent();
        intent.putExtra(MyActivity.EXTRA_NOTE_KEY, note);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
    //Проверка полей на заполнения
    public boolean fieldСheck(String name, String description, Date data ){
        boolean check = false;
        if (name.length() == 0 || description.length() == 0 || data.toString().length() == 0){
            showErrorAlertDialog("Не все поля заполнены!");
            check = true;
        }
        return check;
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
                addNote();
                break;
            case R.id.menuButtonСancel:
                finish();
                break;
            case R.id.buttonDate:
                showCustomAlertDialogDateTime();
                break;
            default:
                showErrorAlertDialog("Error: " + view.getId());
                break;
        }
    }
    // создаем pendingIntent с intent и определяем время запуска
    private void onCompareIntentClick(Note note) {

        Intent intent = new Intent(NoteEditActivity.this, OnTimerActivity.class);
        intent.putExtra(EXTRA_NOTE_KEY_TIMER, note);

        PendingIntent pendingIntent = PendingIntent.getActivity(NoteEditActivity.this,0,intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        //назначаем время запуска
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        //
        sendNotification(NOTIFY_ID,pendingIntent,note);
    }
    //записывем Notification
    void sendNotification(final int id, final PendingIntent pendingIntent,Note note) {

        final Notification notification;
        notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(note.getName())
                .setContentText(note.getDescription())
                .setContentIntent(pendingIntent)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(id, notification);
    }
    //возвращает дату и время
    private String getDateTimeFormat(Date dateTimeNote){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(dateTimeNote);

        return strDate;
    }
    //Создаем открываем диалог (добавляем запись дату)
    private void  showCustomAlertDialogDateTime() {

        //создаем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        //получаем каст макет
        final View messageView = layoutInflater.inflate(R.layout.custom_date_time, null);

        //Получаем поля из макета
        final DatePicker datePicker =  (DatePicker) messageView.findViewById(R.id.customDatePicker);
        final TimePicker timePicker =  (TimePicker) messageView.findViewById(R.id.customTimePicker);

        //открываем диалог
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Select date and time");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setView(messageView);
        //кнопки
        builder .setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int id) {

                        //получаем поля даты
                        calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),timePicker.getCurrentHour(),timePicker.getCurrentMinute());
                        //устнавливаем текст даты
                        currentTimeTextView.setText(getDateTimeFormat(calendar.getTime()));

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Отмена",null);

        final AlertDialog alert = builder.create();
        alert.show();
    }
    //диалог для ошибок
    public void showErrorAlertDialog(String errMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error:")
                .setMessage(errMessage)
                .setIcon(R.drawable.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
