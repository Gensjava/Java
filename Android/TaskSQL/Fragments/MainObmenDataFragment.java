package ua.com.it_st.deliveryman.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.it_st.deliveryman.R;



public class MainObmenDataFragment extends  android.support.v4.app.Fragment   {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.obmen_data, container,
                false);



        return rootView;
    }

}
