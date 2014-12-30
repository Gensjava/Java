package com.example.gens.myapplication_sms_binder_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

/**
 * Created by Gens on 29.12.2014.
 */
public class SmsMonitorBinder extends BroadcastReceiver {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {

        //делаем проверку на ошибки
        if (intent != null && intent.getAction() != null &&
                ACTION.compareToIgnoreCase(intent.getAction()) == 0) {

            //преобразуем в intent массив
            Object[] pduArray = (Object[]) intent.getExtras().get("pdus");

            SmsMessage[] messages = new SmsMessage[pduArray.length];
            //переводим в формат Pdu чтоб можно было получать сообщения кириллицей

            for (int i = 0; i < pduArray.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
            }
            //получаем поля из СМС
            StringBuilder bodyText = new StringBuilder();
            StringBuilder addressText = new StringBuilder();

            for (int i = 0; i < messages.length; i++) {
                bodyText.append(messages[i].getMessageBody());
                addressText.append(messages[i].getOriginatingAddress());
            }

            String body = bodyText.toString();
            String address = addressText.toString();

            //создаем и отправлеям mIntent текст, номер
            Intent mIntent = new Intent(context, SmsServiceBinder.class);
            mIntent.putExtra("sms_body", body);
            mIntent.putExtra("sms_address", address);
            //стартуем сервис
            context.startService(mIntent);
            //прерываем после получения
            abortBroadcast();

        }
    }
}
