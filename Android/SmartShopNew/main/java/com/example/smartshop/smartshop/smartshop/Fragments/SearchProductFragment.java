package ua.smartshop.Fragments;



import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Models.Product;

/**
 * Created by Gens on 14.06.2015.
 */
public class SearchProductFragment extends ProductFragment {

    public  void newInstance(String url,String key_item, int count ) {
        setItemNumber(1);
        getPoducts().clear();
        doSomethingAsyncOperaion(Product.getParamsUrlNumberItem( getItemNumber(), key_item, count), url,  TypeRequest.GET);

    }

}
