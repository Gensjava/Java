package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import ua.smartshop.Adapters.CategoryAdapter;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.R;

public class CategoryProductRootFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        CategoryAdapter mCategoryAdapter = new CategoryAdapter(getActivity(), R.layout.main_list, CategoryProduct.getMainCategory_b());

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mCategoryAdapter);
        
        return rootView;
    }

}
