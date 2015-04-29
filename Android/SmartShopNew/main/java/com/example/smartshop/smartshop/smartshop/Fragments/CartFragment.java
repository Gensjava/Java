package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import ua.smartshop.Adapters.CartAdapter;
import ua.smartshop.Models.Cart;
import ua.smartshop.R;


/**
 * Created by Gens on 03.03.2015.
 */


public class CartFragment extends android.support.v4.app.Fragment implements
        View.OnClickListener
       {

    private onSomeEventListener someEventListener ;
    public static final String TEG_GART_TOTAL_FRAGMENT = "TEG_GART_TOTAL_FRAGMENT";
    public static final String ACTION_GART_FRAGMENT = "ACTION_GART_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_list, container,
                false);

        ListView lvCart = (ListView) rootView.findViewById(R.id.cart_listView);

        CartAdapter adapterCart = new CartAdapter(getActivity(), R.layout.cart_list, (java.util.ArrayList<Cart>) Cart.getmCart());
        lvCart.setAdapter(adapterCart);

        ((TextView) rootView.findViewById(R.id.cart_total_sum)).setText(String.valueOf(Cart.getTotalSum()));


        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cart_make_order:

                someEventListener = (onSomeEventListener) getActivity();
                someEventListener.someEvent(TEG_GART_TOTAL_FRAGMENT, null);
                break;
            default:
                break;
        }
    }
    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }
}
