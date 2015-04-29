package ua.com.it_st.deliveryman.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import ua.com.it_st.deliveryman.R;

/**
 * Created by Gens on 09.04.2015.
 */
public class MainMenuAdapter extends BaseAdapter {
    private Context mContext;

    public MainMenuAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes 237,200
            imageView = new ImageView(mContext);
          imageView.setLayoutParams(new GridView.LayoutParams(237,200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
           // imageView.setPadding(15,2,15, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    public	Integer[] mThumbIds = {R.drawable.orders, R.drawable.orders,
                                   R.drawable.orders, R.drawable.orders,
                                   R.drawable.orders, R.drawable.orders};

}
