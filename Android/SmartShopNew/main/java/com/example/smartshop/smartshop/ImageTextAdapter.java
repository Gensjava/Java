package com.example.smartshop.smartshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gens on 09.04.2015.
 */
public class ImageTextAdapter extends BaseAdapter {
    private Context mContext;
    private onSomeEventListener someEventListener ;
    public static final String ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN = "ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN";

    ArrayList<CategoryProduct> mCategory = new ArrayList<CategoryProduct>();

    public ImageTextAdapter(Context c, final ArrayList<CategoryProduct> categoryProducts) {
        mContext = c;
        mCategory = categoryProducts;
    }

    public int getCount() {
        return mCategory.size();
    }

    public Object getItem(int position) {
        return mCategory.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.cellgrid, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView) grid.findViewById(R.id.imagepart);
        imageView.setPadding(11,11,11, 11);
        TextView textView = (TextView) grid.findViewById(R.id.textpart);
        textView.setPadding(2,2, 2,2);

        imageView.setImageResource(mCategory.get(position).getImageView());
        textView.setText(mCategory.get(position).getName());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                someEventListener = (onSomeEventListener) mContext;
                someEventListener.someEvent(ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN, String.valueOf(mCategory.get(position).getId()));
            }
        });


        return grid;
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }
}
