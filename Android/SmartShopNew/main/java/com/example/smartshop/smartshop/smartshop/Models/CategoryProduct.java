package ua.smartshop.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import ua.smartshop.R;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 11.03.2015.
 */
public class CategoryProduct implements Serializable {

    private String mId;
    private String mName;
    private String mUrl;
    private String mWayImage;
    private int mImage;
    private String mFiltr;

    public CategoryProduct(final String id, final String name) {
        mId = id;
        mName = name;
    }

    public CategoryProduct(final String id, final String name, final String wayImage) {
        mId = id;
        mName = name;
        mWayImage = wayImage;
    }

    public CategoryProduct(final String id, final String name, final int image) {
        mId = id;
        mName = name;
        mImage = image;
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

    public String getWayImage() {
        return mWayImage;
    }

    public void setWayImage(final String wayImage) {
        mWayImage = wayImage;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(final int image) {
        mImage = image;
    }

    public static HashMap<String, String> getParamsUrl(String idItem){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Сonstants.VALUE_KEY_ITEM_ID,idItem);
        return params;
    }

    public static ArrayList<CategoryProduct> getMainCategory(boolean r) {

        ArrayList<CategoryProduct> mCategory = new ArrayList<>();

        mCategory.add(new CategoryProduct("2984","Apple Store", r == true ? R.drawable.apple_c : R.drawable.apple_d));
        mCategory.add(new CategoryProduct("3092","Телефоны и планшеты", r == true ? R.drawable.ipad_c : R.drawable.ipad_d ));
        mCategory.add(new CategoryProduct("700","Бытовая техника", r == true ? R.drawable.consumer_electronics_c: R.drawable.consumer_electronics_d));
        mCategory.add(new CategoryProduct("140","ТВ / Аудио / Видео / Фото", r == true ? R.drawable.tv_c:R.drawable.tv_d));
        mCategory.add(new CategoryProduct("2635","Ноутбуки и компьютерная техника", r == true ? R.drawable.laptop_c:R.drawable.laptop_d));
        mCategory.add(new CategoryProduct("5596","Портативная техника", r == true ? R.drawable.portable_equipment_c:R.drawable.portable_equipment_d));
        mCategory.add(new CategoryProduct("3045","Автотовары", r == true ? R.drawable.avto_c:R.drawable.avto_d));
        mCategory.add(new CategoryProduct("4032","Детский мир", r == true ? R.drawable.children_c: R.drawable.children_d));

        return mCategory;    }

    public String getFiltr() {
        return mFiltr;
    }

    public void setFiltr(final String filtr) {
        mFiltr = filtr;
    }
}
