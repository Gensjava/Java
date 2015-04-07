package com.example.smartshop.smartshop;

import android.provider.SyncStateContract;

import java.io.Serializable;
import java.util.HashMap;


public class Product implements Serializable {

    private String mName;
    private String mWayImage;

    private String mDescription;
    private String mId;
    private String mKod;
    private double mPrice;
    private int mImage;
    //ключ ИД товара value

    public Product(String description, double price, int image) {
        mDescription = description;
        mPrice = price;
        mImage = image;
    }

    public Product(double price, int image, String id, String wayImage) {
        mPrice = price;
        mImage = image;
        mId = id;
        mWayImage = wayImage;
    }

    public Product(String name, String description, String id, String kod, double price, String wayImage) {
        mName = name;
        mDescription = description;
        mId = id;
        mKod = kod;
        mPrice = price;
        mWayImage = wayImage;
    }

    public Product(final String wayImage, final String id, final double price) {
        mWayImage = wayImage;
        mId = id;
        mPrice = price;
    }

    public Product(){

    }

    public Product(final String id, final String name, final String wayImage) {
        mId = id;
        mName = name;
        mWayImage = wayImage;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getWayImage() {
        return mWayImage;
    }

    public void setWayImage(final String wayImage) {
        mWayImage = wayImage;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(final String description) {
        mDescription = description;
    }

    public String getId() {
        return mId;
    }

    public void setId(final String id) {
        mId = id;
    }

    public String getKod() {
        return mKod;
    }

    public void setKod(final String kod) {
        mKod = kod;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(final double price) {
        mPrice = price;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(final int image) {
        mImage = image;
    }

    public static String [] getTegs(){

        String tags[] = new String[6];
        tags[0] = Сonstants.TAG_NAME;
        tags[1] = Сonstants.TAG_PRICE;
        tags[2] = Сonstants.TAG_KOD;
        tags[3] = Сonstants.TAG_DISCRIPTION;
        tags[4] = Сonstants.TAG_WAY_IMAGE;
        tags[5] = Сonstants.TAG_PID;

        return  tags;
    }

    public static HashMap<String, String>  getParamsUrl(String idItem){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Сonstants.VALUE_KEY_ITEM_ID, idItem);
        return params;
    }
}