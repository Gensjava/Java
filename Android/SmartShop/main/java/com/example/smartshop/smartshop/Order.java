package com.example.smartshop.smartshop;

/**
 * Created by Gens on 01.03.2015.
 */
public class Order {

    private String mID;
    private Product mProduct;
    private Profile mProfile;
    private String mDate;
    private double mPrice;
    private double mNumber;
    private double mSum;
    private StatusOrder mStutus;

    public Order(String ID, Product product, Profile profile, String date, double price, double number, double sum, StatusOrder stutus) {
        mID = ID;
        mProduct = product;
        mProfile = profile;
        mDate = date;
        mPrice = price;
        mNumber = number;
        mSum = sum;
        mStutus = stutus;
    }

    public Order (){

    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public Profile getProfile() {
        return mProfile;
    }

    public void setProfile(Profile profile) {
        mProfile = profile;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public double getNumber() {
        return mNumber;
    }

    public void setNumber(double number) {
        mNumber = number;
    }

    public double getSum() {
        return mSum;
    }

    public void setSum(double sum) {
        mSum = sum;
    }

    public StatusOrder getStutus() {
        return mStutus;
    }

    public void setStutus(StatusOrder stutus) {
        mStutus = stutus;
    }
}