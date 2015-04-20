package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ua.smartshop.Models.Cart;
import ua.smartshop.R;

public class CartAdapter extends ArrayAdapter<Cart>  {

    private final LayoutInflater mLayoutInflater;
    private Context ctx;
    private onSomeEventListener someEventListener ;
    public static final String TEG_GART_TOTAL = "TEG_GART_TOTAL";

    public CartAdapter(Context context, final int resource, final ArrayList<Cart> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
       
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.cart, parent, false);

            viewHolder = new ViewHolder();
          
            viewHolder.positionTextView = (TextView) convertView.findViewById(R.id.cart_position_textView);
            viewHolder.discriptionTextView = (TextView) convertView.findViewById(R.id.cart_discription_textView);
            viewHolder.priceTextView = (TextView) convertView.findViewById(R.id.cart_price_textView);
            ///
            viewHolder.numberTextView = (TextView) convertView.findViewById(R.id.cart_number_textView);
            viewHolder.sumTextView = (TextView) convertView.findViewById(R.id.cart_sum_textView);
            //
            viewHolder.deleteButton = (Button) convertView.findViewById(R.id.cart_delete_button);
            viewHolder.plusButton = (Button) convertView.findViewById(R.id.cart_plus_button);
            viewHolder.minusButton = (Button) convertView.findViewById(R.id.cart_minus_button);

           
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Cart item = getItem(position);

        if(item != null){
            viewHolder.positionTextView.setText(String.valueOf(position + 1));
            viewHolder.discriptionTextView.setText(item.getProduct().getDescription());
            viewHolder.priceTextView.setText(String.valueOf(item.getPrice()));
            viewHolder.numberTextView.setText(String.valueOf(item.getNumber()));
            //
            viewHolder.sumTextView.setText(String.valueOf(item.getSum()));
            ImageView imageView = (ImageView) convertView.findViewById(R.id.cart_imageView);
           
            Picasso.with(ctx)
                    .load(item.getProduct().getWayImage())
                    .into(imageView);
            
            //слушатели
            viewHolder.plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick(v,  item);
                    //
                    someEventListener = (onSomeEventListener) ctx;
                    someEventListener.someEvent(TEG_GART_TOTAL, "");
                }
            });
            viewHolder.minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getNumber() > 1){
                        mOnClick(v,  item);
                        //
                        someEventListener = (onSomeEventListener) ctx;
                        someEventListener.someEvent(TEG_GART_TOTAL, null);
                    }
                }
            });
            viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick(v, item);
                    someEventListener = (onSomeEventListener) ctx;
                    someEventListener.someEvent(TEG_GART_TOTAL, null);
                }
            });

        }
        return convertView;
    }

    private static class ViewHolder {
       
        public TextView positionTextView;
        public TextView discriptionTextView;
        public TextView priceTextView;
        public TextView numberTextView;
        public TextView sumTextView;
        public Button deleteButton;
        public Button plusButton;
        public Button minusButton;
    }

    public void mOnClick(View view, Cart item) {

        switch (view.getId()) {
            case R.id.cart_plus_button:
                item.setNumber(item.getNumber() + 1);
                item.setSum(item.getPrice() * item.getNumber());
                break;
            case R.id.cart_minus_button:
                if (item.getNumber() > 1){
                    item.setNumber(item.getNumber() - 1);
                    item.setSum(item.getPrice() * item.getNumber());
                }
                break;
            case R.id.cart_delete_button:
                Cart.getmCart().remove(item);
                break;
            default:
                break;
        }
        //обновляем
        notifyDataSetChanged();
    }
    
    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

}
