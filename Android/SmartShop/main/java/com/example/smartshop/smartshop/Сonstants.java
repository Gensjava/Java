package com.example.smartshop.smartshop;

import java.util.ArrayList;

/**
 * Created by Gens on 01.03.2015.
 */
 public  class Сonstants {
    
     // адреса
    public static final String url_all_products = "http://10.0.2.2/denwer/get_all_products_test.php";
    public static final String url_details_product = "http://10.0.2.2/denwer/get_product_details.php";
    public static final String url_product_description = "http://10.0.2.2/denwer/get_product_description.php";
    public static final String url_get_category_products = "http://10.0.2.2/denwer/get_category_products.php";
    public static final String url_get_cproducts_from_category = "http://10.0.2.2/denwer/get_all_products_from_category.php";
    
    public static final String url_main_way_image = "http://www.smart-shop.ua/photo/product/";
    public static final String url_main_peger = "http://www.smart-shop.ua/images/main/";

    // Имена узлов JSON
    public static final String TAG_NAME = "name";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_PRODUCTS = "products";
    public static final String TAG_PID = "pid";
    public static final String TAG_WAY_IMAGE = "wayimage";
    public static final String TAG_PRICE = "price";
    public static final String TAG_DISCRIPTION = "description";
    public static final String TAG_PRODUCT ="product";
    public static final String TAG_KOD = "kod";

    //Корзина
    public static final ArrayList<Order> mCart = new ArrayList<Order>();
    //Текущий товар
    public static Order currentOrder = new Order();
    //Текущий пользователь
    public static Profile currentProfile = new Profile();



}
