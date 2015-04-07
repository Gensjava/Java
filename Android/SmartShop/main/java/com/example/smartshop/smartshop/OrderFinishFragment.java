package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gens on 27.03.2015.
 */
public class OrderFinishFragment  extends android.support.v4.app.Fragment  {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.order_finish, container,
                false);


        View button = rootView.findViewById(R.id.pay_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onOrderSave();
            }
        });


        return rootView;
    }
    private boolean onOrderSave(){
        boolean mOrder = false;

//        String tags[] = new String[1];
//        tags[0] = Сonstants.TAG_MASSAGE;
//        //
//        HashMap<String, String> params = new HashMap<String, String>();
//        //
//        params.put("name",editAccountName.getText().toString());
//        params.put("username",editAccountUserName.getText().toString());
//        params.put("password",editAccountPassword.getText().toString());
//        params.put("email",editAccountEmail.getText().toString());
//        params.put("phone",editAccountPhone.getText().toString());
//        params.put("isq",editAccountSkypye.getText().toString());
//        params.put("adress",editAccountDelivery.getText().toString());
//
//        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_set_user_registration , tags ,getActivity(), TypeRequest.POST);
//        utilAsyncTask.execute();
//        try {
//            utilAsyncTask.get();
//
//            List<HashMap> mArrayValues  = utilAsyncTask.getArrayValues();
//
//            if(!(mArrayValues.size() == 0)){
//                mRegistrationUser = true;
//            } else {
//                //Открываем фрагмент с ошибкой
//                mRegistrationUser = false;
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        return mOrder;

    }
}
