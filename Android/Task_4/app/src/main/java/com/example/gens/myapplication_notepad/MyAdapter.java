package com.example.gens.myapplication_notepad;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yanc_sh51 on 19.11.2014.
 */
public class MyAdapter extends ArrayAdapter<Notepad> implements View.OnClickListener {

    private final LayoutInflater mLayoutInflater;
    private final int mLayoutResId;

    public MyAdapter(final Context context, final int resource, final List<Notepad> objects) {
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
           viewHolder.idTextView.setOnClickListener(this);
           viewHolder.idTextView.setTag(position);

            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);

           convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
       }

        final Notepad item = getItem(position);
        viewHolder. idTextView.setText(item.getName());
        viewHolder.nameTextView.setText(String.valueOf(item.getDescription()));

        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
    private static class ViewHolder {
        public TextView idTextView;
        public TextView nameTextView;
    }
}

