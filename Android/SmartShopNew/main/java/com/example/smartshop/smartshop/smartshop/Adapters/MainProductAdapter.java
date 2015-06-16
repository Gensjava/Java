package ua.smartshop.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import ua.smartshop.Activity.MainActivity;
import ua.smartshop.Models.Product;
import ua.smartshop.R;

/**
 * Created by Gens on 09.04.2015.
 */
public class MainProductAdapter extends BaseAdapter {

    private Context mContext;
    private onSomeEventListener someEventListener ;
    public static final String ACTION_ITEM = "ACTION_ITEM";//клик на товар imageView
    private Product[] mPoducts;
    private LayoutInflater inflater;
    private int mCount;

    public MainProductAdapter(Context c, final Product[] Products,  int count) {
        mContext = c;
        mPoducts = Products;
        mCount = count;
        inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mPoducts.length;
    }

    public Object getItem(int position) {
        return mPoducts[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final ViewHolder viewHolder;
        final Product item = (Product) getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();

            switch (mCount) {
                case 1:
                    convertView = inflater.inflate(R.layout.product_list_item_from_category, parent, false);

                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.product_from_category_photo);
                    viewHolder.priceView = (TextView) convertView.findViewById(R.id.product_from_category_price);
                    viewHolder.textViewDescription = (TextView) convertView.findViewById(R.id.product_from_category_age);
                    viewHolder.textViewName = (TextView) convertView.findViewById(R.id.product_from_category_name);

                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.product_list_item, parent, false);

                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.main_product_item_imege);
                    viewHolder.priceView = (TextView) convertView.findViewById(R.id.main_product_item_price);
                    viewHolder.textViewName = (TextView) convertView.findViewById(R.id.main_product_item_name);

                    break;
                default:
                    break;
            }

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (item != null){

            final String URL = item.getWayImage();

            Picasso.with(mContext)
                    .load(URL)
                    .into(viewHolder.imageView);

            CardView CardView = (CardView) convertView.findViewById(R.id.cv);

            CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    someEventListener = (onSomeEventListener) mContext;
                    someEventListener.someEvent(ACTION_ITEM, String.valueOf(item.getId()));
                }
            });

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    someEventListener = (onSomeEventListener) mContext;
                    someEventListener.someEvent(ACTION_ITEM, String.valueOf(item.getId()));
                }
            });

            viewHolder.priceView.setText(String.valueOf(mContext.getString(R.string.price) + item.getPrice()+ mContext.getString(R.string.currency)));
            viewHolder.textViewName.setText(item.getName());
            if (mCount == 1){

                viewHolder.textViewDescription.setText(item.getDescription());
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        public TextView priceView;
        public TextView textViewName;
        public TextView textViewDescription;
        public ImageView imageView;
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

}
