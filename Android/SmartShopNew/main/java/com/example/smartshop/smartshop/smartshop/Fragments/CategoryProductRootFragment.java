package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



import java.util.ArrayList;

import ua.smartshop.Adapters.CategoryAdapter;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.R;

/**
 * Created by Gens on 07.03.2015.
 */
public class CategoryProductRootFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        CategoryAdapter mCategoryAdapter = new CategoryAdapter(getActivity(), R.layout.main_list, CategoryProduct.getMainCategory());

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mCategoryAdapter);
        
        return rootView;
    }

}
