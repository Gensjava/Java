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

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;




/**
 * Created by Gens on 21.02.2015.
 */
public class MainAdapter extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ProductDual> objects;

    ViewPager viewPager;
    PagerAdapter adapter;
    String[] rank;
    String[] names;
    String[] count;
    int[] picture_resid;
    onSomeEventListener someEventListener ;

    MainAdapter(Context context, ArrayList<ProductDual> products) {
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
        // View view = convertView;

        switch (position){
            case 0:
                convertView = lInflater.inflate(R.layout.item_view_peger, parent, false);
                //заполняем рекламнный блок
                ViewPager viewPager = (ViewPager) convertView.findViewById(R.id.view_pager);

                fillPeger(convertView);


                viewPager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("view_pager","");
                      
                    }
                });
                break;
            case 1:
                convertView = lInflater.inflate(R.layout.item_all_category, parent, false);

                TextView textView = (TextView) convertView.findViewById(R.id.category_all_text);
                textView.setText("Все категории");

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("category_all_text","");
                    }
                });

                break;
            case 2:

                convertView = lInflater.inflate(R.layout.item_group_category, parent, false);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("category_all_text","");
                    }
                });

                break;
            default:
                convertView = null;
                final ViewHolder viewHolder;
                final ProductDual item = (ProductDual) getItem(position);

                if (convertView == null) {
                    convertView = lInflater.inflate(R.layout.item_listview, parent, false);

                    viewHolder = new ViewHolder();

                    viewHolder.textView_itemOne = (TextView) convertView.findViewById(R.id.textView_itemOne);
                    viewHolder.textView_itemTwo = (TextView) convertView.findViewById(R.id.textView_itemTwo);

                    // viewHolder.imageView_itemOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);
                    //viewHolder.imageView_itemTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                if(item != null){

                    final String URL_ONE = item.getProductOne().getWayImage();
                    final String URL_TWO = item.getProductTwo().getWayImage();

                    final String ITEM_ID_ONE = item.getProductOne().getId();
                    final String ITEM_ID_TWO =item.getProductTwo().getId();

                    ImageView imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);

                    Picasso.with(ctx)
                         .load(URL_ONE)
                   // .resize(128, 128)
                        .into(imageViewOne);

                    ImageView imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

                    Picasso.with(ctx)
                       .load(URL_TWO)
                    //.resize(128, 128)
                      .into(imageViewTwo);

                    viewHolder.textView_itemOne.setText("Цена " + item.getProductOne().getPrice()+" грн. "+item.getProductOne().getId());
                    viewHolder.textView_itemTwo.setText("Цена " + item.getProductTwo().getPrice()+" грн. "+item.getProductTwo().getId());

                    //viewHolder.imageView_itemOne.setImageResource((imageViewOne)imageViewOne);
                    //viewHolder.imageView_itemTwo.setImageResource(item.getProductTwo().getImage());

                    //слушатели
                    imageViewOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            someEventListener = (onSomeEventListener) ctx;
                            someEventListener.someEvent("imageView_itemOne", ITEM_ID_ONE);
                        }
                    });

                    imageViewTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            someEventListener = (onSomeEventListener) ctx;
                            someEventListener.someEvent("imageView_itemTwo", ITEM_ID_TWO);
                        }
                    });
                }

                break;
        }
        return convertView;
    }

    private static class ViewHolder {

        public TextView textView_itemOne;
        public TextView textView_itemTwo;
        //public ImageView imageView_itemOne;
        // public ImageView imageView_itemTwo;
    }

    //заполняем инфой рекламнный блок ViewPeger
    void fillPeger(View view){
        rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
//
        names = new String[] {Сonstants.url_main_peger +"mobilnye-telefony.jpg", Сonstants.url_main_peger +"bytovaya-tehnika.jpg", Сonstants.url_main_peger +"ipad-air-2.png", Сonstants.url_main_peger +"elektronnye-knigi.jpg", Сonstants.url_main_peger +"smart-service.com.ua.jpg",
                Сonstants.url_main_peger +"tehnika-karcher.jpg", Сonstants.url_main_peger +"originalnye-aksessuary-bmw.jpg", Сonstants.url_main_peger +"naushniki.jpg", Сonstants.url_main_peger +"iphone-6.jpg" };
//
//        count = new String[] { "880", "760", "758", "702", "690", "674", "651",
//                "649", "630" };


        picture_resid = new int[] { R.drawable.flatscreen, R.drawable.flatscreen,
                R.drawable.flatscreen, R.drawable.flatscreen, R.drawable.flatscreen,
                R.drawable.flatscreen, R.drawable.flatscreen, R.drawable.flatscreen,
                R.drawable.flatscreen };

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        adapter = new AdapterViewPager(ctx, rank, names, count, picture_resid);
        
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        final CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.titles);
        indicator.setViewPager(viewPager);

    }

    // товар по позиции
    ProductDual getProduct(int position) {
        return ((ProductDual) getItem(position));
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

}

