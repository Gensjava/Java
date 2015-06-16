package ua.smartshop.Activity;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    private byte tabsPage;
    private View nextPageRight;
    private View nextPageLeft;
    //
    private TabHost tabs;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cart_activity);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec(getString(R.string.page_one));

        spec.setContent(R.id.cart_tab1);
        spec.setIndicator(getString(R.string.cart));
        tabs.addTab(spec);

        spec = tabs.newTabSpec(getString(R.string.page_two));
        spec.setContent(R.id.cart_tab2);
        spec.setIndicator(getString(R.string.design));
        tabs.addTab(spec);

        spec = tabs.newTabSpec(getString(R.string.page_three));
        spec.setContent(R.id.cart_tab3);
        spec.setIndicator(getString(R.string.pay));

        tabs.addTab(spec);

        tabs.setCurrentTab(tabsPage);

        nextPageRight = (View) findViewById(R.id.cart_next_page_right);
        nextPageRight.setOnClickListener(this);
        nextPageLeft = (View) findViewById(R.id.cart_next_page_left);
        nextPageLeft.setOnClickListener(this);
        //
        ((TextView) findViewById(R.id.cart_total_sum)).setText(String.valueOf(Cart.getTotalSum()));

        setTabColor(tabs);
        nextVisibility();

        // Add a tab change listener, which calls a method that sets the text color.
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tabs.getApplicationWindowToken(), 0);

                tabsPage = (byte) tabs.getCurrentTab();
                setTabColor(tabs);
                nextVisibility();
            }
        });
        //делаем стрелу вместо меню
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
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

    @Override
    public void someEvent(final String view_id, final String item_id) {

        switch (view_id) {//
            case CartAdapter.TEG_GART_TOTAL:
            case CartFragment.TEG_GART_TOTAL_FRAGMENT:

                ((TextView) findViewById(R.id.cart_total_sum)).setText(String.valueOf(Cart.getTotalSum()));

                if (Cart.getmCart().size() == 0 ){
                    ((TextView)  findViewById(R.id.lvMain_text)).setText(getString(R.string.cart_in_empty));
                }

            case CartFragment.ACTION_GART_FRAGMENT:
                //linkFragment = new OrderMakeFragment();
                // tabs.setCurrentTab(1);

                break;
            default:
                break;
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
            case R.id.cart_next_page_right:
                tabs.setCurrentTab(tabsPage +=1);
                nextVisibility();
                break;
            case R.id.cart_next_page_left:
                tabs.setCurrentTab(tabsPage -=1);
                nextVisibility();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                //Надо вернуть иконку
                onBackPressed();
                MainActivity.updateHotCount();
                return true;
        }
        return true;
    }
}
