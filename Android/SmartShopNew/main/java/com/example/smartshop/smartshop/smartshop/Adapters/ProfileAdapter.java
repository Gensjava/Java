package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import ua.smartshop.Models.Profile;
import ua.smartshop.R;

public class ProfileAdapter  extends BaseAdapter {

    private Context mContext;
    private LayoutInflater lInflater;
    private ArrayList<Profile> objects;

    public ProfileAdapter(Context context, ArrayList<Profile> profile) {
        mContext = context;
        objects = profile;
        lInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        // используем созданные, но не используемые view

        View view = convertView;

        final Profile item = (Profile) getItem(position);
        if(item != null){
            switch (position){

                case 0:
                    view = lInflater.inflate(R.layout.profile, parent, false);

                    TextView txtName = (TextView) view.findViewById(R.id.profile_SNP);
                    TextView txtEmail = (TextView) view.findViewById(R.id.profile_email);
                    TextView txtPhone = (TextView) view.findViewById(R.id.profile_phone);
                    TextView txtCity = (TextView) view.findViewById(R.id.profile_city);
                    TextView txtAddress = (TextView) view.findViewById(R.id.profile_address);
                    //
                    txtEmail.setText(item.getEmail());
                    txtName.setText(item.getSNP());
                    txtPhone.setText(item.getPhone());
                    txtCity.setText(item.getCity());
                    txtAddress.setText(item.getDeliveryAddress());

                    break;
                case 1:
                    view = lInflater.inflate(R.layout.main_category, parent, false);

                    TextView textView = (TextView) view.findViewById(R.id.main_category_text);
                    ImageView imageView = (ImageView) view.findViewById(R.id.main_category_all_arrow);
                    imageView.setImageResource(R.drawable.ic_action_cart);
                    textView.setText(mContext.getString(R.string.cart));
                    break;
                case 2:
                    view = lInflater.inflate(R.layout.main_category, parent, false);
                    textView = (TextView) view.findViewById(R.id.main_category_text);
                    textView.setText(mContext.getString(R.string.orders_all));
                    imageView = (ImageView) view.findViewById(R.id.main_category_all_arrow);
                    imageView.setImageResource(R.drawable.ic_action_orders);
                    break;
                case 3:
                    view = lInflater.inflate(R.layout.main_category, parent, false);

                    textView = (TextView) view.findViewById(R.id.main_category_text);
                    textView.setText(mContext.getString(R.string.browsing_history_products));
                    imageView = (ImageView) view.findViewById(R.id.main_category_all_arrow);
                    imageView.setImageResource(R.drawable.ic_action_browsing_history);
                    break;
                case 4:
                    view = lInflater.inflate(R.layout.main_category, parent, false);

                    textView = (TextView) view.findViewById(R.id.main_category_text);
                    textView.setText(mContext.getString(R.string.massege));
                    imageView = (ImageView) view.findViewById(R.id.main_category_all_arrow);
                    imageView.setImageResource(R.drawable.ic_action_massege);

                    break;
                case 5:
                    view = lInflater.inflate(R.layout.main_category, parent, false);

                    textView = (TextView) view.findViewById(R.id.main_category_text);
                    textView.setText(mContext.getString(R.string.notice));
                    imageView = (ImageView) view.findViewById(R.id.main_category_all_arrow);
                    imageView.setImageResource(R.drawable.ic_action_notification);
                    break;

                default:
                    break;
            }
        }

        return view;
    }

}
