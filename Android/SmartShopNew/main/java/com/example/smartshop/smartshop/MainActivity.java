package com.example.smartshop.smartshop;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity  implements
        MainAdapter.onSomeEventListener,
        ProductItemAdapter.onSomeEventListener,
        MainPagerAdapter.onSomeEventListener,
        View.OnClickListener,
        CategoryAdapter.onSomeEventListener,
        ProductAdapter.onSomeEventListener,
        AdapterView.OnItemSelectedListener,
        UtilAsyncHttpClient.onSomeEventListenerAsync,

        ImageTextAdapter.onSomeEventListener

{

    public static final String KEY_ITEM = "KEY_ITEM";
    private static final String ACTION_SEARCH = "ACTION_SEARCH";
    public static final String URL_KEY = "URL_KEY";

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    //
    private Fragment fragment;
    private android.support.v4.app.FragmentTransaction ft;
    private boolean openMain;
    private int hot_number = 5;
    private TextView ui_hot = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

        MainLogoFragment mainLogoFragment = new MainLogoFragment();
        onOpenFragment(mainLogoFragment);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //делаем поиск события
                openMain = true;
                onOpenFragment(new MainFragment());
            }
        }, 5000); //

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cart_make_order:
                someEvent( CartFragment.ACTION_GART_FRAGMENT,  null);
                break;
            case R.id.hotlist_bell:

                updateHotCount(Cart.mCart.size());

                if (!Profile.mAuthorization){
                    Profile.startAuthorization(this);
                } else {
                    selectItem(2);
                }
                break;
            //someEvent(ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER,item.getId());
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
        // mSelectText.setText("Выбранный элемент: " + mAdapter.GetItem(position));
    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent) {
//        mSelectText.setText("Выбранный элемент: ничего");
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
            case 0:
                Intent ProfileIntent = new Intent(this,ProfileActivity.class);
                startActivity(ProfileIntent);
                break;
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                Intent CartIntent = new Intent(this,CartActivity.class);
                startActivity(CartIntent);
                break;
            case 3:
                fragment = new MainContactFragment();
                break;
            case 4:
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
        final MenuItem customCart = menu.add(0, R.id.menu_hotlist, 0,"");
        customCart.setActionView(R.layout.action_bar_notifitcation_icon);
        customCart.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        final View menu_hotlist = menu.findItem(R.id.menu_hotlist).getActionView();

        ui_hot = (TextView) menu_hotlist.findViewById(R.id.hotlist_hot);

        updateHotCount(hot_number);
        new MyMenuItemStuffListener(menu_hotlist, "Show hot message") {
            @Override
            public void onClick(View v) {
                //onHotlistSelected();
            }
        };
        final MenuItem menuItemCart = menu.findItem(R.id.menu_hotlist);
        final View actionViewCart = menuItemCart.getActionView();

        final View ButtonCart = actionViewCart.findViewById(R.id.hotlist_bell);
        ButtonCart.setOnClickListener(this);

        //////////////////////////////////////////////////////////////////////////////////

        final MenuItem custom = menu.add(0, R.id.menu_custom, 0,"");
        custom.setActionView(R.layout.main_action_bar_custom);
        custom.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        final MenuItem menuItem = menu.findItem(R.id.menu_custom);
        final View actionView = menuItem.getActionView();


        final SearchView ButtonSearch = (SearchView) actionView.findViewById(R.id.searchView);
        ButtonSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {

                someEvent(ACTION_SEARCH, s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {

                return false;
            }
        });

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
    public void someEvent(final String key, final String value) {

        switch (key) {// обрабатывам клик на товар

            case MainAdapter.ACTION_ITEM_ONE:
            case MainAdapter.ACTION_ITEM_TWO:
            case ProductAdapter.ACTION_ITEM_ONE_PRODUCT:
            case ProductAdapter.ACTION_ITEM_TWO_PRODUCT:


                //получаем список товаров
                List<HashMap> mArrayValues = getArrListData(Product.getTegs(), Product.getParamsUrl(value), TypeRequest.GET, Сonstants.url_details_product);

                //JSONArray mArrayValues1 = getArrListDataTest(Product.getParamsUrl(value), Сonstants.url_details_product, null ,TypeRequest.GET );
                //HashMap<String, String> params,String url, String tags, TypeRequest typeRequest
                if(!(mArrayValues == null)){
                    if(!(mArrayValues.size() == 0)){
                        fragment = new ProducttItemFragmen();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(KEY_ITEM, (java.io.Serializable) mArrayValues);
                        fragment.setArguments(bundle);
                    } else {
                        //Открываем фрагмент с ошибкой
                    }
                }
                break;
            case ProductItemAdapter.ACTION_DISRIPTION:// просмотр хар-к товара
                fragment = new ProductDiscriptionFragment();

                Bundle bundleDiscription = new Bundle();
                bundleDiscription.putString(Сonstants.VALUE_KEY_ITEM_ID, value);
                fragment.setArguments(bundleDiscription);

                break;
            case MainAdapter.ACTION_CATEGORY_ALL:// открываем корень категорий

                fragment = new CategoryProductLevelOneFragment();

                break;

            case MainPagerAdapter.ACTION_ONCLIK_ITEM_PEGER_ADAPTER://работает

                //получаем список товаров
                mArrayValues = getArrListData(MainPagerAdapter.getTegs(), MainPagerAdapter.getParamsUrl(value), TypeRequest.GET, Сonstants.url_get_slider_main_page_category);

                if(!(mArrayValues == null)){
                    if(!(mArrayValues.size() == 0)){
                        fragment = new ProductFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Сonstants.VALUE_KEY_ITEM_ID, value);
                        bundle.putString(URL_KEY, Сonstants.url_get_slider_main_page_category);
                        fragment.setArguments(bundle);
                    } else {
                        //Открываем фрагмент с ошибкой
                    }
                }
                break;
            case ACTION_SEARCH://работает

                //получаем список товаров
                mArrayValues = getArrListData(MainPagerAdapter.getTegs(), MainPagerAdapter.getParamsUrl(value), TypeRequest.GET,Сonstants.url_get_search_products_two);

                if(!(mArrayValues == null)){
                    if(!(mArrayValues.size() == 0)){
                        fragment = new ProductFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Сonstants.VALUE_KEY_ITEM_ID, value);
                        bundle.putString(URL_KEY, Сonstants.url_get_search_products_two);
                        fragment.setArguments(bundle);
                    } else {
                        //Открываем фрагмент с ошибкой
                    }
                }
                break;
            case CategoryAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER://работает
            case ImageTextAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN://работает

                //получаем список товаров
                mArrayValues = getArrListData(CategoryProduct.getTegs(), CategoryProduct.getParamsUrl(value), TypeRequest.GET,Сonstants.url_get_category_products);

                if(!(mArrayValues == null)){
                    if(!(mArrayValues.size() == 0)){
                        fragment = new CategoryProductFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(CategoryAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER, (java.io.Serializable) mArrayValues);
                        fragment.setArguments(bundle);
                    } else {

                        //получаем список товаров
                        mArrayValues = getArrListData(MainPagerAdapter.getTegs(), MainPagerAdapter.getParamsUrl(value), TypeRequest.GET,Сonstants.url_get_cproducts_from_category);

                        if(!(mArrayValues == null)){
                            if(!(mArrayValues.size() == 0)){
                                fragment = new ProductFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString(Сonstants.VALUE_KEY_ITEM_ID, value);
                                bundle.putString(URL_KEY, Сonstants.url_get_cproducts_from_category);
                                fragment.setArguments(bundle);
                            } else {
                                //Открываем фрагмент с ошибкой
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
        onOpenFragment(fragment);

    }
    void onOpenFragment(Fragment fragment) {

        if (fragment!= null) {

            FragmentManager fragmentManager = getSupportFragmentManager();

            ft = fragmentManager.beginTransaction();

            if(fragment.getClass().equals(new MainLogoFragment().getClass()) || openMain){
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
                openMain = false;
            } else {
                ft.addToBackStack(fragment.getClass().toString());
            }
            ft.replace(R.id.content_frame, fragment,fragment.getClass().toString());
            ft.commit();

        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    private List<HashMap> getArrListData(String tags[], HashMap<String, String> params, TypeRequest typeRequest, String url ) {

        List<HashMap> mArrayValues  = null ;
        UtilAsyncTask utilAsyncTask = new UtilAsyncTask(params, url , tags ,this, typeRequest);
        utilAsyncTask.execute();
        try {
            utilAsyncTask.get();
            mArrayValues  = utilAsyncTask.getArrayValues();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mArrayValues;
    }

    private JSONArray getArrListDataTest(HashMap<String, String> params,String url, String tags, TypeRequest typeRequest  ) {

        UtilSyncHttpClient utilSyncTask = new UtilSyncHttpClient(params, url , tags , typeRequest,this);

        Log.i("getArrListDataTest ","0");

        JSONArray mArrayValues =  utilSyncTask.getSyncArrayValues();

        Log.i("getArrListDataTest ","3  "+mArrayValues.length());

        return mArrayValues;
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void someEventAsync(final String tag, final JSONArray valueJSONArray ) {
        // Log.i("getArrListDataTest ","2  "+value);
        switch (tag){
            case "class com.example.smartshop.smartshop.MainFragment":
                MainFragment Mfragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(tag);
                if (Mfragment != null) {
                    Mfragment.executeArrayValues(valueJSONArray);
                }
                break;
            case "class com.example.smartshop.smartshop.ProductFragment":
                ProductFragment Prfragment = (ProductFragment) getSupportFragmentManager().findFragmentByTag(tag);
                if (Prfragment != null) {
                    Prfragment.executeArrayValues(valueJSONArray);
                }
                default:
                    break;

        }

    }
    static abstract class MyMenuItemStuffListener implements View.OnClickListener, View.OnLongClickListener {
        private String hint;
        private View view;

        MyMenuItemStuffListener(View view, String hint) {
            this.view = view;
            this.hint = hint;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override abstract public void onClick(View v);

        @Override public boolean onLongClick(View v) {
            final int[] screenPos = new int[2];
            final Rect displayFrame = new Rect();
            view.getLocationOnScreen(screenPos);
            view.getWindowVisibleDisplayFrame(displayFrame);
            final Context context = view.getContext();
            final int width = view.getWidth();
            final int height = view.getHeight();
            final int midy = screenPos[1] + height / 2;
            final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            Toast cheatSheet = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
            if (midy < displayFrame.height()) {
                cheatSheet.setGravity(Gravity.TOP | Gravity.RIGHT,
                        screenWidth - screenPos[0] - width / 2, height);
            } else {
                cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
            }
            cheatSheet.show();
            return true;
        }
    }
    public void updateHotCount(final int new_hot_number) {
        hot_number = new_hot_number;
        if (ui_hot == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Cart.mCart.size() == 0)
                    ui_hot.setVisibility(View.INVISIBLE);
                else {
                    ui_hot.setVisibility(View.VISIBLE);
                    ui_hot.setText(Integer.toString(new_hot_number));
                }
            }
        });
    }

}