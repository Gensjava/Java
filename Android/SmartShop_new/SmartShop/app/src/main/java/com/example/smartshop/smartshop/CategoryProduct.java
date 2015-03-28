package com.example.smartshop.smartshop;

import java.io.Serializable;

/**
 * Created by Gens on 11.03.2015.
 */
public class CategoryProduct implements Serializable {

    private String mId;
    private String mName;
    private String mUrl;
    private int mImageView;

    public CategoryProduct(final String id, final String name) {
        mId = id;
        mName = name;
    }

    public CategoryProduct(final String id, final String name, final String url) {
        mId = id;
        mName = name;
        mUrl = url;
    }

    public CategoryProduct(final String id, final String name, final int imageView) {
        mId = id;
        mName = name;
        mImageView = imageView;
    }

    public String getId() {
        return mId;
    }

    public void setId(final String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(final String url) {
        mUrl = url;
    }

    public int getImageView() {
        return mImageView;
    }

    public void setImageView(final int imageView) {
        mImageView = imageView;
    }
}
