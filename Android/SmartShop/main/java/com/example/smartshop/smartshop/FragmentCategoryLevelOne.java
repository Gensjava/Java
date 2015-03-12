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
public class FragmentCategoryLevelOne extends android.support.v4.app.Fragment {
    
    ArrayList<ProductCategory> mCategory = new ArrayList<ProductCategory>();

    AdapterCategory adapterCategory;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_level_one, container,
                false);
        mCategory.clear();
        fillData(rootView);
        adapterCategory = new AdapterCategory(getActivity(), R.layout.category_level_one, mCategory);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.listView_level_one);
        lvMain.setAdapter(adapterCategory);
        
        return rootView;
    }
    void fillData(View rootView) {

        mCategory.add(new ProductCategory("2984","Apple Store",R.drawable.apple));
        mCategory.add(new ProductCategory("3092","Телефоны и планшеты",R.drawable.ipad));
        mCategory.add(new ProductCategory("700","Бытовая техника",R.drawable.consumer_electronics));
        mCategory.add(new ProductCategory("140","ТВ / Аудио / Видео / Фото",R.drawable.tv));
        mCategory.add(new ProductCategory("2635","Ноутбуки и компьютерная техника",R.drawable.laptop));
        mCategory.add(new ProductCategory("5596","Портативная техника",R.drawable.portable_equipment));
        mCategory.add(new ProductCategory("3045","Автотовары",R.drawable.avto));
        mCategory.add(new ProductCategory("4032","Детский мир",R.drawable.children));
        
    }




}
