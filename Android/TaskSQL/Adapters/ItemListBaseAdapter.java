package ua.com.it_st.deliveryman.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ua.com.it_st.deliveryman.R;

/**
 * Created by Gens on 27.04.2015.
 */
public class ItemListBaseAdapter extends BaseAdapter   {

    public static final String ACTION_ITEM =  "ACTION_ITEM" ;
    private  String[] itemDetailsrrayList;
    private Context ctx;
    private LayoutInflater l_Inflater;
    private onSomeEventListener someEventListener ;

    private Integer[] imgid = {
            R.drawable.ic_action,
            R.drawable.ic_action,
            R.drawable.ic_action,
            R.drawable.ic_action,
            R.drawable.ic_action,
            R.drawable.ic_action,
            R.drawable.ic_action
    };

    @Override
    public int getCount() {
        return itemDetailsrrayList.length;
    }
    public ItemListBaseAdapter(Context context) {

        ctx = context;
        l_Inflater = LayoutInflater.from(context);
        itemDetailsrrayList = ctx.getResources().getStringArray(R.array.man_title_list);
    }

    @Override
    public Object getItem(final int position) {
        return itemDetailsrrayList[position];
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.main_list_item, null);

            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.main_item_title);
           //holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.main_item_discription);
            holder.txt_itemNumber = (TextView) convertView.findViewById(R.id.main_item_number);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.main_item_icon);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_itemName.setText(itemDetailsrrayList[position].toString());
        //holder.txt_itemDescription.setText(itemDetailsrrayList[position].toString());
        holder.txt_itemNumber.setText("10");
        holder.itemImage.setImageResource(imgid[position]);

        TextView txt_itemName = (TextView) convertView.findViewById(R.id.main_item_title);

        txt_itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener = (onSomeEventListener) ctx;
                someEventListener.someEvent(ACTION_ITEM, String.valueOf(position));
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txt_itemName;
        TextView txt_itemDescription;
        TextView txt_itemNumber;
        ImageView itemImage;
    }
    public interface onSomeEventListener {
        public void someEvent(String key, String id);
    }
}
