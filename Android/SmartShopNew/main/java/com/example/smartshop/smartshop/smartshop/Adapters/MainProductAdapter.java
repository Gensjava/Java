package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

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

    public MainProductAdapter(Context c, final Product[] Products) {
        mContext = c;
        mPoducts = Products;
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

            convertView = inflater.inflate(R.layout.product_list_item, parent, false);

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.main_product_item_imege);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.main_product_item_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (item != null){

            final String URL = item.getWayImage();

            Picasso.with(mContext)
                    .load(URL)
                    .into(viewHolder.imageView);

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    someEventListener = (onSomeEventListener) mContext;
                    someEventListener.someEvent(ACTION_ITEM, String.valueOf(item.getId()));
                }
            });

            viewHolder.textView.setText(String.valueOf(mContext.getString(R.string.price) + item.getPrice()+ mContext.getString(R.string.currency)));
        }

        return convertView;
    }

    private static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

}
