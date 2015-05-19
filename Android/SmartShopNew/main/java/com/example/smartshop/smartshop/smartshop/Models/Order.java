package ua.smartshop.Models;

import ua.smartshop.Enums.StatusOrder;

public class Order {

    private String mID;
    private Product mProduct;
    private Profile mProfile;
    private String mDate;
    private double mPrice;
    private double mNumber;
    private double mSum;
    private StatusOrder mStutus;

    public Order(final String ID, final Product product, final Profile profile, final String date, final double price, final double number, final double sum, final StatusOrder stutus) {
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

    public void setID(final String ID) {
        mID = ID;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(final Product product) {
        mProduct = product;
    }

    public Profile getProfile() {
        return mProfile;
    }

    public void setProfile(final Profile profile) {
        mProfile = profile;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(final String date) {
        mDate = date;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(final double price) {
        mPrice = price;
    }

    public double getNumber() {
        return mNumber;
    }

    public void setNumber(final double number) {
        mNumber = number;
    }

    public double getSum() {
        return mSum;
    }

    public void setSum(final double sum) {
        mSum = sum;
    }

    public StatusOrder getStutus() {
        return mStutus;
    }

    public void setStutus(final StatusOrder stutus) {
        mStutus = stutus;
    }
}