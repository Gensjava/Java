package com.example.smartshop.smartshop;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
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
        View view = convertView;

        switch (position){
            case 0:
                view = lInflater.inflate(R.layout.item_view_peger, parent, false);
                //заполняем рекламнный блок
                ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);

                fillPeger(view);

                viewPager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("view_pager","");
                    }
                });
                break;
            case 1:
                view = lInflater.inflate(R.layout.item_all_category, parent, false);

                TextView textView = (TextView) view.findViewById(R.id.category_all_text);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("category_all_text","");
                    }
                });

                break;
            case 2:

                view = lInflater.inflate(R.layout.item_group_category, parent, false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("category_all_text","");
                    }
                });

                break;
            default:
                
                ProductDual p = getProduct(position);
                
                final String URL_ONE = p.getProductOne().getWayImage();
                final String URL_TWO = p.getProductTwo().getWayImage();
                
                final String ITEM_ID_ONE = p.getProductOne().getId();
                final String ITEM_ID_TWO = p.getProductTwo().getId();
                
                
                view = lInflater.inflate(R.layout.item_listview, parent, false);
                ImageView imageViewOne = (ImageView) view.findViewById(R.id.imageView_itemOne);
               
                Picasso.with(ctx)
                        .load(URL_ONE)
                        //.resize(128, 128)
                        .into(imageViewOne , new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });

                imageViewOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("imageView_itemOne", ITEM_ID_ONE);
                    }
                });

                ImageView imageViewTwo = (ImageView) view.findViewById(R.id.imageView_itemTwo);

                Picasso.with(ctx)
                        .load(URL_TWO)
                        //.resize(128, 128)
                        .into(imageViewTwo , new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });
                imageViewTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("imageView_itemTwo", ITEM_ID_TWO);
                    }
                });

               
                fillGoods( view,  position,  p);
                break;
        }
        return view;
    }

    //заполняем инфой товар
    void fillGoods(View view, int position, ProductDual p){
        ((TextView) view.findViewById(R.id.textView_itemOne)).setText("Цена " + p.getProductOne().getPrice()+" грн. "+p.getProductOne().getId());
        ((TextView) view.findViewById(R.id.textText_itemTwo)).setText("Цена " + p.getProductTwo().getPrice()+" грн. "+p.getProductTwo().getId());

        ((ImageView) view.findViewById(R.id.imageView_itemOne)).setImageResource(p.getProductOne().getImage());
        ((ImageView) view.findViewById(R.id.imageView_itemTwo)).setImageResource(p.getProductTwo().getImage());

//        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
//        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");"Цена " + price+" грн."
        // ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);
//
        //CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
//        //присваиваем чекбоксу обработчик
       // cbBuy.setOnCheckedChangeListener(myCheckChangList);
//        // пишем позицию
//        cbBuy.setTag(position);
//        // заполняем данными из товаров: в корзине или нет
//        cbBuy.setChecked(p.box);
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
        
        final CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.titles);
        indicator.setViewPager(viewPager);
        

    }

    // товар по позиции
    ProductDual getProduct(int position) {
        return ((ProductDual) getItem(position));
    }

    // содержимое корзины
    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (ProductDual p : objects) {
            // если в корзине
           // if (p.mBox)
               // box.add(p);
        }
        return box;
    }

    // обработчик для чекбоксов
    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // меняем данные товара (в корзине или нет)
          //  getProduct((Integer) buttonView.getTag()).mBox = isChecked;
        }
    };

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

    public class SamplePagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            View imview = lInflater.inflate(R.layout.info_image, null);
            ((ViewPager) container).addView(imview);
            return imview;
        }

        public SamplePagerAdapter() {
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public int getCount(){
            return 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object){
            return view.equals(object);
        }

        @Override
        public void finishUpdate(View arg0){
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1){
        }

        @Override
        public Parcelable saveState(){
            return null;
        }

        @Override
        public void startUpdate(View arg0){
        }
    }
}

