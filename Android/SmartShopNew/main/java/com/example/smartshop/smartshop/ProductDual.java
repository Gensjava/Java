package com.example.smartshop.smartshop;

/**
 * Created by Gens on 27.02.2015.
 */
public class ProductDual {
    private Product mProductOne;
    private Product mProductTwo;

    public ProductDual(final Product productOne, final Product productTwo) {
        mProductOne = productOne;
        mProductTwo = productTwo;
    }

    public Product getProductOne() {
        return mProductOne;
    }

    public void setProductOne(final Product productOne) {
        mProductOne = productOne;
    }

    public Product getProductTwo() {
        return mProductTwo;
    }

    public void setProductTwo(final Product productTwo) {
        mProductTwo = productTwo;
    }
}
