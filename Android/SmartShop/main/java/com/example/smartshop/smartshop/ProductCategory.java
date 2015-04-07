package com.example.smartshop.smartshop;

/**
 * Created by Gens on 11.03.2015.
 */
public class ProductCategory {
    private String mId;
    private String mName;
    private int mImageView;


    public ProductCategory(String id, String name, int imageView) {
        mId = id;
        mName = name;
        mImageView = imageView;
    }

    public ProductCategory(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImageView() {
        return mImageView;
    }

    public void setImageView(int imageView) {
        mImageView = imageView;
    }
}
