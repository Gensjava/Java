package ua.smartshop;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

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

            TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
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

        } else {

            setContentView(R.layout.profile_activity_cabinet);
            TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
            tabs.setup();
            //
            TabHost.TabSpec spec = tabs.newTabSpec("tag3");
            spec.setContent(R.id.profile_tab3);
            spec.setIndicator("кабинет");
            tabs.addTab(spec);
            tabs.setCurrentTab(0);

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


