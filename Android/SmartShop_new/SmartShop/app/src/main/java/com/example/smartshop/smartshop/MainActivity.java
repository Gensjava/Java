package com.example.smartshop.smartshop;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity  implements
        MainAdapter.onSomeEventListener,
        ProductItemAdapter.onSomeEventListener,
        PagerViewAdapter.onSomeEventListener,
        View.OnClickListener,
        CategoryAdapter.onSomeEventListener,
        CategoryProductFragment.onSomeEventListener,
        ProductAdapter.onSomeEventListener,
        UtilVolley.onSomeEventListener,
        UtilAsyncTask.onSomeEventListener,
        UtilAsyncTask.onSomeEventListenerUtilAsyncTask,

        CartAdapter.onSomeEventListener

{

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

                break;
            case R.id.buy_now:

                break;
            case R.id.cart_make_order:
                someEvent( "FragmentMakeOrder",  "FragmentMakeOrder");
                Log.i("FragmentMakeOrder","FragmentMakeOrder");
                break;
            case R.id.ImageViewcart:

                if (!Profile.mAuthorization){
                    Profile.startAuthorization(this);
                } else {
                    selectItem(2);
                }
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
                // fragment = new CartFragment();
                Intent intent = new Intent(this,CartActivity.class);
                startActivity(intent);
                break;
            case 3:
                fragment = new PreferenceFragment();
                break;
            default:
                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            onOpenFragment(fragment);
            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    // Задача в другом потоке для загрузки всех товаров через HTTP Request
    void LoadAllProductsTask ()  {

        // Строим параметры
        RequestQueue queue = Volley.newRequestQueue(this);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // получим строку JSON из URL

        params.add(new BasicNameValuePair("itemnumber", "" + 3));
        String paramString = URLEncodedUtils.format(params, "utf-8");

        String url = Сonstants.url_all_products+"/?"+paramString;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            Product ProductOne ;
            Product ProductTwo ;
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub

                //JSONObject json = response;
//                try {
//                    int success = response.getInt(Сonstants.TAG_SUCCESS);
//
//                    if (success == 1) {     // товар найден
//                        // получаем массив товаров
//                        products = response.getJSONArray(Сonstants.TAG_PRODUCTS);
//                        //
//
//
//                        // проходим в цикле через все товары
//                        for (int i = 0; i < products.length(); i++) {
//                            JSONObject c = products.getJSONObject(i);
//
//                            String id = c.getString(Сonstants.TAG_PID);
//                            String wayImage = c.getString(Сonstants.TAG_WAY_IMAGE);
//                            String fullImage = Сonstants.url_main_way_image + wayImage;
//
//                            double price = 0.00;
//
//                            if (!(c.getString(Сonstants.TAG_PRICE).equals("null"))){
//                                price = Double.parseDouble(c.getString(Сonstants.TAG_PRICE));
//                                switch (i) {
//                                    case 0:
//                                        //ProductOne = new Product(price, R.drawable.flatscreen, id,fullImage);
//                                        break;
//                                    case 1:
//                                       // ProductTwo = new Product(price, R.drawable.flatscreen, id, fullImage);
//                                        break;
//                                    default:
//                                        break;
//                                }
//                            }
//                        }
//                        //mPoducts.add(new ProductDual(ProductOne, ProductTwo));
//                       // mainAdapter.notifyDataSetChanged();
//
//                    } else {
//                        // не нашли товар по pid
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        });

        queue.add(jsObjRequest);
        //обновляем

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
        custom.setActionView(R.layout.action_bar_custom);
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
    }
    @Override
    public void someEvent(String view_id, String idItem) {

        switch (view_id) {

            case "imageView_itemOne":
            case "imageView_itemTwo":

                String tags[] = new String[5];
                tags[0] = Сonstants.TAG_NAME;
                tags[1] = Сonstants.TAG_PRICE;
                tags[2] = Сonstants.TAG_KOD;
                tags[3] = Сonstants.TAG_DISCRIPTION;
                tags[4] = Сonstants.TAG_WAY_IMAGE;
                //
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("idItem",idItem);

                UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_details_product , tags ,this);
                utilAsyncTask.execute();
                try {
                    utilAsyncTask.get();

                    List<HashMap> mArrayValues  = utilAsyncTask.getArrayValues();

                    if(!(mArrayValues.size() == 0)){

                        linkFragment = new ProducttItemFragmen();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("imageView_mValues", (java.io.Serializable) mArrayValues);
                        linkFragment.setArguments(bundle);
                    } else {
                        //Открываем фрагмент с ошибкой
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case ProductItemAdapter.ACTION_DISRIPTION:
                linkFragment = new ProductDiscriptionFragment();

                Bundle bundleDiscription = new Bundle();
                bundleDiscription.putString("idItem", idItem);
                linkFragment.setArguments(bundleDiscription);

                break;
            case "category_all_text":
                linkFragment = new CategoryProductLevelOneFragment();

                break;

            case PagerViewAdapter.ACTION_ONCLIK_ITEM:

                tags = new String[4];
                tags[0] = Сonstants.TAG_PID;
                tags[1] = Сonstants.TAG_NAME;
                tags[2] = Сonstants.TAG_PRICE;
                tags[3] = Сonstants.TAG_WAY_IMAGE;
                //
                params = new HashMap<String, String>();
                params.put("idItem",idItem);
                params.put("itemnumber","1");

          utilAsyncTask = new UtilAsyncTask(params, Сonstants.url_get_slider_main_page_category, tags, this);
                utilAsyncTask.execute();
                try {
                    utilAsyncTask.get();

                    List<HashMap> mArrayValues  = utilAsyncTask.getArrayValues();

                    if(!(mArrayValues.size() == 0)){

                        linkFragment = new ShareFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("imageView_mValues", idItem);
                        linkFragment.setArguments(bundle);
                    } else {
                        //Открываем фрагмент с ошибкой
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                break;
            case "FragmentCategoryProduct":

                linkFragment = new ProductFragment();

                Bundle bundleCategoryProduct = new Bundle();
                bundleCategoryProduct.putString("idItemCategoryProduct", idItem);
                linkFragment.setArguments(bundleCategoryProduct);
                break;
            case "CarTotal":

                ((TextView)fragment.getView().findViewById(R.id.cart_total_sum)).setText(String.valueOf(Cart.getTotalSum()));
                break;
            case "FragmentMakeOrder":
                linkFragment = new OrderMakeFragment();

                break;
            default:
                break;
        }
        if (!view_id.equals("CarTotal")){
            onOpenFragment(linkFragment);
        }

    }
    @Override
    public void someEvent(ArrayList<Object> mCategory) {

        linkFragment = new CategoryProductFragment();

        Bundle bundlemCategory = new Bundle();
        bundlemCategory.putSerializable("mCategory", mCategory);
        linkFragment.setArguments(bundlemCategory);

        onOpenFragment(linkFragment);

    }
    public void onOpenFragment(Fragment fragment) {

        if (fragment!= null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack("myStack")
                    .commit();

        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

}