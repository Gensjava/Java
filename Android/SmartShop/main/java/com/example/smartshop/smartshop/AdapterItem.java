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

public class AdapterItem extends BaseAdapter {
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

    AdapterItem(Context context, ArrayList<Product> products) {
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
       
        switch (position){
            case 0:
                view = lInflater.inflate(R.layout.item_view_peger_product, parent, false);
                //заполняем
                fillPeger(view);
                break;
            case 1:
                view = lInflater.inflate(R.layout.item_full_discription, parent, false);
                break;
            case 2:
                view = lInflater.inflate(R.layout.item_price, parent, false);
                break;
            case 3:
                view = lInflater.inflate(R.layout.item_discription, parent, false);
                TextView textViewDiscription = (TextView) view.findViewById(R.id.item_view_discription);

                textViewDiscription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {                        
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent(ACTION_DISRIPTION, FragmentItem.idItem);
                    }
                });
                break;
            case 4:
                view = lInflater.inflate(R.layout.item_deliver, parent, false);
                TextView textViewDeliver = (TextView) view.findViewById(R.id.item_text_deliver);

                textViewDeliver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent(ACTION_DELIVER,FragmentItem.idItem);
                    }
                });
                break;
            default:
                view = lInflater.inflate(R.layout.item_listview, parent, false);
                break;
        }
        return view;
    }
    //заполняем инфой рекламнный блок ViewPeger
    void fillPeger(View view){
        rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        names = new String[] { "Васька", "Барсик", "Мурзик", "Рыжик", "Пушок",
                "Снежок", "Борис", "Филя", "Сёма", "Кузя" };

        count = new String[] { "880", "760", "758", "702", "690", "674", "651",
                "649", "630", "625" };

        picture_resid = new int[] { R.drawable.flatscreen, R.drawable.flatscreen,
                R.drawable.flatscreen, R.drawable.flatscreen, R.drawable.flatscreen,
                R.drawable.flatscreen, R.drawable.flatscreen, R.drawable.flatscreen,
                R.drawable.flatscreen, R.drawable.flatscreen };

        viewPager = (ViewPager) view.findViewById(R.id.item_pager_product);
        adapter = new AdapterViewPager(ctx, rank, names, count,
                picture_resid);
        viewPager.setAdapter(adapter);
        
        viewPager.setCurrentItem(5);
        final CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator_item_titles);
        indicator.setViewPager(viewPager);
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String idItem);
    }
}
