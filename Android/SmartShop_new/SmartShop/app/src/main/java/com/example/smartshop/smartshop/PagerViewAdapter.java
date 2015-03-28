package com.example.smartshop.smartshop;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by Gens on 21.02.2015.
 */
public class PagerViewAdapter extends PagerAdapter {
    private Context mContext;
    private CategoryProduct[] mCategoryProduct;
    public static final String ACTION_ONCLIK_ITEM = "ACTION_ONCLIK_ITEM";

    LayoutInflater mInflater;
    onSomeEventListener someEventListener ;

    public PagerViewAdapter(Context context, CategoryProduct categoryProduct [] ) {
        this.mContext = context;
        this.mCategoryProduct = categoryProduct;
    }

    @Override
    public int getCount() {
        return mCategoryProduct.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageViewAvatar;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mInflater.inflate(R.layout.product_peger, container,
                false);

        imageViewAvatar = (ImageView) itemView.findViewById(R.id.imageViewAvatar);

        Picasso.with(mContext)
                .load(String.valueOf(mCategoryProduct[position].getUrl()))
                .into(imageViewAvatar);

        itemView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {

                someEventListener = (onSomeEventListener) mContext;
                someEventListener.someEvent(ACTION_ONCLIK_ITEM, mCategoryProduct[position].getId());
            }
        });
        
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    public interface onSomeEventListener {
        public void someEvent(String view_id,String item_id );
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }

}

