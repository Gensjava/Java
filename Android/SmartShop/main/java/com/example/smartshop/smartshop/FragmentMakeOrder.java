package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gens on 10.03.2015.
 */
public class FragmentMakeOrder  extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       
        View rootView = inflater.inflate(R.layout.order_make_layout, container,
                false);
        
        return rootView;
    }
}
