package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Utils.Сonstants;


public class ProductAdapter extends BaseAdapter{

    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Product[]> objects;

    private onSomeEventListener someEventListener ;
    public static final String ACTION_ITEM_ONE_PRODUCT = "ACTION_ITEM_ONE_PRODUCT";//клик на первый товар imageView
    public static final String ACTION_ITEM_TWO_PRODUCT = "ACTION_ITEM_TWO_PRODUCT";//клик на второй товар imageView

    ProductAdapter(Context context, ArrayList<Product[]> products) {
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
            
                if( item[0] != null){

                final String URL_ONE = item[0].getWayImage();
                final String ITEM_ID_ONE = item[0].getId();

                ImageView imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);

                Picasso.with(ctx)
                        .load(URL_ONE)
                        .into(imageViewOne);
                viewHolder.textView_itemOne.setText(Сonstants.TEXT_PRICE + item[0].getPrice()+ Сonstants.TEXT_CURRENCY);
                
                imageViewOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent(ACTION_ITEM_ONE_PRODUCT, ITEM_ID_ONE);
                    }
                });
            }
            
            if(item[1] != null){
                
                final String URL_TWO = item[1].getWayImage();
                final String ITEM_ID_TWO = item[1].getId();

                ImageView imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

                Picasso.with(ctx)
                        .load(URL_TWO)
                        .into(imageViewTwo);

                viewHolder.textView_itemTwo.setText(Сonstants.TEXT_PRICE + item[1].getPrice()+ Сonstants.TEXT_CURRENCY);
                imageViewTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent(ACTION_ITEM_TWO_PRODUCT, ITEM_ID_TWO);
                    }
                });
            }
           
        }
        return convertView;
    }

    private static class ViewHolder {

        public TextView textView_itemOne;
        public TextView textView_itemTwo;

    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

}

