package com.example.smartshop.smartshop;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gens on 10.02.2015.
 */
public class AdapterPagerShare extends BaseAdapter {
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

    AdapterPagerShare(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;

        view = lInflater.inflate(R.layout.item_listview, parent, false);
        ImageView imageViewOne = (ImageView) view.findViewById(R.id.imageView_itemOne);

        imageViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener = (onSomeEventListener) ctx;
                someEventListener.someEvent("imageView_itemOne");
            }
        });

        ImageView imageViewTwo = (ImageView) view.findViewById(R.id.imageView_itemTwo);

        imageViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener = (onSomeEventListener) ctx;
                someEventListener.someEvent("imageView_itemTwo");
            }
        });

        Product p = getProduct(position);
        fillGoods(view, position, p);
        // }
        return view;
    }
    public interface onSomeEventListener {
        public void someEvent(String view_id);
    }
    //заполняем инфой товар
    void fillGoods(View view, int position, Product p){
        ((TextView) view.findViewById(R.id.textView_itemOne)).setText( p.getName());
         ((TextView) view.findViewById(R.id.textView_itemTwo)).setText(p.getName());

        ((ImageView) view.findViewById(R.id.imageView_itemOne)).setImageResource(p.getImage());
        ((ImageView) view.findViewById(R.id.imageView_itemTwo)).setImageResource(p.getImage());

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

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        adapter = new AdapterViewPager(ctx, rank, names, count,
                picture_resid);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(5);
    }

    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }
}
