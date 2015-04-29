package ua.smartshop.Adapters;

/**
 * Created by Gens on 03.02.2015.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import ua.smartshop.AsyncWorker;
import ua.smartshop.IWorkerCallback;
import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.Сonstants;

public class ProductItemAdapter extends BaseAdapter implements IWorkerCallback {

    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Product> objects;
    private onSomeEventListener someEventListener ;
    private ProductPagerAdapter adapter;
    private ViewPager viewPager;
    private CirclePageIndicator indicator;

    public static final String ACTION_DISRIPTION = "ACTION_DISRIPTION";
    public static final String ACTION_DELIVER = "ACTION_DELIVER";

    public ProductItemAdapter(Context context, ArrayList<Product> products) {
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

            HashMap<String, String> params = new HashMap<String, String>();
            params.put(Сonstants.VALUE_KEY_ITEM_ID,item.getId());

            doSomethingAsyncOperaion(params, Сonstants.url_get_category_product_file_imege , TypeRequest.GET);

            viewPager = (ViewPager) view.findViewById(R.id.item_pager_product);
            indicator = (CirclePageIndicator) view.findViewById(R.id.indicator_item_titles);

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

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest) {
        }.execute();
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            Product[] product = new Product[mPJSONArray.length()];
            for (int i = 0; i < mPJSONArray.length(); i++) {
                product[i] =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), Product.class);
            }
            adapter = new ProductPagerAdapter(ctx, product);

            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);
            indicator.setViewPager(viewPager);

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

    public interface onSomeEventListener {
        public void someEvent(String view_id, String idItem);
    }
}
