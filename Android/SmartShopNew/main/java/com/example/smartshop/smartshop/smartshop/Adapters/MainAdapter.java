package ua.smartshop.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import ua.smartshop.AsyncWorker;
import ua.smartshop.IWorkerCallback;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.Сonstants;

public class MainAdapter extends BaseAdapter implements IWorkerCallback {

    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Product[]> objects;
    private onSomeEventListener someEventListener ;
    private MainPagerAdapter adapter;
    private CategoryProduct[] categoryProduct = new CategoryProduct[0];

    private static final String TEXT_CATEGORY_ALL = "Все категории";// текст
    private static final String TEXT_SHARES = "Акции";//текст

    public static final String ACTION_CATEGORY_ALL = "ACTION_CATEGORY_ALL";//клик на все категории
    public static final String ACTION_ITEM = "ACTION_ITEM";//клик на товар imageView

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
                //заполняем рекламнный блок
                convertView = lInflater.inflate(R.layout.main_peger, parent, false);

                doSomethingAsyncOperaion(new HashMap<String, String>(), Сonstants.url_get_slider_main_page , TypeRequest.GET);

                adapter = new MainPagerAdapter(ctx, Сonstants.categoryProduct);

                ViewPager viewPager = (ViewPager) convertView.findViewById(R.id.view_pager);
                viewPager.setAdapter(adapter);
                viewPager.setCurrentItem(0);

                CirclePageIndicator indicator = (CirclePageIndicator) convertView.findViewById(R.id.titles);
                indicator.setViewPager(viewPager);

                break;
            case 1:
                convertView = lInflater.inflate(R.layout.main_category, parent, false);

                TextView textView = (TextView) convertView.findViewById(R.id.main_category_all_text);
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
                //http://www.materialpalette.com/amber/red
                convertView = lInflater.inflate(R.layout.main_category_grid, parent, false);

                GridView cridView = (GridView) convertView.findViewById(R.id.main_grid_view);
                cridView.setAdapter(new MainCategoryAdapter(ctx, CategoryProduct.getMainCategory()));

                break;
            case 3:
                convertView = lInflater.inflate(R.layout.main_category, parent, false);

                TextView textViewShares = (TextView) convertView.findViewById(R.id.main_category_all_text);
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
                    viewHolder.imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);
                    viewHolder.imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

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

                    // imageViewOne.setImageBitmap(GetBitmapDual(imageViewOne) );

                    final String URL_ONE = item[0].getWayImage();
                    final String URL_TWO = item[1].getWayImage();

                    final String ITEM_ID_ONE = item[0].getId();
                    final String ITEM_ID_TWO = item[1].getId();

                    ImageView imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);

                    Picasso.with(ctx)
                            .load(URL_ONE)
                            .into(imageViewOne);



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
                            someEventListener.someEvent(ACTION_ITEM, ITEM_ID_ONE);
                        }
                    });

                    imageViewTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            someEventListener = (onSomeEventListener) ctx;
                            someEventListener.someEvent(ACTION_ITEM, ITEM_ID_TWO);
                        }
                    });
                }

                break;
        }
        return convertView;
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            Сonstants.categoryProduct = new CategoryProduct[mPJSONArray.length()];
            for (int i = 0; i < mPJSONArray.length(); i++) {
                Сonstants.categoryProduct[i] =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), CategoryProduct.class);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(final Throwable t) {

    }

    @Override
    public void onEnd() {

    }

    private static class ViewHolder {
        public TextView textView_itemOne;
        public TextView textView_itemTwo;
        public ImageView imageViewOne;
        public ImageView imageViewTwo;
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest) {
        }.execute();
    }

//    Bitmap GetBitmapDual(ImageView imageView)  {
//        Bitmap bitmap = null;
//        try {
//
//            bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(bitmap);
//
//            Bitmap bitmap1 = ((BitmapDrawable)imageView.getDrawable()).getBitmap(); //blue
//
//            Bitmap bitmap2 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.top_byu); //green
//            Drawable drawable1 = new BitmapDrawable(bitmap1);
//            Drawable drawable2 = new BitmapDrawable(bitmap2);
//
//            drawable1.setBounds(100, 100, 400, 400);
//            drawable2.setBounds(100, 100, 300, 300);
//            drawable1.draw(c);
//            drawable2.draw(c);
//
//        } catch (Exception e) {
//        }
//        return bitmap;
//    }

}

