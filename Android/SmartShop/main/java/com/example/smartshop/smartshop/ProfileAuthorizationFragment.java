package com.example.smartshop.smartshop;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gens on 20.03.2015.
 */
public class ProfileAuthorizationFragment extends android.support.v4.app.Fragment {

    private MultiAutoCompleteTextView editAccountName;
    private MultiAutoCompleteTextView editAccountPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.profile_authorization, container,
                false);
        editAccountName = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_authorization_login);
        editAccountPassword = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_authorization_password);

        View button = rootView.findViewById(R.id.profile_authorization_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                EditText arrEdit [] = new EditText[2];
                arrEdit[0] = editAccountName;
                arrEdit[1] = editAccountPassword;
                //
                if (!Error.fieldValidationRegistration(arrEdit)){
                    //
                    if(onAuthorizationUser()){

                        Profile profile = new Profile();
                        profile.createAccount(
                                editAccountName.getText().toString(),
                                editAccountPassword.getText().toString(),
                                getActivity().getBaseContext());

                        if (Profile.mAuthorization){
                            getActivity().finish();
                        } else {
                            Log.e(this.getClass().getName(), "Error. Authorization");
                        }
                    }else {
                        Log.e(this.getClass().getName(), "Error. Authorization");
                    }

                }
            }
        });

        CheckBox checkBoxAuthorization = (CheckBox) rootView.findViewById(R.id.profile_authorization_checkBox);
        checkBoxAuthorization.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {

                if(isChecked)
                    editAccountPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else {
                    editAccountPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        return rootView;
    }
    private boolean onAuthorizationUser(){
        boolean mAuthorizationUser = false;

        String tags[] = new String[2];
        tags[0] = Сonstants.TAG_USER_NAME;
        tags[1] = Сonstants.TAG_PASWWORD;
        HashMap<String, String> mValues;
        //
        HashMap<String, String> params = new HashMap<String, String>();
        //
        String AccountName = editAccountName.getText().toString();
        String AccountPassword = editAccountPassword.getText().toString();

        params.put(Сonstants.TAG_USER_NAME, AccountName);
        params.put(Сonstants.TAG_PASWWORD, AccountPassword);

        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_get_user_authorization , tags ,getActivity(), TypeRequest.POST);
        utilAsyncTask.execute();

        try {
            utilAsyncTask.get();

            List<HashMap> mArrayValues  = utilAsyncTask.getArrayValues();

            if(!(mArrayValues.size() == 0)){

                mValues = mArrayValues.get(0);
               //делаем проверу логина и пароля
                if (mValues.get(Сonstants.TAG_USER_NAME).equals(AccountName) && mValues.get(Сonstants.TAG_PASWWORD).equals(AccountPassword)){
                    mAuthorizationUser = true;
                } else {
                    mAuthorizationUser = false;
                }

            } else {
                //Открываем фрагмент с ошибкой
                mAuthorizationUser = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mAuthorizationUser;

    }
}
    