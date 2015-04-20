package ua.smartshop.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.UtilAsyncTask;
import ua.smartshop.Utils.Сonstants;

import static ua.smartshop.R.drawable.*;

public class MainAdapter extends BaseAdapter  {

    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Product[]> objects;
    private onSomeEventListener someEventListener ;

    private static final String TEXT_CATEGORY_ALL = "Все категории";// текст
    private static final String TEXT_SHARES = "Акции";//текст

    public static final String ACTION_CATEGORY_ALL = "ACTION_CATEGORY_ALL";//клик на все категории
    public static final String ACTION_ITEM_ONE = "ACTION_ITEM_ONE";//клик на первый товар imageView
    public static final String ACTION_ITEM_TWO = "ACTION_ITEM_TWO";//клик на второй товар imageView

    public MainAdapter(Context context, ArrayList<Product[]> products) {
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
                convertView = lInflater.inflate(R.layout.main_category_grid, parent, false);

                GridView cridView = (GridView) convertView.findViewById(R.id.main_grid_view);
                cridView.setAdapter(new MainCategoryAdapter(ctx, fillGridView()));

                break;
            case 3:
                convertView = lInflater.inflate(R.layout.category, parent, false);

                TextView textViewShares = (TextView) convertView.findViewById(R.id.category_all_text);
                textViewShares.setText(TEXT_SHARES);

                break;
            default:

                convertView = null;

                final ViewHolder viewHolder;
                final Product[] item = (Product[]) getItem(position);

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

//                   for (Product i : item){
//                       final String URL = i.getWayImage();
//                       final String ITEM_ID = i.getId();
//
//                       ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_itemOne);
//
//                    Picasso.with(ctx)
//                            .load(URL)
//                            .into(imageView);
//
//                       imageView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                           someEventListener = (onSomeEventListener) ctx;
//                           someEventListener.someEvent(ACTION_ITEM_ONE, ITEM_ID);
//                       }
//                    });
//
//                    }

                    final String URL_ONE = item[0].getWayImage();
                    final String URL_TWO = item[1].getWayImage();

                    final String ITEM_ID_ONE = item[0].getId();
                    final String ITEM_ID_TWO = item[1].getId();

                    ImageView imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);

                    Picasso.with(ctx)
                            .load(URL_ONE)
                            .into(imageViewOne);

                   // imageViewOne.setImageBitmap(GetBitmapDual(imageViewOne) );

                    ImageView imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

                    Picasso.with(ctx)
                            .load(URL_TWO)
                            .into(imageViewTwo);

                    viewHolder.textView_itemOne.setText(Сonstants.TEXT_PRICE + item[0].getPrice()+ Сonstants.TEXT_CURRENCY);
                    viewHolder.textView_itemTwo.setText(Сonstants.TEXT_PRICE + item[1].getPrice()+ Сonstants.TEXT_CURRENCY);

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

    Bitmap GetBitmapDual(ImageView imageView)  {
        Bitmap bitmap = null;
        try {

            bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);

            Bitmap bitmap1 = ((BitmapDrawable)imageView.getDrawable()).getBitmap(); //blue

            Bitmap bitmap2 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.top_byu); //green
            Drawable drawable1 = new BitmapDrawable(bitmap1);
            Drawable drawable2 = new BitmapDrawable(bitmap2);

            drawable1.setBounds(100, 100, 400, 400);
            drawable2.setBounds(100, 100, 300, 300);
            drawable1.draw(c);
            drawable2.draw(c);

        } catch (Exception e) {
        }
        return bitmap;
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

        mCategory.add(new CategoryProduct("2984","Apple Store\n\n", apple));
        mCategory.add(new CategoryProduct("3092","Телефоны и планшеты\n", ipad));
        mCategory.add(new CategoryProduct("700","Бытовая техника\n", consumer_electronics));
        mCategory.add(new CategoryProduct("140","ТВ / Аудио / Видео / Фото\n", tv));
        mCategory.add(new CategoryProduct("2635","Ноутбуки и компьютерная техника", laptop));
        mCategory.add(new CategoryProduct("5596","Портативная техника\n", portable_equipment));
        mCategory.add(new CategoryProduct("3045","Автотовары\n\n", avto));
        mCategory.add(new CategoryProduct("4032","Детский мир\n\n", children));

        return mCategory;
    }

}

