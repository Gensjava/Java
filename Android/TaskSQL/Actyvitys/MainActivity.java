package ua.com.it_st.deliveryman.Actyvitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

import ua.com.it_st.deliveryman.Adapters.ItemListBaseAdapter;
import ua.com.it_st.deliveryman.Enums.TypeRequest;
import ua.com.it_st.deliveryman.Fragments.MainContragentFragment;
import ua.com.it_st.deliveryman.Fragments.MainFragment;
import ua.com.it_st.deliveryman.Fragments.MainObmenDataFragment;
import ua.com.it_st.deliveryman.R;
import ua.com.it_st.deliveryman.Utils.UtilAsyncHttpClient;
import ua.com.it_st.deliveryman.Utils.IWorkerCallback;



public class MainActivity extends ActionBarActivity implements
        ItemListBaseAdapter.onSomeEventListener,View.OnClickListener,
        IWorkerCallback {

    private android.support.v4.app.FragmentTransaction ft;
    private Fragment fragment;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onOpenFragment(new MainFragment());

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

    void onOpenFragment(Fragment fragment) {

        if (fragment!= null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(fragment.getClass().toString());
            ft.commit();
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    @Override
    public void someEvent(final String key, final String id) {

        switch (key) {// обрабатывам клик на товар
            case ItemListBaseAdapter.ACTION_ITEM:

                //fragment = new MainObmenDataFragment();
                fragment = new MainContragentFragment();
                break;
            default:
                //fragment = new MainContragentFragment();
                break;
        }
        onOpenFragment(fragment);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {// обрабатывам клик на товар
            case R.id.obmen_download:

                UtilAsyncHttpClient utilAsyncHttpClient = new UtilAsyncHttpClient();
                utilAsyncHttpClient.setBasicAuth("admin","123");
                try {
                    utilAsyncHttpClient.getPublicTimeline();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }



    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final Object mPJSONArray) {

    }

    @Override
    public void onFailure(final Throwable t) {

    }

    @Override
    public void onEnd() {

    }
}
