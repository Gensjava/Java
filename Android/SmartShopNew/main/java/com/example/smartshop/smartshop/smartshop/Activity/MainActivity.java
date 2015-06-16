package ua.smartshop.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import ua.smartshop.Adapters.CategoryAdapter;
import ua.smartshop.Adapters.MainAdapter;
import ua.smartshop.Adapters.MainProductAdapter;
import ua.smartshop.Adapters.MainPagerAdapter;
import ua.smartshop.Adapters.ProductItemAdapter;
import ua.smartshop.Fragments.SearchProductFragment;
import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.Fragments.CartFragment;
import ua.smartshop.Fragments.CategoryProductFragment;
import ua.smartshop.Fragments.CategoryProductRootFragment;
import ua.smartshop.Fragments.ErrorFragment;
import ua.smartshop.Fragments.MainContactFragment;
import ua.smartshop.Fragments.MainLogoFragment;
import ua.smartshop.Fragments.MainPreferenceFragment;
import ua.smartshop.Fragments.ProductDiscriptionFragment;
import ua.smartshop.Fragments.ProducttItemRootFragment;
import ua.smartshop.Fragments.MainFragment;
import ua.smartshop.Models.Cart;
import ua.smartshop.Models.Profile;
import ua.smartshop.Fragments.ProductFragment;
import ua.smartshop.R;


public class MainActivity extends ActionBarActivity  implements
        MainAdapter.onSomeEventListener,
        MainPagerAdapter.onSomeEventListener,
        View.OnClickListener,
        CategoryAdapter.onSomeEventListener,
        AdapterView.OnItemSelectedListener,
        ProducttItemRootFragment.onUpDataCartListener,
        AsyncWorker.onSomeEventListener,
        MainProductAdapter.onSomeEventListener

