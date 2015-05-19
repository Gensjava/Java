package ua.smartshop.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import ua.smartshop.Models.Product;
import ua.smartshop.R;

public class ProductPagerAdapter extends PagerAdapter {

    private Context mContext;
    private Product[] mProduct;
    private LayoutInflater mInflater;
    private View ui_bar;

    public ProductPagerAdapter(Context context, Product Product[]) {
        this.mContext = context;
        this.mProduct = Product;
    }

    @Override
    public int getCount() {
        return mProduct.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageViewAvatar;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mInflater.inflate(R.layout.product_peger, container,
                false);

        imageViewAvatar = (ImageView) itemView.findViewById(R.id.imageViewAvatar);
         ui_bar = (View) itemView.findViewById(R.id.image_progress_bar);

        Picasso.with(mContext)
                .load(String.valueOf(mProduct[position].getWayImage()))
                .into(imageViewAvatar, new Callback() {
                    @Override
                    public void onSuccess() {
                        ui_bar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        ui_bar.setVisibility(View.INVISIBLE);

                    }
                });

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

}

