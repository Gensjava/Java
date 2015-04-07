package com.example.smartshop.smartshop;

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
}
