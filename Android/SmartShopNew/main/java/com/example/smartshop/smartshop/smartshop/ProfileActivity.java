package ua.smartshop;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TabHost;
import android.widget.TextView;

import ua.smartshop.Models.Profile;


public class ProfileActivity extends ActionBarActivity{

    public final static String ACCOUNT_TYPE = BuildConfig.APPLICATION_ID;
    public final static String AUTH_TYPE = "AUTH_TYPE";
    public final static String IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(!Profile.mAuthorization){

            setContentView(R.layout.profile_activity);

            final TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
            tabs.setup();
            TabHost.TabSpec spec = tabs.newTabSpec("tag1");

            spec.setContent(R.id.profile_tab1);
            spec.setIndicator("Вход");
            tabs.addTab(spec);

            spec = tabs.newTabSpec("tag2");
            spec.setContent(R.id.profile_tab2);
            spec.setIndicator("Регистрация");
            tabs.addTab(spec);
            tabs.setCurrentTab(0);

            setTabColor(tabs);

            // Add a tab change listener, which calls a method that sets the text color.
            tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                public void onTabChanged(String tabId) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(tabs.getApplicationWindowToken(), 0);
                    setTabColor(tabs);
                }
            });

        } else {

            setContentView(R.layout.profile_activity_cabinet);
            final TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
            tabs.setup();
            //
            TabHost.TabSpec spec = tabs.newTabSpec("tag3");
            spec.setContent(R.id.profile_tab3);
            spec.setIndicator("кабинет");
            tabs.addTab(spec);
            tabs.setCurrentTab(0);

            setTabColor(tabs);

            // Add a tab change listener, which calls a method that sets the text color.
            tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                public void onTabChanged(String tabId) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(tabs.getApplicationWindowToken(), 0);
                    setTabColor(tabs);
                }
            });

        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


