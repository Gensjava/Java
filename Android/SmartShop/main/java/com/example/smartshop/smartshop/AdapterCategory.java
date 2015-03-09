package com.example.smartshop.smartshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gens on 10.02.2015.
 */
public class AdapterCategory extends ArrayAdapter<Product> implements View.OnClickListener  {

    private final LayoutInflater mLayoutInflater;
    
    AdapterCategory(Context context,final int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
    }
    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_all_category, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.categoryTextView = (TextView) convertView.findViewById(R.id.category_all_text);
            viewHolder.categoryTextView.setOnClickListener(this);
            
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
         }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        
    }
    private static class ViewHolder {
        public TextView categoryTextView;
    }
}
