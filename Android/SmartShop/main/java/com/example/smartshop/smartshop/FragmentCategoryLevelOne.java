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
public class FragmentCategoryLevelOne extends android.support.v4.app.Fragment{
    
    ArrayList<Product> products = new ArrayList<Product>();

    AdapterCategory adapterCategory;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_category_level_one, container,
                false);
        fillData();
        adapterCategory = new AdapterCategory(getActivity(), R.layout.activity_category_level_one,products);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.listView_level_one);
        lvMain.setAdapter(adapterCategory);
        
        return rootView;
    }
    void fillData() {

        for (int i = 1; i <= 20; i++) {
            products.add(new Product("Category " + i+" >>", i * 1000,
                    R.drawable.flatscreen));
        }
    }
}
