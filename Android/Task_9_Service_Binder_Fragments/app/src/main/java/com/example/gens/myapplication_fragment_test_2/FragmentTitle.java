package com.example.gens.myapplication_fragment_test_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentTitle extends Fragment {

    private ServiceConnection sConnection;
    private onSomeEventListener someEventListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, null);

        ListView listView = (ListView) view.findViewById(R.id.title_fragment);
        //получаем SmsServiceBinder
        final SmsService myService  = ServiceConnected();
        //инициализируем ArrayList
        final List<Note> itemsSms = new ArrayList<Note>();
        //создаем свой адаптер
        final NoteAdapter adapter = new NoteAdapter(getActivity(), R.layout.fragment_details, itemsSms);
        //добавляем adapter
        listView.setAdapter(adapter);
        //
        //Клик на листе
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                someEventListener.someEvent(itemsSms.get(position));
            }
        });
        //запускаем авто получения СМС через определенное время
        //Вкл.таймер
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            //            @Override
            public void run() {
                //Обновляем список СМС
                updateListSms(false,itemsSms, adapter, myService);
            }
        }, 10000, 10000); //
        return view;
    }
    public interface onSomeEventListener {
        public void someEvent(Note note);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        someEventListener = (onSomeEventListener) activity;
    }
    //связь активити и сервисом
    public SmsService  ServiceConnected(){
        SmsService myService = null;

        sConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                SmsService myService = ((SmsService.MyBinder) binder).getService();
            }
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        return  myService;
    }
    //Обновляем список СМС
    public void updateListSms(final boolean client, final List<Note> itemsSms, final NoteAdapter adapter,final SmsService myService){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (myService == null) {
                    return;
                }
                // получаем последнии СМС
                List<Note> mItemsNote = myService.getmItemsNote();
                //проверка на кво СМС
                if (mItemsNote.size() != 0) {
                    for (Note item : mItemsNote) {
                        itemsSms.add(item);
                    }
                    //обновляем список
                    adapter.notifyDataSetChanged();
                    //очистка массива
                    myService.clearItemsNote();
                } else {
                    if (client) {
                        showErrorAlertDialog("No mesgg.");
                    } else {
                        Log.d("SMSupdate", "No mesgg.");
                    }
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().bindService(new Intent(getActivity(), SmsService.class), sConnection, 0);
    }
    //диалог для ошибок
    public void showErrorAlertDialog(String errMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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