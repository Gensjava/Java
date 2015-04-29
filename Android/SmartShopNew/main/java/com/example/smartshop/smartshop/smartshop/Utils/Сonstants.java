package ua.smartshop.Utils;


import ua.smartshop.Models.CategoryProduct;
import ua.smartshop.Models.Product;

/**
 * Created by Gens on 01.03.2015.
 */
 public  class Сonstants {

   //корни
    public static final String url_main = "http://www.smart-shop.ua/";
    public static final String url_main_way_image = url_main + "http://www.smart-shop.ua/photo/product/";

    private static final String is_server = "http://smart-shop.ua/JHVafnmJKH/";
    //http://10.0.3.2/denwer/
    //http://smart-shop.ua/JHVafnmJKH/

    // адреса
    public static final String url_all_products = is_server+"a_get_all_products_two.php";
    public static final String url_details_product = is_server+"a_get_product_details.php";
    public static final String url_product_description = is_server+"a_get_product_description.php";
    public static final String url_get_category_products = is_server+"a_get_category_products.php";
    public static final String url_get_cproducts_from_category = is_server+"a_get_all_products_from_category.php";
    public static final String url_get_slider_main_page = is_server+"a_get_slider_main_page.php";
    public static final String url_get_slider_main_page_category = is_server+"a_get_slider_main_page_category.php";
    public static final String url_get_category_product_file_imege = is_server+"a_get_category_product_file_imege.php";
    public static final String url_set_user_registration = is_server+"a_set_user_registration.php";
    public static final String url_get_user_authorization = is_server+"a_get_user_authorization.php";
    public static final String url_get_search_products_two = is_server+"a_get_search_products_two.php";
    public static final String url_get_user = is_server+"a_get_user.php";

    // Имена узлов JSON
    public static final String TAG_NAME = "name";
    public static final String TAG_USER_NAME = "username";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_DISCRIPTION = "description";
    public static final String TAG_PRODUCT ="product";
    public static final String TAG_PASWWORD = "password";
    public static final String TAG_PHONE = "phone";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_ADDRESS = "DeliveryAddress";
    public static final String TAG_ICQ_SKYPE = "icq_skype";
    //
    public static final String VALUE_KEY_ITEM_ID = "idItem";
    public static final String VALUE_KEY_ITEM_NUMBER = "itemnumber";
    public static final String TEXT_PRICE = "Цена ";
    public static final String TEXT_CURRENCY = " грн.";

    public static CategoryProduct[] categoryProduct = new CategoryProduct[0];

}
