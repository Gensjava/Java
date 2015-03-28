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

/**
 * Created by Gens on 20.03.2015.
 */
public class ProfileRegistrationFragment extends android.support.v4.app.Fragment {

    private MultiAutoCompleteTextView editAccountPassword;
    private MultiAutoCompleteTextView editAccountName;
    private MultiAutoCompleteTextView editAccountPasswordConfirmation;
    private MultiAutoCompleteTextView editAccountEmail;
    private MultiAutoCompleteTextView editAccountPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.profile_registration, container,
                false);

        editAccountName = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_login);
        editAccountPassword = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_password);
        editAccountPasswordConfirmation = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_password_сonfirmation);
        editAccountEmail = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_email);
        editAccountPhone = (MultiAutoCompleteTextView) rootView.findViewById(R.id.profile_registration_phone);

        View button = rootView.findViewById(R.id.profile_registration_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                EditText arrEdit [] = new EditText[5];
                arrEdit[0] = editAccountName;
                arrEdit[1] = editAccountPassword;
                arrEdit[2] = editAccountPasswordConfirmation;
                arrEdit[3] = editAccountEmail;
                arrEdit[4] = editAccountPhone;
                //
                if (!Error.fieldValidationRegistration(arrEdit)){
                    Profile profile = new Profile();
                    profile.createAccount(editAccountName.getText().toString(),editAccountPassword.getText().toString(), getActivity().getBaseContext());

                    if (Profile.mAuthorization){
                        getActivity().finish();
                    } else {
                        Log.e(this.getClass().getName(), "Error. Authorization");
                    }
                }

            }
        });


        return rootView;
    }
}
