package ua.smartshop.Adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.Interface.IWorkerCallback;
import ua.smartshop.Activitys.MainActivity;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.Сonstants;

public class MainAdapter extends BaseAdapter implements IWorkerCallback {

    private Context mContext;
    private LayoutInflater lInflater;
    private ArrayList<Product[]> Products;
    private onSomeEventListener someEventListener ;
    private MainPagerAdapter adapter;
    public static final String ACTION_CATEGORY_ALL = "ACTION_CATEGORY_ALL";
    public static final String ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN = "ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN";

    public MainAdapter(Context context, ArrayList<Product[]> products) {
        mContext = context;
        Products = products;
        lInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return Products.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return Products.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        switch (position){
            case 0:
                //заполняем рекламнный блок
                convertView = lInflater.inflate(R.layout.main_peger, parent, false);

                doSomethingAsyncOperaion(new HashMap<String, String>(), mContext.getString(R.string.url_get_slider_main_page) , TypeRequest.GET);

                adapter = new MainPagerAdapter(mContext, Сonstants.categoryProduct);

                ViewPager viewPager = (ViewPager) convertView.findViewById(R.id.view_pager);
                viewPager.setAdapter(adapter);
                viewPager.setCurrentItem(0);

                CirclePageIndicator indicator = (CirclePageIndicator) convertView.findViewById(R.id.titles);
                indicator.setViewPager(viewPager);

                break;
            case 1:
                convertView = lInflater.inflate(R.layout.main_category, parent, false);

                TextView textView = (TextView) convertView.findViewById(R.id.main_category_text);
                textView.setText(mContext.getString(R.string.category_all));

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        someEventListener = (onSomeEventListener) mContext;
                        someEventListener.someEvent(ACTION_CATEGORY_ALL, null);
                    }
                });

                ImageView imageView = (ImageView)convertView.findViewById(R.id.main_category_all_arrow);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        someEventListener = (onSomeEventListener) mContext;
                        someEventListener.someEvent(ACTION_CATEGORY_ALL, null);
                    }
                });
                TextView textViewcounter = (TextView) convertView.findViewById(R.id.main_category_counter);
                textViewcounter.setVisibility(View.INVISIBLE);

                break;
            case 2:

                convertView = lInflater.inflate(R.layout.main_horizontal_scroll, parent, false);

                LinearLayout mGallery = (LinearLayout) convertView.findViewById(R.id.main_gallery_scroll);

                for (int i = 0; i < CategoryProduct.getMainCategory().size(); i++)
                {
                    View view = lInflater.inflate(R.layout.main_horizontal_scroll_item,   mGallery, false);
                    view.setTag(CategoryProduct.getMainCategory().get(i).getId());

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) {
                            someEventListener = (onSomeEventListener) mContext;
                            someEventListener.someEvent(ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN, String.valueOf(v.getTag()));
                        }
                    });

                    ImageView img = (ImageView) view.findViewById(R.id.main_scroll_image);
                    img.setImageResource(CategoryProduct.getMainCategory().get(i).getImage());

                    View txt = (View) view.findViewById(R.id.main_scrll_space);
                    mGallery.addView(view);
                }

                break;
            case 3:
                convertView = lInflater.inflate(R.layout.main_category, parent, false);

                TextView textViewShares = (TextView) convertView.findViewById(R.id.main_category_text);
                textViewShares.setText(mContext.getString(R.string.category_shares));
                textViewcounter = (TextView) convertView.findViewById(R.id.main_category_counter);
                textViewcounter.setVisibility(View.INVISIBLE);

                break;
            default:

                convertView = lInflater.inflate(R.layout.main_category_grid, parent, false);

                GridView cridView = (GridView) convertView.findViewById(R.id.main_grid_view);
                cridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
                cridView.setAdapter(new MainProductAdapter(mContext, Products.get(position)));

                break;
        }
        return convertView;
    }

    @Override
    public void onBegin() {
        MainActivity.ui_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            Сonstants.categoryProduct = new CategoryProduct[mPJSONArray.length()];
            for (int i = 0; i < mPJSONArray.length(); i++) {
                Сonstants.categoryProduct[i] =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), CategoryProduct.class);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(final Throwable t) {
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onEnd() {

    }

    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest, mContext) {
        }.execute();
    }

//    Bitmap GetBitmapDual(ImageView imageView)  {
//        Bitmap bitmap = null;
//        try {
//
//            bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(bitmap);
//
//            Bitmap bitmap1 = ((BitmapDrawable)imageView.getDrawable()).getBitmap(); //blue
//
//            Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.top_byu); //green
//            Drawable drawable1 = new BitmapDrawable(bitmap1);
//            Drawable drawable2 = new BitmapDrawable(bitmap2);
//
//            drawable1.setBounds(100, 100, 400, 400);
//            drawable2.setBounds(100, 100, 300, 300);
//            drawable1.draw(c);
//            drawable2.draw(c);
//
//        } catch (Exception e) {
//        }
//        return bitmap;
//    }

}

