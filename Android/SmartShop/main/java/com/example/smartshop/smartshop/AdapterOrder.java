package com.example.smartshop.smartshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gens on 10.02.2015.
 */
public class AdapterOrder extends ArrayAdapter<Order> implements View.OnClickListener {

    private final LayoutInflater mLayoutInflater;

    public AdapterOrder(final Context context, final int resource, final List<Order> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_cart, parent, false);

            viewHolder = new ViewHolder();
          
            viewHolder.sellerTextView = (TextView) convertView.findViewById(R.id.cart_seller_textView);
            viewHolder.discriptionTextView = (TextView) convertView.findViewById(R.id.cart_discription_textView);
            viewHolder.priceTextView = (TextView) convertView.findViewById(R.id.cart_price_textView);
            ///
            viewHolder.numberTextView = (TextView) convertView.findViewById(R.id.cart_number_textView);
            viewHolder.deliveryTextView = (TextView) convertView.findViewById(R.id.cart_delivery_textView);
            viewHolder.deliveryButton = (Button) convertView.findViewById(R.id.cart_delivery_button);
            viewHolder.deleteButton = (Button) convertView.findViewById(R.id.cart_delete_button);
            viewHolder.sumTextView = (TextView) convertView.findViewById(R.id.cart_sum_textView);
            viewHolder.sumDeliveryTextView = (TextView) convertView.findViewById(R.id.cart_sum_delivery_textView);
            viewHolder.totalTextView = (TextView) convertView.findViewById(R.id.cart_total_textView);
            //слушатели
            viewHolder.deleteButton.setOnClickListener(this);
            viewHolder.deliveryButton.setOnClickListener(this);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Order item = getItem(position);
        
        if(item != null){
            viewHolder.sellerTextView.setText("Андрей");
            viewHolder.discriptionTextView.setText(item.getProduct().getDescription());
            viewHolder.priceTextView.setText(String.valueOf(item.getPrice()));
            viewHolder.numberTextView.setText(String.valueOf(item.getNumber()));
            viewHolder.deliveryTextView.setText(String.valueOf("delivery"));
            //
            viewHolder.sumTextView.setText(String.valueOf(item.getSum()));
            viewHolder.sumDeliveryTextView.setText(String.valueOf("SumDelivery"));
            viewHolder.totalTextView.setText(String.valueOf(item.getSum()));
        }
        return convertView;
    }

    @Override
    public void onClick(final View v) {
        v.getTag();
    }

    private static class ViewHolder {
       
        public TextView sellerTextView;
        public TextView discriptionTextView;
        public TextView priceTextView;
        public TextView numberTextView;
        public TextView deliveryTextView;
        public TextView sumTextView;
        public TextView sumDeliveryTextView;
        public TextView totalTextView;
        public Button deliveryButton;
        public Button deleteButton;

    }
}
