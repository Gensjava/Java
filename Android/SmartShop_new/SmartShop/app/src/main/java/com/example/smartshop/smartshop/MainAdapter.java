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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainAdapter extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ProductDual> objects;

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
                convertView = lInflater.inflate(R.layout.main_peger, parent, false);
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
                convertView = lInflater.inflate(R.layout.category, parent, false);

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

                convertView = lInflater.inflate(R.layout.main_category, parent, false);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("category_all_text","");
                    }
                });

                break;
            case 3:
                convertView = lInflater.inflate(R.layout.category, parent, false);

                TextView textViewShares = (TextView) convertView.findViewById(R.id.category_all_text);
                textViewShares.setText("Акции");

                break;
            default:
                convertView = null;
                final ViewHolder viewHolder;
                final ProductDual item = (ProductDual) getItem(position);

                if (convertView == null) {
                    convertView = lInflater.inflate(R.layout.product_dual, parent, false);

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
                            .into(imageViewOne);

                    ImageView imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

                    Picasso.with(ctx)
                            .load(URL_TWO)
                            .into(imageViewTwo);

                    viewHolder.textView_itemOne.setText("Цена " + item.getProductOne().getPrice()+" грн.");
                    viewHolder.textView_itemTwo.setText("Цена " + item.getProductTwo().getPrice()+" грн.");

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

    }

    //заполняем инфой рекламнный блок ViewPeger
    private void fillPeger(View view){

        PagerAdapter adapter = new PagerViewAdapter(ctx, getCategoryProducts());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

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

    private  CategoryProduct[] getCategoryProducts(){

        String tags[] = new String[3];
        tags[0] = Сonstants.TAG_NAME;
        tags[1] = Сonstants.TAG_PID;
        tags[2] = Сonstants.TAG_WAY_IMAGE;
        CategoryProduct[] categoryProduct = new CategoryProduct[0];
        //
        HashMap<String, String> params = new HashMap<String, String>();
        //
        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_get_slider_main_page , tags , ctx);
        utilAsyncTask.execute();

        try {
            utilAsyncTask.get();

            List<HashMap> mArrayValues  = utilAsyncTask.getArrayValues();
            categoryProduct = new CategoryProduct[mArrayValues.size()];
            //
            HashMap<String, String> mValues;
            CategoryProduct itemCategoryProduct;

            if(!(mArrayValues.size() == 0)){
                for (byte i = 0; i < mArrayValues.size(); i++) {
                    mValues = mArrayValues.get(i);
                    itemCategoryProduct = new CategoryProduct( mValues.get("pid"), mValues.get("name"), Сonstants.url_main + mValues.get("wayimage"));
                    categoryProduct[i] = itemCategoryProduct;
                }
            } else {
                //открываем фрагмент с ошибкой
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return categoryProduct;
    }
}

