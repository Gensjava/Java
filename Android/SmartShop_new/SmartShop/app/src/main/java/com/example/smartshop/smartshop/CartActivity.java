package com.example.smartshop.smartshop;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TabHost;

/**
 * Created by Gens on 27.03.2015.
 */
public class CartActivity extends ActionBarActivity{
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cart_activity);

        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);

        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

        spec.setContent(R.id.cart_tab1);
        spec.setIndicator("Корзина");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.cart_tab2);
        spec.setIndicator("Оформление");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag3");
        spec.setContent(R.id.cart_tab3);
        spec.setIndicator("Оплата");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

    }
}
