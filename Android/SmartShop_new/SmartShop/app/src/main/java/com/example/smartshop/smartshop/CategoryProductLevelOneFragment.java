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
public class CategoryProductLevelOneFragment extends android.support.v4.app.Fragment {
    
    ArrayList<CategoryProduct> mCategory = new ArrayList<CategoryProduct>();

    CategoryAdapter mCategoryAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);
        mCategory.clear();
        fillData(rootView);
        mCategoryAdapter = new CategoryAdapter(getActivity(), R.layout.main_list, mCategory);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mCategoryAdapter);
        
        return rootView;
    }
    void fillData(View rootView) {

        mCategory.add(new CategoryProduct("2984","Apple Store",R.drawable.apple));
        mCategory.add(new CategoryProduct("3092","Телефоны и планшеты",R.drawable.ipad));
        mCategory.add(new CategoryProduct("700","Бытовая техника",R.drawable.consumer_electronics));
        mCategory.add(new CategoryProduct("140","ТВ / Аудио / Видео / Фото",R.drawable.tv));
        mCategory.add(new CategoryProduct("2635","Ноутбуки и компьютерная техника",R.drawable.laptop));
        mCategory.add(new CategoryProduct("5596","Портативная техника",R.drawable.portable_equipment));
        mCategory.add(new CategoryProduct("3045","Автотовары",R.drawable.avto));
        mCategory.add(new CategoryProduct("4032","Детский мир",R.drawable.children));
        
    }




}
