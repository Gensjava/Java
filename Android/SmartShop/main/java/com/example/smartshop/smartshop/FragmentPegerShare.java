package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gens on 07.03.2015.
 */
public class FragmentPegerShare extends android.support.v4.app.Fragment{
    
    ArrayList<Product> products = new ArrayList<Product>();

    AdapterPagerShare adapterPagerShare;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_peger, container,
                false);
        fillData();
        adapterPagerShare = new AdapterPagerShare(getActivity(), products);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.list_peger);
        lvMain.setAdapter(adapterPagerShare);
        
        return rootView;
    }
    void fillData() {

        for (int i = 1; i <= 20; i++) {
            products.add(new Product("Category " + i+" >>", i * 1000,
                    R.drawable.flatscreen));
        }
    }
}
