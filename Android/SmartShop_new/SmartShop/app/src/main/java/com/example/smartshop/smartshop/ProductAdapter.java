package com.example.smartshop.smartshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Gens on 21.02.2015.
 */
public class ProductAdapter extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ProductDual> objects;

    onSomeEventListener someEventListener ;

    ProductAdapter(Context context, ArrayList<ProductDual> products) {
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
            
                if(item.getProductOne() != null){
                final String URL_ONE = item.getProductOne().getWayImage();
                final String ITEM_ID_ONE = item.getProductOne().getId();

                ImageView imageViewOne = (ImageView) convertView.findViewById(R.id.imageView_itemOne);

                Picasso.with(ctx)
                        .load(URL_ONE)
                                // .resize(128, 128)
                        .into(imageViewOne);
                viewHolder.textView_itemOne.setText("Цена " + item.getProductOne().getPrice()+" грн.");
                
                imageViewOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("imageView_itemOne", ITEM_ID_ONE);
                    }
                });
            }
            
            if(item.getProductTwo() != null){
                
                final String URL_TWO = item.getProductTwo().getWayImage();
                final String ITEM_ID_TWO =item.getProductTwo().getId();

                ImageView imageViewTwo = (ImageView) convertView.findViewById(R.id.imageView_itemTwo);

                Picasso.with(ctx)
                        .load(URL_TWO)
                                //.resize(128, 128)
                        .into(imageViewTwo);

                viewHolder.textView_itemTwo.setText("Цена " + item.getProductTwo().getPrice()+" грн.");
                imageViewTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent("imageView_itemTwo", ITEM_ID_TWO);
                    }
                });

            }
           
        }

       
        return convertView;
    }

    private static class ViewHolder {

        public TextView textView_itemOne;
        public TextView textView_itemTwo;
        //public ImageView imageView_itemOne;
        // public ImageView imageView_itemTwo;
    }


    // товар по позиции
    ProductDual getProduct(int position) {
        return ((ProductDual) getItem(position));
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

}

