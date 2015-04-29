package ua.com.it_st.deliveryman.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ua.com.it_st.deliveryman.Adapters.ItemListBaseAdapter;
import ua.com.it_st.deliveryman.R;

/**
 * Created by Gens on 21.04.2015.
 */
public class MainFragment extends  android.support.v4.app.Fragment  {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container,
                false);

        ItemListBaseAdapter itemListBaseAdapter = new ItemListBaseAdapter(getActivity());
        ListView listView = (ListView) rootView.findViewById(R.id.main_list);
        listView.setAdapter(itemListBaseAdapter);

        return rootView;
    }
}
