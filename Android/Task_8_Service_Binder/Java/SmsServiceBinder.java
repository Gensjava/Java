package com.example.gens.myapplication_sms_binder_2;

/**
 * Created by Gens on 29.12.2014.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Gens on 29.12.2014.
 */
public class SmsServiceBinder extends Service {

    private MyBinder binder = new MyBinder();
    public static final String ACTION_SERVICE_SMS = "ACTION_SERVICE_SMS";
    private int notifyID = 0;
    private int numMessages = 0;
    private ExecutorService executorService;
    private List<Note> mItemsNote;
    private NotificationManager notificationManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Выполняем передачу СМС в другом потоке
        MyRun mr = new MyRun(intent);
        executorService.execute(mr);

        return START_STICKY;
    }
    //возвращает дату и время
    private String getDateTimeFormat(Date dateTimeNote){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(dateTimeNote);
        return strDate;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //поток
        executorService = Executors.newFixedThreadPool(1);
        //Создаем ArrayList для хранения СМС
        mItemsNote = new ArrayList<Note>();
        //
        Context context = getApplicationContext();
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
    //Создаем Notification для СМС
    private void showNotification(Note note) {

        //создаем Intent для просмотра СМС при нажатии  Notification
        Intent intent = new Intent(this, SmsViewingMessagesActivity.class);
        intent.putExtra(ACTION_SERVICE_SMS, note);
        //Создаем PendingIntent для Notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Создаем сам notification
        final Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(note.getName())
                .setContentIntent(contentIntent)
                .setContentText(note.getDescription())
                .setNumber(++numMessages)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //
        notificationManager.notify(notifyID++ , notification);
    }
    //Создаем класс для потока
     class MyRun implements Runnable {
        public Intent mIntent;

        public MyRun(Intent intent) {
            this.mIntent = intent;
        }
        public void run() {
            //получем поля СМС
            String sms_body = mIntent.getExtras().getString("sms_body");
            String sms_address = mIntent.getExtras().getString("sms_address");
            // создаем новую запись
            Note note = new Note(sms_address,sms_body, new Date(System.currentTimeMillis()), getDateTimeFormat(new Date(System.currentTimeMillis())));
            //Создаем Notification с СМС
            showNotification(note);
            //
            mItemsNote.add(note);
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    class MyBinder extends Binder {

        SmsServiceBinder getService() {
            return SmsServiceBinder.this;
        }
    }
    public List<Note> getmItemsNote() {
        return mItemsNote;
    }

    public void clearItemsNote() {
        mItemsNote.clear();
    }
}

