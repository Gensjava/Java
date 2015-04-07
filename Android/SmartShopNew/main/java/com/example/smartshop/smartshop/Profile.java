package com.example.smartshop.smartshop;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Gens on 01.03.2015.
 */
public class Profile {

    private int mFoto;
    private String mID;
    private String mSNP;
    private String mGender;
    private String mEmail;
    private Order mOrders;
    private String mDeliveryAddress;
    //
    public static boolean mAuthorization;
    public static String mUserName;
    private static AccountManager mAccountManager;

    public Profile(final String ID, final String SNP, final String email) {
        mID = ID;
        mSNP = SNP;
        mEmail = email;
    }

    public Profile(){

    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(final String userName) {
        mUserName = userName;
    }

    public Order getOrders() {
        return mOrders;
    }

    public void setOrders(final Order orders) {
        mOrders = orders;
    }

    public int getFoto() {
        return mFoto;
    }

    public void setFoto(final int foto) {
        mFoto = foto;
    }

    public String getID() {
        return mID;
    }

    public void setID(final String ID) {
        mID = ID;
    }

    public String getSNP() {
        return mSNP;
    }

    public void setSNP(final String SNP) {
        mSNP = SNP;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(final String gender) {
        mGender = gender;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(final String email) {
        mEmail = email;
    }

    public String getDeliveryAddress() {
        return mDeliveryAddress;
    }

    public void setDeliveryAddress(final String deliveryAddress) {
        mDeliveryAddress = deliveryAddress;
    }

    public static AccountManager getmAccountManager() {
        return mAccountManager;
    }

    public static void setmAccountManager(final AccountManager mAccountManager) {
        Profile.mAccountManager = mAccountManager;
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
                mUserName = account.name;
            } else {
                mAuthorization = false;
                mUserName = account.name;
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

    public static String [] getTegs(){

        String tags[]  = new String[8];
        tags[0] = Сonstants.TAG_NAME;
        tags[1] = Сonstants.TAG_USER_NAME;
        tags[2] = Сonstants.TAG_EMAIL;
        tags[3] = Сonstants.TAG_PHONE;
        tags[4] = Сonstants.TAG_ICQ_SKYPE;
        tags[5] = Сonstants.TAG_ALL_ORDERS;
        tags[6] = Сonstants.TAG_PASWWORD;
        tags[7] = Сonstants.TAG_PID;

        return  tags;
    }
    public static HashMap<String, String> getParamsUrl(String idItem){

        HashMap<String, String> params = new HashMap<String, String>();

        params.put("username","gens");
        params.put("username","password");
        return params;
    }

}
