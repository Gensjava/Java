package com.example.smartshop.smartshop;

import java.util.ArrayList;

/**
 * Created by Gens on 01.03.2015.
 */
 public  class Сonstants {

   //корни
    public static final String url_main = "http://www.smart-shop.ua/";
    public static final String url_main_way_image = url_main + "/photo/product/";
    public static final String url_main_peger = url_main + "/images/main/";

    // адреса
    public static final String url_all_products = "http://10.0.3.2/denwer/a_get_all_products_two.php";
    public static final String url_details_product = "http://10.0.3.2/denwer/a_get_product_details.php";
    public static final String url_product_description = "http://10.0.3.2/denwer/a_get_product_description.php";
    public static final String url_get_category_products = "http://10.0.3.2/denwer/a_get_category_products.php";
    public static final String url_get_cproducts_from_category = "http://10.0.3.2/denwer/a_get_all_products_from_category.php";
    public static final String url_get_slider_main_page = "http://10.0.3.2/denwer/a_get_slider_main_page.php";
    public static final String url_get_slider_main_page_category = "http://10.0.3.2/denwer/a_get_slider_main_page_category.php";
    //public static final String url_get_cproducts_from_category = "http://10.0.2.2/denwer/get_all_products_from_category.php";

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

    //Текущий пользователь
    public static Profile profile = new Profile();
    //
    public static ArrayList<CategoryProduct> mCategory = new ArrayList<CategoryProduct>();

}
