package com.example.gens.myapplication_fragment_test_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

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
            viewHolder.idTextView = (TextView) convertView.findViewById(R.id.nameTextViewfragment);
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.descriptionTextViewfragment);
            viewHolder.dateView = (TextView) convertView.findViewById(R.id.dateViewfragment);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Note item = getItem(position);
        //проверка
        if(item != null){
            viewHolder.idTextView.setText(item.getName());
            viewHolder.nameTextView.setText(item.getDescription());
            viewHolder.dateView.setText(item.getDateFormat());
        }

        return convertView;
    }
    private static class ViewHolder {
        public TextView idTextView;
        public TextView nameTextView;
        public TextView dateView;
    }
}
