package ua.smartshop.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.Date;
import ua.smartshop.Activity.MainActivity;
import ua.smartshop.Models.Cart;
import ua.smartshop.Models.Product;
import ua.smartshop.R;

public class ProducttItemRootFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    private TabHost tabs;
    public static String mItem = null;
    private TextView number,sum;
    private Product mProduct;
    private byte tabsPage;
    private onUpDataCartListener  mUpDataCartListener;
    private View nextPageRight;
    private View nextPageLeft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            mItem = bundle.getString(MainActivity.KEY_ITEM);
        }

        View rootView = inflater.inflate(R.layout.product_item_root, container,
                false);

        tabs = (TabHost) rootView.findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec(getString(R.string.page_one));
        spec.setContent(R.id.product_tab1);
        spec.setIndicator(getString(R.string.goods));
        tabs.addTab(spec);

        spec = tabs.newTabSpec(getString(R.string.page_two));
        spec.setContent(R.id.product_tab2);
        spec.setIndicator(getString(R.string.discription));
        tabs.addTab(spec);

        spec = tabs.newTabSpec(getString(R.string.page_three));
        spec.setContent(R.id.product_tab3);
        spec.setIndicator(getString(R.string.delyvery));
        tabs.addTab(spec);

        tabs.setCurrentTab(tabsPage);

        ImageView cartClick = (ImageView) rootView.findViewById(R.id.in_box);
        cartClick.setOnClickListener(this);

        nextPageRight = (View) rootView.findViewById(R.id.product_next_page_right);
        nextPageRight.setOnClickListener(this);
        nextPageLeft = (View) rootView.findViewById(R.id.product_next_page_left);
        nextPageLeft.setOnClickListener(this);
        //
        setTabColor(tabs);
        nextVisibility();

        // Add a tab change listener, which calls a method that sets the text color.
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tabs.getApplicationWindowToken(), 0);
                setTabColor(tabs);
                //
                tabsPage = (byte) tabs.getCurrentTab();
                nextVisibility();
            }
        });

        return  rootView;
    }
    private void setTabColor(TabHost tabHost) {
        try {
            for (int i=0; i < tabHost.getTabWidget().getChildCount();i++) {
                TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                if (tv != null) {
                    tv.setTextColor(Color.parseColor("#FFFFECB3"));
                }
                TextView tv2 = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); // Selected Tab
                if (tv2 != null) {
                    tv2.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        } catch (ClassCastException e) {
            // A precaution, in case Google changes from a TextView on the tabs.
        }
    }

    private void nextVisibility(){
        if (tabsPage == 0){
            nextPageLeft.setVisibility(View.INVISIBLE);
            nextPageRight.setVisibility(View.VISIBLE);
        }else if (tabsPage == 1){
            nextPageLeft.setVisibility(View.VISIBLE);
            nextPageRight.setVisibility(View.VISIBLE);
        }else if (tabsPage == 2 ){
            nextPageLeft.setVisibility(View.VISIBLE);
            nextPageRight.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.in_box:
              showCustomAlertDialogEnterNumber();
                break;
            case R.id.product_next_page_right:
            tabs.setCurrentTab(tabsPage +=1);
                nextVisibility();
                break;
            case R.id.product_next_page_left:
                tabs.setCurrentTab(tabsPage -=1);
                nextVisibility();
                break;
            default:
                break;
        }
    }
    //Создаем открываем диалог (добавляем запись дату)
    private void  showCustomAlertDialogEnterNumber() {

        //получаем Inflater
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        //каст макет
        final View numberView = layoutInflater.inflate(R.layout.dialog_number, null);
        mProduct = ProducttItemFragment.mProduct;

        if (mProduct == null){
            return;
        }
        //поля из макета
        final Button numberMinus =  (Button) numberView.findViewById(R.id.dialog_number_minus);
        final Button numberPlus =  (Button) numberView.findViewById(R.id.dialog_number_plus);
        final TextView price =  (TextView) numberView.findViewById(R.id.dialog_number_price);
        number =  (TextView) numberView.findViewById(R.id.dialog_number_number);
        sum =  (TextView) numberView.findViewById(R.id.dialog_number_sum);

        price.setText(String.valueOf(mProduct.getPrice()));
        sum.setText(String.valueOf(mProduct.getPrice()));

        final double[] numberD = {1.0};

        numberPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberD[0]++;
                number.setText(String.valueOf(numberD[0]));
                sum.setText(String.valueOf(mProduct.getPrice()* numberD[0]));
            }
        });

        numberMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( numberD[0] > 1){
                    numberD[0]--;
                    number.setText(String.valueOf(numberD[0]));
                    sum.setText(String.valueOf(mProduct.getPrice()* numberD[0]));
                }
            }
        });

        //открываем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Добавить в корзину");
        builder.setView(numberView);
        //кнопки
        builder .setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int id) {
                        double numberInDialog = Double.parseDouble(String.valueOf(number.getText()));
                        //заказ
                        Cart cart = new Cart(mProduct,  String.valueOf(getDate()), mProduct.getPrice(), numberInDialog, mProduct.getPrice() * numberInDialog);
                        Cart.setmCart(cart);
                        dialog.cancel();
                        mUpDataCartListener = (onUpDataCartListener) getActivity();
                        mUpDataCartListener.UpDataCart();
                    }
                })
                .setNegativeButton("Отмена", null);

        final AlertDialog alert = builder.create();
        alert.show();
    }
    // Получаем текущее дату системы
    // Возвращаем дату "текущюю дату"
    private Date getDate() {
        //текущая дата
        long curTime = System.currentTimeMillis();
        Date date = new Date(curTime);
        return date;
    }
    public interface onUpDataCartListener {
        public  void UpDataCart();
    }
}
