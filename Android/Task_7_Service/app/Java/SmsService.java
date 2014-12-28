package com.example.gens.myapplication_sms;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Gens on 27.12.2014.
 */
public class SmsService extends Service {

    public static final String ACTION_SERVICE_SMS = "ACTION_SERVICE_SMS";
    private int notifyID = 0;
    private int numMessages = 0;
    private ExecutorService executorService;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //получем поля СМС
        String sms_body = intent.getExtras().getString("sms_body");
        String sms_address = intent.getExtras().getString("sms_address");
        // создаем новую запись
        Note note = new Note(sms_address,sms_body, new Date(System.currentTimeMillis()), getDateTimeFormat(new Date(System.currentTimeMillis())));
        //Выполняем передачу СМС в другом потоке
        MyRun mr = new MyRun(note);
        executorService.execute(mr);
        //Создаем Notification с СМС
        showNotification(note);
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
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //Создаем Notification для СМС
    private void showNotification(Note note) {

        //создаем Intent для просмотра СМС при нажатии  Notification
        Intent intent = new Intent(this, ViewingMessagesActivity.class);
        intent.putExtra(ACTION_SERVICE_SMS, note);
        //Создаем PendingIntent для Notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //
        Context context = getApplicationContext();
        //
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //
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
        Note note;
        public MyRun(Note note) {
            this.note = note;
        }
        public void run() {
            //Создаем Intent для передачи его в Активити в фокусе
            Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
            intent.putExtra(MainActivity.BROADCAST_ACTION, note);
            //отправляем в Активити СМС
            sendBroadcast(intent);
        }
    }
}
