package com.example.gens.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Gens on 22.11.2014.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    private final LayoutInflater mLayoutInflater;
    private final int mLayoutResId;

    public NoteAdapter(Context context, int resource, List<Note> objects ) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
        mLayoutResId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Создаем механизм кеша
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mLayoutResId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.idTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
            viewHolder.dateView = (TextView) convertView.findViewById(R.id.dateView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Note item = getItem(position);
        //проверка
        if(item != null){
            viewHolder.idTextView.setText(item.getName());
            viewHolder.nameTextView.setText(String.valueOf(item.getDescription()));
            viewHolder.dateView.setText(getDateTimeFormat(item.getDate()));
        }

        return convertView;
    }
    //возвращает дату и время
    private String getDateTimeFormat(Date dateTimeNote){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(dateTimeNote);

        return strDate;
    }
    private static class ViewHolder {
        public TextView idTextView;
        public TextView nameTextView;
        public TextView dateView;
    }
}
