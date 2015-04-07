package com.example.smartshop.smartshop;

/**
 * Created by Gens on 27.02.2015.
 */
public class ProductDual {
    private Product mProductOne;
    private Product mProductTwo;

    public ProductDual(Product productOne, Product productTwo) {
        mProductOne = productOne;
        mProductTwo = productTwo;
    }

    public Product getProductOne() {
        return mProductOne;
    }

    public void setProductOne(Product productOne) {
        mProductOne = productOne;
    }

    public Product getProductTwo() {
        return mProductTwo;
    }

    public void setProductTwo(Product productTwo) {
        mProductTwo = productTwo;
    }
}
