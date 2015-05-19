package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Interface.IWorkerCallback;
import ua.smartshop.Activitys.MainActivity;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.R;

/**
 * Created by Gens on 10.02.2015.
 */
public class CategoryAdapter extends ArrayAdapter<CategoryProduct> implements IWorkerCallback {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private onSomeEventListener someEventListener ;
    public static final String ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER = "ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER";
    public static final String ACTION_FROM_CATEGORY_PRODUCT = "ACTION_FROM_CATEGORY_PRODUCT";
    private String mItem_id;
    
    public CategoryAdapter(Context context, final int resource, ArrayList<CategoryProduct> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }
    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       
        final ViewHolder viewHolder;
        final CategoryProduct item = getItem(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.category, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.categoryNameTextView = (TextView) convertView.findViewById(R.id.category_all_text);
            viewHolder.categoryImageView = (ImageView) convertView.findViewById(R.id.category__all_imageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
         }
       
        if(item != null){
            viewHolder.categoryNameTextView.setText(item.getName());
            viewHolder.categoryImageView.setImageResource(item.getImage());

            viewHolder.categoryNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItem_id = item.getId();
                    doSomethingAsyncOperaion( CategoryProduct.getParamsUrl(item.getId()),  mContext.getString(R.string.url_get_category_products), TypeRequest.GET);
                }
            });
        }
        return convertView;
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest, mContext) {
        }.execute();
    }

    @Override
    public void onBegin() {
        MainActivity.ui_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        someEventListener = (onSomeEventListener) mContext;
        someEventListener.someEvent(ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER, mItem_id);
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(final Throwable t) {
        someEventListener = (onSomeEventListener) mContext;
        someEventListener.someEvent(ACTION_FROM_CATEGORY_PRODUCT, mItem_id);
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onEnd() {

    }

    public interface onSomeEventListener {

        public void someEvent(String id,String idItem );
    }

    private static class ViewHolder {
        public TextView categoryNameTextView;
        public ImageView categoryImageView;
    }
}
