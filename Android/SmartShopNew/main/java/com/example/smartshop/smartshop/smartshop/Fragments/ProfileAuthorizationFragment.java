package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
public class ProfileAuthorizationFragment extends android.support.v4.app.Fragment implements AsyncWorkerInterface {

    private MultiAutoCompleteTextView editAccountName;
    private MultiAutoCompleteTextView editAccountPassword;
    private String AccountName ;
    private String AccountPassword ;

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
                if (!ErrorInfo.fieldValidationRegistration(arrEdit)){

                    //
                    AccountName = editAccountName.getText().toString();
                    AccountPassword = editAccountPassword.getText().toString();

                    doSomethingAsyncOperaion(Profile.getParamsuserNameUrl(AccountName) , getString(R.string.url_get_user_authorization),  TypeRequest.POST);

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
    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker(this, paramsUrl, url, typeRequest, getActivity()) {
        }.execute();
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {

        try {
            JSONObject jsonObject =  mPJSONArray.getJSONObject(0);
            if (jsonObject.getString(Сonstants.TAG_USER_NAME).equals(AccountName)){
           // if (jsonObject.getString(Сonstants.TAG_USER_NAME).equals(AccountName) && jsonObject.getString(Сonstants.TAG_PASWWORD).equals(AccountPassword)){

                Profile profile = new Profile();
                profile.createAccount(editAccountName.getText().toString(),editAccountPassword.getText().toString(), getActivity().getBaseContext());

                Profile.mUserName = editAccountName.getText().toString();
                Profile.mAuthorization = true;
                getActivity().finish();
            } else {
                Log.e(this.getClass().getName(), "Error. Authorization");
                Toast.makeText(getActivity(), "Error. Authorization"
                        , Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();

            Log.e(this.getClass().getName(), "Error. Authorization");
            Toast.makeText(getActivity(), "Error. Authorization"
                    , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(final Throwable t) {

        Log.e(this.getClass().getName(), "Error. Authorization");
        Toast.makeText(getActivity(), "Error. Authorization"
                , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEnd() {

    }
}
    