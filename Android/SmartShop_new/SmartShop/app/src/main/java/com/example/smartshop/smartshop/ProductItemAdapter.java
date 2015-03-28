package com.example.smartshop.smartshop;

/**
 * Created by Gens on 03.02.2015.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class ProductItemAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;

    ViewPager viewPager;
    PagerAdapter adapter;
    String[] rank;
    String[] names;
    String[] count;
    int[] picture_resid;
    onSomeEventListener someEventListener ;

    public static final String ACTION_DISRIPTION = "onClick_item_discription";
    public static final String ACTION_DELIVER = "onClick_item_deliver";

    ProductItemAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;

        final Product item = (Product) getItem(position);
        if(item != null){
            
            view = lInflater.inflate(R.layout.product, parent, false);

            TextView txtKod = (TextView) view.findViewById(R.id.item_text_kod);
            TextView txtName = (TextView) view.findViewById(R.id.item_text_name_product);
            TextView txtPrice = (TextView) view.findViewById(R.id.item_text_price);
            TextView txtDescription = (TextView) view.findViewById(R.id.item_text_full_discription);
            //
            txtKod.setText(item.getKod());
            txtName.setText(item.getName());
            txtPrice.setText(item.getPrice()+" грн.");
            txtDescription.setText(item.getDescription());

            //заполняем
            fillPeger(view,item);

            TextView textViewDiscription = (TextView) view.findViewById(R.id.item_view_discription);

            textViewDiscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    someEventListener = (onSomeEventListener) ctx;
                    someEventListener.someEvent(ACTION_DISRIPTION, item.getId());
                }
            });

            TextView textViewDeliver = (TextView) view.findViewById(R.id.item_text_deliver);

            textViewDeliver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    someEventListener = (onSomeEventListener) ctx;
                    someEventListener.someEvent(ACTION_DELIVER, item.getId());
                }
            });
        }

        return view;
    }
    //заполняем инфой рекламнный блок ViewPeger
    void fillPeger(View view, Product item ){
//        rank = new String[] { "1", "2" };
//
//        names = new String[] {item.getWayImage(), item.getWayImage()};
//
//        count = new String[] { "880", "760" };
//
//        picture_resid = new int[] { R.drawable.flatscreen, R.drawable.flatscreen };
//
//        viewPager = (ViewPager) view.findViewById(R.id.item_pager_product);
//        adapter = new PagerViewAdapter(ctx, rank, names, count,
//                picture_resid);
//        viewPager.setAdapter(adapter);
//
//        viewPager.setCurrentItem(0);
//        final CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator_item_titles);
//        indicator.setViewPager(viewPager);
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String idItem);
    }
}
