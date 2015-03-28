package com.example.smartshop.smartshop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gens on 16.03.2015.
 */
public class Cart {

    private Product mProduct;
    private Profile mProfile;
    private String mDate;
    private double mPrice;
    private double mNumber;
    private double mSum;
    static double mTotal;
    private static ArrayList<Cart> mCart = new ArrayList<Cart>();

    public Cart(Product product, Profile profile, String date, double price, double number, double sum) {
        mProduct = product;
        mProfile = profile;
        mDate = date;
        mPrice = price;
        mNumber = number;
        mSum = sum;
    }

    public double getTotal() {
        return mTotal;
    }

    public void setTotal(double total) {
        mTotal = total;
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

    public static double getTotalSum(){
        mTotal = 0;

        for (short i = 0;i < mCart.size();i++){
            Cart Cart = mCart.get(i);
            mTotal = mTotal + Cart.getSum();
        }
        return mTotal;
    }

    public static List<Cart> getmCart() {
        return mCart;
    }

    public static void setmCart(Cart cart) {

        //поиск есть такой товар
        boolean flag = false;

        //если есть такой уже добавляем кол-во и делаем пересчет суммы

        for (int i = 0; i < mCart.size(); i++) {
            Cart iCart = mCart.get(i);
            if (cart.getProduct().getKod().equals(iCart.getProduct().getKod())){

                iCart.setNumber(iCart.getNumber() + cart.getNumber());
                iCart.setPrice(cart.getPrice());
                iCart.setSum(iCart.getNumber() * iCart.getPrice());

                flag = true;
                break;
            }
        }
        // не нашли добавляем
        if (!flag){
            mCart.add(cart);
        }
    }
}
