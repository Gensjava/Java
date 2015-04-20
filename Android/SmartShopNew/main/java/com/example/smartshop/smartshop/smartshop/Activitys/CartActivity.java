package ua.smartshop.Activitys;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import ua.smartshop.Adapters.CartAdapter;
import ua.smartshop.Fragments.CartFragment;
import ua.smartshop.Models.Cart;
import ua.smartshop.R;


/**
 * Created by Gens on 27.03.2015.
 */
public class CartActivity extends ActionBarActivity implements CartAdapter.onSomeEventListener, View.OnClickListener{

    private static final String TEXT_DESIGN = "Оформление";
    private static final String TEXT_CART = "Корзина";
    private static final String TEXT_PAY = "Оплата";
    private static final String TEG_PAGE_ONE = "tag1";
    private static final String TEG_PAGE_TWO = "tag2";
    private static final String TEG_PAGE_THREE = "tag3";
    //
    TabHost tabs;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cart_activity);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tabs = (TabHost) findViewById(android.R.id.tabhost);

        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec(TEG_PAGE_ONE);

        spec.setContent(R.id.cart_tab1);
        spec.setIndicator(TEXT_CART);
        tabs.addTab(spec);

        spec = tabs.newTabSpec(TEG_PAGE_TWO);
        spec.setContent(R.id.cart_tab2);
        spec.setIndicator(TEXT_DESIGN);
        tabs.addTab(spec);

        spec = tabs.newTabSpec(TEG_PAGE_THREE);
        spec.setContent(R.id.cart_tab3);
        spec.setIndicator(TEXT_PAY);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
        //
        View buttonPay = (View) findViewById(R.id.order_pay_button);
        buttonPay.setOnClickListener(this);
        View buttonMake = (View) findViewById(R.id.cart_make_order);
        buttonMake.setOnClickListener(this);

    }

    @Override
    public void someEvent(final String view_id, final String item_id) {

        switch (view_id) {//
            case CartAdapter.TEG_GART_TOTAL:
            case CartFragment.TEG_GART_TOTAL_FRAGMENT:

                ((TextView) findViewById(R.id.cart_total_sum)).setText(String.valueOf(Cart.getTotalSum()));
                ((TextView) findViewById(R.id.order_item_total)).setText(String.valueOf(Cart.getTotalSum()));

            case CartFragment.ACTION_GART_FRAGMENT:
                //linkFragment = new OrderMakeFragment();
               // tabs.setCurrentTab(1);

                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.cart_make_order:
                tabs.setCurrentTab(1);
                break;
            case R.id.order_pay_button:
                tabs.setCurrentTab(2);
                break;
            default:
                break;
        }

    }
}
