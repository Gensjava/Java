package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ua.smartshop.Adapters.CategoryAdapter;
import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.R;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 07.03.2015.
 */
public class CategoryProductFragment extends android.support.v4.app.Fragment {

    private ArrayList<CategoryProduct> mMroducts = new ArrayList<CategoryProduct>();
    private List<HashMap> mArrayValues;
    private CategoryAdapter mCategoryAdapter;
    String id ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        if (mMroducts.size() == 0){
            Bundle bundle = getArguments();
            if (bundle != null) {
                mArrayValues = (List<HashMap>) bundle.getSerializable(CategoryAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER);
                if(!(mArrayValues == null)){
                    GetProductDetailsTask();
                }
            }
        }

        mCategoryAdapter = new CategoryAdapter(getActivity(), R.layout.main_list,  mMroducts);

        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mCategoryAdapter);
        
        mCategoryAdapter.notifyDataSetChanged();

        return rootView;
    }

    // получения информации о товаре
    void GetProductDetailsTask () {

        HashMap<String, String> mValues;

        for (int i = 0; i < mArrayValues.size(); i++) {

            mValues = mArrayValues.get(i);

            CategoryProduct categoryProduct = new CategoryProduct(
                    mValues.get(Сonstants.TAG_PID)
                    ,mValues.get(Сonstants.TAG_NAME));
            mMroducts.add(categoryProduct);
        }
    }
}
