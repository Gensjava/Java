package com.example.smartshop.smartshop;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Gens on 01.03.2015.
 */
public class Profile {
    private int mFoto;
    private String mID;
    private String mSNP;
    private String mGender;
    private String mEmail;
    private String mDeliveryAddress;
    public static boolean mAuthorization;
    private static AccountManager mAccountManager;

    public Profile(int foto, String ID, String SNP, String gender, String email, String deliveryAddress) {
        mFoto = foto;
        mID = ID;
        mSNP = SNP;
        mGender = gender;
        mEmail = email;
        mDeliveryAddress = deliveryAddress;
    }

    public Profile(){

    }

    public int getFoto() {
        return mFoto;
    }

    public void setFoto(int foto) {
        mFoto = foto;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getSNP() {
        return mSNP;
    }

    public void setSNP(String SNP) {
        mSNP = SNP;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getDeliveryAddress() {
        return mDeliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        mDeliveryAddress = deliveryAddress;
    }

    public void createAccount(final String login, final String password, Context context) {

        if (login.toString().equals("") || password.toString().equals(""))
        {
            Log.i("Не заполнены все поля!","Не заполнены все поля!");
        }
        else
        {
            final Account account = new Account(login, BuildConfig.APPLICATION_ID);

            if (!(getAllAccount(context))){
                mAuthorization = mAccountManager.addAccountExplicitly(account, password, null);
            } else {
                mAccountManager.setPassword(account, password);
            }
        }

    }

    public static boolean  getAllAccount(Context context){

        mAccountManager = AccountManager.get(context);

        final Account[] accounts = mAccountManager.getAccounts();
        for (Account account : accounts) {
            if (account.type.equalsIgnoreCase(BuildConfig.APPLICATION_ID)){
                mAuthorization = true;
            } else {
                mAuthorization = false;
            }
        }
        return mAuthorization;
    }

    public static void startAuthorization(Context context){

        if (!(getAllAccount(context))){
            Intent intent = new Intent(context,ProfileActivity.class);
            context.startActivity(intent);
        }
    }
}
