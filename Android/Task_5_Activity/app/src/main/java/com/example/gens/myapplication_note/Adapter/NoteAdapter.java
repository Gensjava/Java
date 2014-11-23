package com.example.gens.myapplication_note.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gens.myapplication_note.Model.Note;
import com.example.gens.myapplication_note.R;

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

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Note item = getItem(position);
        //проверка
        if(item != null){
            viewHolder.idTextView.setText(item.getmName());
            viewHolder.nameTextView.setText(String.valueOf(item.getmDescription()));
        }

        return convertView;
    }

    private static class ViewHolder {
        public TextView idTextView;
        public TextView nameTextView;
    }
}
