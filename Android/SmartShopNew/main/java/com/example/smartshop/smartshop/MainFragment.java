package com.example.smartshop.smartshop;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gens on 02.03.2015.
 */

public class MainFragment extends android.support.v4.app.Fragment {


    private int itemNumber = 1;
    ArrayList<ProductDual> mPoducts = new ArrayList<ProductDual>();

    MainAdapter mainAdapter;

    ListView lvMain;
    private ProgressDialog pDialog;

    Product ProductOne;
    Product ProductTwo;
    // массив товаров JSONArray
    JSONArray products;
    // Создаем объект JSON Parser
    JSONParser jParser = new JSONParser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        // создаем адаптер
        fillData();
        mainAdapter = new MainAdapter(getActivity(), mPoducts);

        // настраиваем список
        lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mainAdapter);

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    itemNumber++;
                   new LoadAllProductsTask().execute();
                }
            }
        });
        return rootView;
    }
    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 4; i++) {
            mPoducts.add(new ProductDual(new Product(), new Product()));
        }
    }

    class LoadAllProductsTask extends AsyncTask<String, String, String> {
        // Сначала покажем диалоговое окно прогресса

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair(Сonstants.VALUE_KEY_ITEM_NUMBER, String.valueOf(itemNumber)));

            try {
                // получим строку JSON из URL
                JSONObject json = jParser.makeHttpRequest( Сonstants.url_all_products, "GET",
                        params);
                int success = json.getInt(Сonstants.TAG_SUCCESS);

                if (success == 1) {     // товар найден
                    // получаем массив товаров
                    products = json.getJSONArray(Сonstants.TAG_PRODUCTS);

                    // проходим в цикле через все товары
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        String id = c.getString(Сonstants.TAG_PID);
                        String wayImage = c.getString(Сonstants.TAG_WAY_IMAGE);
                        String fullImage = Сonstants.url_main_way_image + wayImage;

                        double price = 0.00;

                            price = Double.parseDouble(c.getString(Сonstants.TAG_PRICE));
                            switch (i) {
                                case 0:
                                    ProductOne = new Product(price, R.drawable.flatscreen, id,fullImage);
                                    break;
                                case 1:
                                    ProductTwo = new Product(price, R.drawable.flatscreen, id, fullImage);
                                    break;
                                default:
                                    break;
                            }
                    }
                    mPoducts.add(new ProductDual(ProductOne, ProductTwo));


                } else {
                    // не нашли товар по pid
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            mainAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(final String... values) {
            super.onProgressUpdate(values);

        }
    }
}
