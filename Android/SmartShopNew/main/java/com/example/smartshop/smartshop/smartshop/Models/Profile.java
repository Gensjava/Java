package ua.smartshop.Models;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ua.smartshop.BuildConfig;
import ua.smartshop.ProfileActivity;
import ua.smartshop.Utils.Сonstants;

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
    private String mPhone;
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

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(final String phone) {
        mPhone = phone;
    }

    public static String getmUserName() {
        return mUserName;
    }

    public static void setmUserName(final String mUserName) {
        Profile.mUserName = mUserName;
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
}
