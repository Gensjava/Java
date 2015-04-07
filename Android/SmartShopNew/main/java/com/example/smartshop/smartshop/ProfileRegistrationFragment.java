package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gens on 20.03.2015.
 */
public class ProfileRegistrationFragment extends android.support.v4.app.Fragment {

    private MultiAutoCompleteTextView editAccountName;
    private MultiAutoCompleteTextView editAccountUserName;
    private MultiAutoCompleteTextView editAccountPassword;
    private MultiAutoCompleteTextView editAccountPasswordConfirmation;
    private MultiAutoCompleteTextView editAccountEmail;
    private MultiAutoCompleteTextView editAccountPhone;
    private MultiAutoCompleteTextView editAccountSkypye;
    private MultiAutoCompleteTextView editAccountDelivery;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.profile_registration, container,
                false);

        editAccountName = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_name);
        editAccountUserName = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_user_name);
        editAccountPassword = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_password);
        editAccountPasswordConfirmation = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_password_сonfirmation);
        editAccountEmail = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_email);
        editAccountPhone = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_phone);
        editAccountSkypye = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_skypye);
        editAccountDelivery = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_delivery);

        View button = rootView.findViewById(R.id.profile_registration_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                EditText arrEdit [] = new EditText[6];
                arrEdit[0] = editAccountName;
                arrEdit[1] = editAccountUserName;
                arrEdit[2] = editAccountPassword;
                arrEdit[3] = editAccountPasswordConfirmation;
                arrEdit[4] = editAccountEmail;
                arrEdit[5] = editAccountPhone;
                //
                if (!Error.fieldValidationRegistration(arrEdit)){

                    if (onRegistrationUser()){

                        Profile profile = new Profile();
                        profile.createAccount(editAccountName.getText().toString(),editAccountPassword.getText().toString(), getActivity().getBaseContext());

                        if (Profile.mAuthorization){
                            getActivity().finish();
                        } else {
                            Log.e(this.getClass().getName(), "Error. Registration");
                        }
                    }else {
                        Log.e(this.getClass().getName(), "Error. Registration");
                    }
                }
            }
        });
        return rootView;
    }

    private boolean onRegistrationUser(){
        boolean mRegistrationUser = false;

        String tags[] = new String[1];
        tags[0] = Сonstants.TAG_MASSAGE;
        //
        HashMap<String, String> params = new HashMap<String, String>();
        //
        params.put(Сonstants.TAG_NAME, editAccountName.getText().toString());
        params.put(Сonstants.TAG_USER_NAME, editAccountUserName.getText().toString());
        params.put(Сonstants.TAG_PASWWORD, editAccountPassword.getText().toString());
        params.put(Сonstants.TAG_EMAIL, editAccountEmail.getText().toString());
        params.put(Сonstants.TAG_PHONE, editAccountPhone.getText().toString());
        params.put(Сonstants.TAG_ICQ_SKYPE, editAccountSkypye.getText().toString());
        params.put(Сonstants.TAG_ADDRESS, editAccountDelivery.getText().toString());

        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_set_user_registration , tags ,getActivity(), TypeRequest.POST);
        utilAsyncTask.execute();
        try {
            utilAsyncTask.get();

            List<HashMap> mArrayValues  = utilAsyncTask.getArrayValues();

            if(!(mArrayValues.size() == 0)){
                mRegistrationUser = true;
            } else {
                //Открываем фрагмент с ошибкой
                mRegistrationUser = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mRegistrationUser;

    }

}
