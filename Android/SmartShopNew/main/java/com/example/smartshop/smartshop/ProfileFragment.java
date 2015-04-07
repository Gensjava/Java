package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gens on 10.03.2015.
 */
public class ProfileFragment extends android.support.v4.app.Fragment{

    private ArrayList<Profile> mProfile = new ArrayList<Profile>();
    private List<HashMap> mArrayValues;
    private Profile profile;
    private ListView lvMain;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        if (mProfile.size() == 0   ){
            mArrayValues = getArrListData(Profile.getTegs(), MainPagerAdapter.getParamsUrl(Profile.mUserName), TypeRequest.POST,Сonstants.url_get_user);
            if(!(mArrayValues == null)){
                GetProfileDetailsTask();
            }
        }
        //
        ProfileAdapter itemAdapter = new ProfileAdapter(getActivity(), mProfile);
        // настраиваем список
        lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();

        return rootView;
    }
    // получения информации о пользователе
    void GetProfileDetailsTask () {

        HashMap<String, String> mValues;

        for (int i = 0; i < mArrayValues.size(); i++) {

            mValues = mArrayValues.get(i);
            profile = new Profile(
                    mValues.get(Сonstants.TAG_PID),
                    mValues.get(Сonstants.TAG_NAME),
                    mValues.get(Сonstants.TAG_EMAIL));

            mProfile.add(profile);

        }
        mProfile.add(profile);
        mProfile.add(profile);
        mProfile.add(profile);
    }

    private List<HashMap> getArrListData(String tags[], HashMap<String, String> params, TypeRequest typeRequest, String url ) {

        List<HashMap> mArrayValues  = null ;
        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, url , tags ,getActivity(), typeRequest);
        utilAsyncTask.execute();
        try {
            utilAsyncTask.get();
            mArrayValues  = utilAsyncTask.getArrayValues();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mArrayValues;
    }

}
