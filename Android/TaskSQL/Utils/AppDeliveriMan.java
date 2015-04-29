package ua.com.it_st.deliveryman.Utils;

import android.app.Application;

/**
 * Created by Gens on 27.04.2015.
 */
public class AppDeliveriMan extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UtilSQLiteOpenHelper.init(this);
    }
}
