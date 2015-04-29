package ua.smartshop.Models;

import java.io.Serializable;
import java.util.HashMap;

import ua.smartshop.Utils.Сonstants;


public class Product implements Serializable {

    private String mName;
    private String mWayImage;

    private String mDescription;
    private String mId;
    private String mKod;
    private double mPrice;

    public Product(String description, double price) {
        mDescription = description;
        mPrice = price;

    }

    public Product(final double price,final String id, final String wayImage) {
        mPrice = price;
        mId = id;
        mWayImage = wayImage;
    }

    public Product(final String name, final String description, final String id, final String kod, final double price, final String wayImage) {
        mName = name;
        mDescription = description;
        mId = id;
        mKod = kod;
        mPrice = price;
        mWayImage = wayImage;
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

    public static HashMap<String, String>  getParamsUrl(final String idItem){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Сonstants.VALUE_KEY_ITEM_ID, idItem);
        return params;
    }
    public static HashMap<String, String>  getParamsUrlNumber(final int itemNumber){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Сonstants.VALUE_KEY_ITEM_NUMBER, String.valueOf(itemNumber));
        return params;
    }

    public static HashMap<String, String>  getParamsUrlNumberItem(final int itemNumber, final String idItem){
        HashMap<String, String> params= new HashMap<String, String>();
        params.put(Сonstants.VALUE_KEY_ITEM_ID, idItem);
        params.put(Сonstants.VALUE_KEY_ITEM_NUMBER, String.valueOf(itemNumber));
        return params;
    }
}