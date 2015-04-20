package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ua.smartshop.Models.Cart;
import ua.smartshop.R;


public class OrderAdapter extends ArrayAdapter<Cart>  {

    private final LayoutInflater mLayoutInflater;
    private Context ctx;

    public OrderAdapter(Context context, final int resource, final ArrayList<Cart> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
       
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.order_item, parent, false);

            viewHolder = new ViewHolder();
          
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.order_item_name);
            viewHolder.priceTextView = (TextView) convertView.findViewById(R.id.order_item_price);
            viewHolder.numberTextView = (TextView) convertView.findViewById(R.id.order_item_number);
            ///
            viewHolder.sumTextView = (TextView) convertView.findViewById(R.id.order_item_sum);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Cart item = getItem(position);

        if(item != null){
            viewHolder.nameTextView.setText(item.getProduct().getName());
            viewHolder.sumTextView.setText(String.valueOf(item.getSum()));
            viewHolder.priceTextView.setText(String.valueOf(item.getPrice()));
            viewHolder.numberTextView.setText(String.valueOf(item.getNumber()));
            //
            ImageView imageView = (ImageView) convertView.findViewById(R.id.order_item_image);
           
            Picasso.with(ctx)
                    .load(item.getProduct().getWayImage())
                    .into(imageView);


        }
        return convertView;
    }

    private static class ViewHolder {
       
        public TextView nameTextView;
        public TextView sumTextView;
        public TextView priceTextView;
        public TextView numberTextView;

    }


}
