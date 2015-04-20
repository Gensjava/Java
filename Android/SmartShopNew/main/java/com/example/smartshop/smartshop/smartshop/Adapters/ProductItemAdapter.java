package ua.smartshop.Adapters;

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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.UtilAsyncTask;
import ua.smartshop.Utils.Сonstants;

public class ProductItemAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Product> objects;
    private onSomeEventListener someEventListener ;

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

        PagerAdapter adapter = new ProductPagerAdapter(ctx, getProducts(item));

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.item_pager_product);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        final CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator_item_titles);
        indicator.setViewPager(viewPager);

    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String idItem);
    }
    private  Product[] getProducts(Product item){

        String tags[] = new String[3];
        tags[0] = Сonstants.TAG_NAME;
        tags[1] = Сonstants.TAG_PID;
        tags[2] = Сonstants.TAG_WAY_IMAGE;

        Product[] arrProduct = new Product[0];
        //
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("idItem",item.getId());
        //
        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_get_category_product_file_imege , tags , ctx, TypeRequest.GET);
        utilAsyncTask.execute();

        try {
            utilAsyncTask.get();

            List<HashMap> mArrayValues  = utilAsyncTask.getArrayValues();
            arrProduct = new Product[mArrayValues.size()];
            //
            HashMap<String, String> mValues;
            Product itemProduct;

            if(!(mArrayValues.size() == 0)){

                //дополнительные фото
                for (byte i = 0; i < mArrayValues.size(); i++) {
                    mValues = mArrayValues.get(i);
                    itemProduct = new Product(
                            mValues.get(Сonstants.TAG_PID),
                            mValues.get(Сonstants.TAG_NAME),
                            mValues.get(Сonstants.TAG_WAY_IMAGE));
                    arrProduct[i] = itemProduct;
                }
            } else {
                //открываем фрагмент с ошибкой
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return arrProduct;
    }
}
