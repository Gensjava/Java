package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import java.util.ArrayList;
import ua.smartshop.Models.Product;
import ua.smartshop.R;

public class ProductAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater lInflater;
    private ArrayList<Product[]> mPoducts;
    private int mCount;

    public ProductAdapter(Context context, ArrayList<Product[]> products, int count) {
        mContext = context;
        mPoducts = products;
        mCount = count;
        lInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return mPoducts.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return mPoducts.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // используем созданные, но не используемые view
        final ViewHolder viewHolder;
        final Product[] item = (Product[]) getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = lInflater.inflate(R.layout.main_category_grid, parent, false);

            viewHolder.cridView = (GridView) convertView.findViewById(R.id.main_grid_view);
            viewHolder.cridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            viewHolder.cridView.setNumColumns(1);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (item!= null){
            viewHolder.cridView.setAdapter(new MainProductAdapter(mContext, item, mCount));
        }

        return convertView;
    }

    private static class ViewHolder {
        public GridView cridView;
    }
}

