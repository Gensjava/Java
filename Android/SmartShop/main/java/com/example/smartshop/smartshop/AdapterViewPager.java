package com.example.smartshop.smartshop;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Gens on 21.02.2015.
 */
public class AdapterViewPager extends PagerAdapter {
    private Context mContext;
    private String[] mRank;
    private String[] mCatName;
    private String[] mCounter;
    private int[] mPictureID;
    LayoutInflater mInflater;
    onSomeEventListener someEventListener ;

    public AdapterViewPager(Context context, String[] rank, String[] name,
                            String[] counter, int[] resid) {
        this.mContext = context;
        this.mRank = rank;
        this.mCatName = name;
        this.mCounter = counter;
        this.mPictureID = resid;
    }

    @Override
    public int getCount() {
        return mRank.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        TextView textViewRank;
        TextView textViewName;
        TextView textViewCounter;
        ImageView imageViewAvatar;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mInflater.inflate(R.layout.item_peger, container,
                false);

        textViewRank = (TextView) itemView.findViewById(R.id.textViewRank);
        textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        textViewCounter = (TextView) itemView.findViewById(R.id.textViewCount);

        textViewRank.setText(mRank[position]);
        textViewName.setText(mCatName[position]);
        textViewCounter.setText(mCounter[position]);

        imageViewAvatar = (ImageView) itemView.findViewById(R.id.imageViewAvatar);
        imageViewAvatar.setImageResource(mPictureID[position]);

        itemView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {

                
                someEventListener = (onSomeEventListener) mContext;
                someEventListener.someEvent("view_pager","");
            }
        });
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