{

    public static final String KEY_ITEM = "KEY_ITEM";
    public static final String COUNT_GOODS = "COUNT_GOODS";
    private static final String ACTION_SEARCH = "ACTION_SEARCH";
    public static final String URL_KEY = "URL_KEY";
    private  final int count_one = 1 ;
    private  final int count_two = 2 ;
    public static View ui_bar;

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private boolean openMain;
    //
    private Fragment fragment;
    private android.support.v4.app.FragmentTransaction ft;
    private static TextView ui_hot = null;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (!openMain){
            onOpenFragment(new MainLogoFragment());

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    openMain = true;
                    onOpenFragment(new MainFragment());
                }
            }, 5000); //
        }
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        ListView mDrawerListRight = (ListView) findViewById(R.id.right_drawer);

        //
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));

        mDrawerListRight.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {


            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); //
            }
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); //
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.hotlist_bell:
                selectItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {

    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent) {

    }

    @Override
    public void UpDataCart() {
        updateHotCount();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }
    }

    private void selectItem(int position) {

        fragment = null;

        switch (position) {
            case 0:
                Intent ProfileIntent = new Intent(this,ProfileActivity.class);
                ProfileIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ProfileIntent);
                break;
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                //проверка регистрации
                Profile.getAllAccount(this);

                if (!Profile.mAuthorization){
                    Profile.startAuthorization(this);
                } else {
                    Intent CartIntent = new Intent(this,CartActivity.class);
                    CartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(CartIntent);
                }
                break;
            case 3:
                fragment = new MainContactFragment();
                break;
            case 4:
                fragment = new MainPreferenceFragment();
                break;
            case 6:
                 onBackPressed();
                break;
            default:
                break;
        }

        if (fragment != null) {
            onOpenFragment(fragment);

            if(!fragment.getClass().equals(new MainFragment().getClass())){
                mDrawerToggle.setDrawerIndicatorEnabled(false);
            }
        } else {
            Log.e(this.getClass().getName(), "Error. object is not created");
        }

        if (position == 0 || position == 2 || fragment != null ){

            mDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
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
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final MenuItem customBar = menu.add(0, R.id.menu_bar, 0,"");
        customBar.setActionView(R.layout.progress_bar);
        customBar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        final View menu_hotlistBar = menu.findItem(R.id.menu_bar).getActionView();
        ui_bar = (View) menu_hotlistBar.findViewById(R.id.menu_bar);

        // Inflate the menu;
        final MenuItem custom = menu.add(0, R.id.menu_search, 0,"");
        custom.setActionView(R.layout.main_action_bar_search);
        custom.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        final View actionView = menuItem.getActionView();

        final SearchView ButtonSearch = (SearchView) actionView.findViewById(R.id.searchView);
        //меняем значок поиска
        int searchIconId = ButtonSearch.getContext().getResources().
                getIdentifier("android:id/search_button", null, null);
        ImageView searchIcon = (ImageView) ButtonSearch.findViewById(searchIconId);
        searchIcon.setImageResource(R.drawable.abc_ic_search_api_mtrl_alpha);

        ButtonSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {

                SearchProductFragment searchProductFragment = (SearchProductFragment) getSupportFragmentManager().findFragmentByTag(SearchProductFragment.class.toString());

                if (searchProductFragment == null) {
                    someEvent(ACTION_SEARCH, s);
                }else {
                    searchProductFragment.newInstance(getString(R.string.url_get_search_products), s, count_one);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(final String s) {
                return false;
            }
        });

        //////////////////////////////////////////////////////////////////////////////////

        final MenuItem customCart = menu.add(0, R.id.menu_hotlist, 0,"");
        customCart.setActionView(R.layout.main_action_bar_cart);
        customCart.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        final View menu_hotlist = menu.findItem(R.id.menu_hotlist).getActionView();

        ui_hot = (TextView) menu_hotlist.findViewById(R.id.hotlist_hot);

        updateHotCount();

        final MenuItem menuItemCart = menu.findItem(R.id.menu_hotlist);
        final View actionViewCart = menuItemCart.getActionView();

        final View ButtonCart = actionViewCart.findViewById(R.id.hotlist_bell);
        ButtonCart.setOnClickListener(this);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId())
        {
            case android.R.id.home:
                //При нажатии вызывается обработчик кнопки назад
                //Он по умолчанию должен будет вернуться по списку фрагментов назад
                onBackPressed();
                return true;
        }
        return true;
    }
    @Override
    public void someEvent(final String key, final String value) {

        Bundle bundleItem = new Bundle();
        bundleItem.putString(KEY_ITEM,  value);

        switch (key) {// обрабатывам клик на товар
            case MainProductAdapter.ACTION_ITEM:

                fragment = new ProducttItemRootFragment();
                break;
            case ProductItemAdapter.ACTION_DISRIPTION:// просмотр хар-к товара

                fragment = new ProductDiscriptionFragment();
                break;
            case MainAdapter.ACTION_CATEGORY_ALL:// открываем корень категорий

                fragment = new CategoryProductRootFragment();
                break;
            case MainPagerAdapter.ACTION_ONCLIK_ITEM_PEGER_ADAPTER://банер

                fragment = new ProductFragment();
                bundleItem.putString(URL_KEY, getString(R.string.url_get_slider_main_page_category));
                bundleItem.putInt(COUNT_GOODS, count_two);
                break;
            case ACTION_SEARCH://поиск потовару

                fragment = new SearchProductFragment();

                bundleItem.putString(URL_KEY, getString(R.string.url_get_search_products));
                bundleItem.putInt(COUNT_GOODS, count_one);
                break;
            case CategoryAdapter.ACTION_FROM_CATEGORY_PRODUCT:

                fragment = new ProductFragment();
                bundleItem.putString(URL_KEY, getString(R.string.url_get_cproducts_from_category));
                bundleItem.putInt(COUNT_GOODS, count_one);
                break;
            case CategoryAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER://категория товаров
            case MainAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN:

                fragment = new CategoryProductFragment();
                break;
            case AsyncWorker.ERROR_JSON:
                fragment = new ErrorFragment();
                break;
            default:
                break;
        }
        if (fragment!= null ) {
            fragment.setArguments(bundleItem);
            onOpenFragment(fragment);
            //делаем стрелу вместо меню
            if(!fragment.getClass().equals(new ErrorFragment().getClass())){
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            }
        }
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
            ft.replace(R.id.content_frame, fragment, fragment.getClass().toString());
            ft.commit();
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static  void updateHotCount() {

        if (ui_hot == null) return;

        if (Cart.mCart.size() == 0)
            ui_hot.setVisibility(View.INVISIBLE);
        else {
            ui_hot.setVisibility(View.VISIBLE);
            ui_hot.setText(Integer.toString(Cart.mCart.size()));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateHotCount();

        if (checkMainFragment()){
            mDrawerToggle.setDrawerIndicatorEnabled(true); //меняемс стрелку в меню
        }
    }

    private boolean checkMainFragment(){
        boolean checkMain = false;
        Fragment mainFragment = getSupportFragmentManager().findFragmentByTag(MainFragment.class.toString());
        if (mainFragment != null){
            checkMain = mainFragment.isVisible();
        }
        return checkMain;
    }
}