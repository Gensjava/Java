package ua.smartshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import ua.smartshop.Models.Profile;
import ua.smartshop.R;

/**
 * Created by Gens on 01.04.2015.
 */
public class ProfileAdapter  extends BaseAdapter {

    private static final String TEXT_ORDERS_ALL = "Все заказы >>";
    private static final String TEXT_ORDERS_ALL_STATUS = "Заказы по статусам >>";
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Profile> objects;


    ProfileAdapter(Context context, ArrayList<Profile> profile) {
        ctx = context;
        objects = profile;
        lInflater = (LayoutInflater) ctx
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
                    TextView txtemail = (TextView) view.findViewById(R.id.profile_email);

                    //
                    txtemail.setText(item.getEmail());
                    txtName.setText(item.getUserName());

                    break;
                case 1:
                    view = lInflater.inflate(R.layout.category, parent, false);


                    TextView textView = (TextView) view.findViewById(R.id.category_all_text);
                    textView.setText(TEXT_ORDERS_ALL);
                    break;
                case 2:
                    view = lInflater.inflate(R.layout.profile_status, parent, false);

                    break;
                case 3:
                    view = lInflater.inflate(R.layout.category, parent, false);

                    textView = (TextView) view.findViewById(R.id.category_all_text);
                    textView.setText(TEXT_ORDERS_ALL_STATUS);
                    break;
                default:
                    break;
            }
        }

        return view;
    }

}
