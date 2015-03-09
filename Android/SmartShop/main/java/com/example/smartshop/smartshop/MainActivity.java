package com.example.smartshop.smartshop;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity  implements MainAdapter.onSomeEventListener, AdapterItem.onSomeEventListener, AdapterViewPager.onSomeEventListener,View.OnClickListener  {

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    // Update the main content by replacing fragments
    Fragment fragment = null;
    Fragment linkFragment = null;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      
      

        mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // Set the adapter for the list view
       mDrawerList.setAdapter(new ArrayAdapter<String>(this,
               R.layout.drawer_list_item, mScreenTitles));

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Initialize the first fragment when the application first loads.
        if (savedInstanceState == null) {
            selectItem(1);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.in_box:
                Сonstants.mCart.add(Сonstants.currentOrder);
                break;
            case R.id.buy_now:
           
                break;
            case R.id.ImageViewcart:
                //test ();
            selectItem(2);
                break;
            default:
                break;
        }
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {

        switch (position) {
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                fragment = new FragmentCart();
                break;
           case 3:
                fragment = new FragmentPreference();
                break;
            default:
                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(fragment.toString())
                    .replace(R.id.content_frame, fragment).commit();

            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
           setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;

        final MenuItem custom = menu.add(0, R.id.menu_custom, 0,"Custom");
        custom.setActionView(R.layout.menu_custom);
        custom.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        final MenuItem menuItem = menu.findItem(R.id.menu_custom);
        final View actionView = menuItem.getActionView();

        final View ButtonCart = actionView.findViewById(R.id.ImageViewcart);
        ButtonCart.setOnClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
        // Handle action buttons
//        switch(item.getItemId()) {
//            case R.id.action_search:
//                // Show toast about click.
//                //Toast.makeText(this, R.string.action_search, Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
        //}
    }
    @Override
    public void someEvent(String view_id, String idItem) {

        switch (view_id) {

            case "imageView_itemOne":
            case "imageView_itemTwo":
                linkFragment = new FragmentItem();
                
                Bundle bundle = new Bundle();
                bundle.putString("idItem", idItem);
                linkFragment.setArguments(bundle);

                break;
            case AdapterItem.ACTION_DELIVER:
                linkFragment = new FragmentDiscription();

                Bundle bundleDiscription = new Bundle();
                bundleDiscription.putString("idItem", idItem);
                linkFragment.setArguments(bundleDiscription);

                break;
            case "category_all_text":
                linkFragment = new FragmentCategoryLevelOne();

                break;
            case "view_pager":
                linkFragment = new FragmentPegerShare();
                break;
            default:
                break;
        }
        onOpenFragment(linkFragment);
    }
    public void onOpenFragment(Fragment fragment) {
        
        if (fragment!= null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(fragment.toString())
                    .replace(R.id.content_frame, fragment).commit();
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }        
    }
    
    void test (){

        RequestQueue queue = Volley.newRequestQueue(this);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("idItem", "9066"));
        String paramString = URLEncodedUtils.format(params, "utf-8");
        
        String url = Сonstants.url_details_product+"/?"+paramString;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override           
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub

                JSONObject productsArray = response;
                try {
                    JSONArray  productsArray1 = productsArray.getJSONArray(Сonstants.TAG_PRODUCT);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    int success = productsArray.getInt(Сonstants.TAG_SUCCESS);
                    Log.i("success",""+success);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final String json = new String(response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        });
        queue.add(jsObjRequest);
    }

}