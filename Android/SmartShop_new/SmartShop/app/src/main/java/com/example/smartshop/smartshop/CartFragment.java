package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Gens on 03.03.2015.
 */


public class CartFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    onSomeEventListener someEventListener ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_list, container,
                false);

        ListView lvCart = (ListView) rootView.findViewById(R.id.cart_listView);

        CartAdapter adapterCart = new CartAdapter(getActivity(), R.layout.cart_list, (java.util.ArrayList<Cart>) Cart.getmCart());
        lvCart.setAdapter(adapterCart);

        ((TextView) rootView.findViewById(R.id.cart_total_sum)).setText(String.valueOf(Cart.getTotalSum()));


//        View buttonMake = (View) rootView.findViewById(R.id.cart_make_order);
//        buttonMake.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("cart_make_order","cart_make_order");
//            }
//        });
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cart_make_order:
                Log.i("cart_make_order","cart_make_order");
                someEventListener = (onSomeEventListener) getActivity();
                someEventListener.someEvent("CarTotal", "totalCart");
                break;
            default:
                break;
        }
    }
    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }
}
