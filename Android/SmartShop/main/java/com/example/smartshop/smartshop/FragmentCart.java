package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Gens on 03.03.2015.
 */


public class FragmentCart extends android.support.v4.app.Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_layout, container,
                false);

        ListView lvCart = (ListView) rootView.findViewById(R.id.cart_listView);

        AdapterCart adapterCart = new AdapterCart(getActivity(), R.layout.cart_layout,Сonstants.mCart);
        lvCart.setAdapter(adapterCart);
        
        return rootView;
    }

}
