package com.example.smartshop.smartshop;

import java.io.Serializable;

/**
 * Created by Gens on 03.02.2015.
 */
public class Product implements Serializable {
    //@SerializedName("description")
    
    private String mName;
    private String mWayImage;
  
    private String mDescription;
    private String mId;
    private String mKod;
    private double mPrice;
    private int mImage;

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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getWayImage() {
        return mWayImage;
    }

    public void setWayImage(String wayImage) {
        mWayImage = wayImage;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKod() {
        return mKod;
    }

    public void setKod(String kod) {
        mKod = kod;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }
}