package ua.smartshop.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import ua.smartshop.Activitys.ProfileActivity;
import ua.smartshop.R;

/**
 * Created by Gens on 11.05.2015.
 */
public class StatusService extends Service {

    // constant
    public static final long NOTIFY_INTERVAL =  60 * 1000; // 60 seconds
    private static final String ACTION_SERVICE_STATUS = "ACTION_SERVICE_STATUS";
    private NotificationManager notificationManager;

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;
    private int numMessages;
    private int notifyID;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }
    @Override
    public void onCreate() {
        Context context = getApplicationContext();
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        // cancel if already existed
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0,
                NOTIFY_INTERVAL);

    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    showNotification();
                }
            });
        }

        // используйте метод в сообщении для вывода текущего времени
        private String getDateTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "[dd/MM/yyyy - HH:mm:ss]", Locale.getDefault());
            return sdf.format(new Date());
        }
    }

    //Создаем Notification для СМС
    private void showNotification() {

        //создаем Intent для просмотра СМС при нажатии  Notification
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ACTION_SERVICE_STATUS, "");
        //Создаем PendingIntent для Notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Создаем сам notification

        final Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_action_notification_bar)
                .setContentTitle("Статус в заказах")
                .setContentIntent(contentIntent)
                .setContentText("№1 изменился на статус в пути.")
                .setNumber(++numMessages)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //
        notificationManager.notify(notifyID++ , notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}