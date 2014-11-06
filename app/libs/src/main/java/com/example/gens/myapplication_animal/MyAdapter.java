package com.example.gens.myapplication_animal;

/**
 * Created by Gens on 06.11.2014.
 */
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter <Animal> {

    private final LayoutInflater mLayoutInflater;
    private final int mLayoutResId;

    public MyAdapter(final Context context, final int resource, final List<Animal> objects) {
        super(context, resource, objects);

        mLayoutInflater = LayoutInflater.from(context);
        mLayoutResId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view = mLayoutInflater.inflate(mLayoutResId, null);

        final Animal item = getItem(position);
        if (position == 0){
            view.setBackgroundColor(Color.BLUE);
        } else {
            view.setBackgroundColor(Color.GRAY);
        }
        final TextView idTextView = (TextView) view.findViewById(R.id.idTextView);
        idTextView.setText(String.valueOf(item.getId()));

        final TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        nameTextView.setText(item.getName());

       return view;
    }
}
