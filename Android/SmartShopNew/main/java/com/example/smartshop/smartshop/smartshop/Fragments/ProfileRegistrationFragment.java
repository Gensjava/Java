package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import org.json.JSONArray;
import java.lang.*;
import java.util.HashMap;

import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.interfaces.AsyncWorkerInterface;
import ua.smartshop.Models.Profile;
import ua.smartshop.R;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Utils.ErrorInfo;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 20.03.2015.
 */
public class ProfileRegistrationFragment extends android.support.v4.app.Fragment  implements AsyncWorkerInterface {

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
                if (!ErrorInfo.fieldValidationRegistration(arrEdit)){

                    HashMap<String, String> params = new HashMap<String, String>();
                    //
                    params.put(Сonstants.TAG_NAME, editAccountName.getText().toString());
                    params.put(Сonstants.TAG_USER_NAME, editAccountUserName.getText().toString());
                    params.put(Сonstants.TAG_PASWWORD, editAccountPassword.getText().toString());
                    params.put(Сonstants.TAG_EMAIL, editAccountEmail.getText().toString());
                    params.put(Сonstants.TAG_PHONE, editAccountPhone.getText().toString());
                    params.put(Сonstants.TAG_ICQ_SKYPE, editAccountSkypye.getText().toString());
                    params.put(Сonstants.TAG_ADDRESS, editAccountDelivery.getText().toString());

                    doSomethingAsyncOperaion(params ,  getString(R.string.url_set_user_registration),  TypeRequest.POST);
                }
            }
        });
        return rootView;
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker(this, paramsUrl, url, typeRequest, getActivity()) {
        }.execute();
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {

        Profile profile = new Profile();
        profile.createAccount(editAccountName.getText().toString(),editAccountPassword.getText().toString(), getActivity().getBaseContext());

        Profile.mUserName = editAccountName.getText().toString();
        Profile.mAuthorization = true;

        if (Profile.mAuthorization){
            getActivity().finish();
        } else {
            Log.e(this.getClass().getName(), "Error. Registration");
            Toast.makeText(getActivity(), "Error. Registration"
                    , Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onFailure(final Throwable t) {

        Log.e(this.getClass().getName(), "Error. Registration");
        Toast.makeText(getActivity(), "Error. Registration"
                , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEnd() {

    }
}
