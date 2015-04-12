package com.example.smartshop.smartshop;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainAdapter extends BaseAdapter  {

    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<ProductDual> objects;
    private onSomeEventListener someEventListener ;

    private static final String TEXT_CATEGORY_ALL = "Все категории";// текст
    private static final String TEXT_SHARES = "Акции";//текст

    public static final String ACTION_CATEGORY_ALL = "ACTION_CATEGORY_ALL";//клик на все категории
    public static final String ACTION_ITEM_ONE = "ACTION_ITEM_ONE";//клик на первый товар imageView
    public static final String ACTION_ITEM_TWO = "ACTION_ITEM_TWO";//клик на второй товар imageView

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

        switch (position){
            case 0:
                convertView = lInflater.inflate(R.layout.main_peger, parent, false);
                //заполняем рекламнный блок
                fillPeger(convertView);
                break;
            case 1:
                convertView = lInflater.inflate(R.layout.category, parent, false);

                TextView textView = (TextView) convertView.findViewById(R.id.category_all_text);
                textView.setText(TEXT_CATEGORY_ALL);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent(ACTION_CATEGORY_ALL, null);
                    }
                });

                break;
            case 2:
                convertView = lInflater.inflate(R.layout.main_grid, parent, false);

                GridView cridView = (GridView) convertView.findViewById(R.id.main_grid_view);
                cridView.setAdapter(new ImageTextAdapter(ctx,fillGridView()));

                break;
            case 3:
                convertView = lInflater.inflate(R.layout.category, parent, false);

                TextView textViewShares = (TextView) convertView.findViewById(R.id.category_all_text);
                textViewShares.setText(TEXT_SHARES);

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

                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                if(item != null){

                    final String URL_ONE = item.getProductOne().getWayImage();
                    final String URL_TWO = item.getProductTwo().getWayImage();

                    final String ITEM_ID_ONE = item.getProductOne().getId();
                    final String ITEM_ID_TWO = item.getProductTwo().getId();

                    ImageView imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);

                    Picasso.with(ctx)
                            .load(URL_ONE)
                            .into(imageViewOne);

                    ImageView imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

                    Picasso.with(ctx)
                            .load(URL_TWO)
                            .into(imageViewTwo);

                    viewHolder.textView_itemOne.setText(Сonstants.TEXT_PRICE + item.getProductOne().getPrice()+ Сonstants.TEXT_CURRENCY);
                    viewHolder.textView_itemTwo.setText(Сonstants.TEXT_PRICE + item.getProductTwo().getPrice()+ Сonstants.TEXT_CURRENCY);

                    //слушатели
                    imageViewOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            someEventListener = (onSomeEventListener) ctx;
                            someEventListener.someEvent(ACTION_ITEM_ONE, ITEM_ID_ONE);
                        }
                    });

                    imageViewTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            someEventListener = (onSomeEventListener) ctx;
                            someEventListener.someEvent(ACTION_ITEM_TWO, ITEM_ID_TWO);
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

        PagerAdapter adapter = new MainPagerAdapter(ctx, getCategoryProducts());

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
        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_get_slider_main_page , tags , ctx, TypeRequest.GET);
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
                    itemCategoryProduct = new CategoryProduct(
                            mValues.get(Сonstants.TAG_PID),
                            mValues.get(Сonstants.TAG_NAME),
                            Сonstants.url_main +
                                    mValues.get(Сonstants.TAG_WAY_IMAGE));
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

    private ArrayList<CategoryProduct> fillGridView() {

        ArrayList<CategoryProduct> mCategory = new ArrayList<>();

        mCategory.add(new CategoryProduct("2984","Apple Store\n\n",R.drawable.apple));
        mCategory.add(new CategoryProduct("3092","Телефоны и планшеты\n",R.drawable.ipad));
        mCategory.add(new CategoryProduct("700","Бытовая техника\n\n",R.drawable.consumer_electronics));
        mCategory.add(new CategoryProduct("140","ТВ / Аудио / Видео / Фото\n",R.drawable.tv));
        mCategory.add(new CategoryProduct("2635","Ноутбуки и компьютерная техника",R.drawable.laptop));
        mCategory.add(new CategoryProduct("5596","Портативная техника\n",R.drawable.portable_equipment));
        mCategory.add(new CategoryProduct("3045","Автотовары\n\n",R.drawable.avto));
        mCategory.add(new CategoryProduct("4032","Детский мир\n\n",R.drawable.children));

        return mCategory;
    }

    public void updateData(JSONArray jsonArray) {
        // update the adapter's dataset
        //mJsonArray = jsonArray;
        notifyDataSetChanged();
    }
}

