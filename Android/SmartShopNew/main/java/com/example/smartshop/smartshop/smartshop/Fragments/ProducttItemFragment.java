package ua.smartshop.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ua.smartshop.AsyncWorker;
import ua.smartshop.Adapters.ProductItemAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.IWorkerCallback;
import ua.smartshop.MainActivity;
import ua.smartshop.Models.Cart;
import ua.smartshop.Models.Product;
import ua.smartshop.R;
import ua.smartshop.Utils.Сonstants;

/**
 * Created by Gens on 03.03.2015.
 */
public class ProducttItemFragment extends android.support.v4.app.Fragment implements IWorkerCallback,View.OnClickListener  {

    private ArrayList<Product> mProducts = new ArrayList<Product>();
    private Product mProduct;
    private TextView number,sum;
    private onUpDataCartListener  mUpDataCartListener;
    private String mItem;
    private ProductItemAdapter mItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.product_list, container,
                false);
        //
        mItemAdapter = new ProductItemAdapter(getActivity(), mProducts);
        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.listItem);
        lvMain.setAdapter(mItemAdapter);

        ImageView cartButton = (ImageView) rootView.findViewById(R.id.in_box);
        cartButton.setOnClickListener(this);

        if (mProducts.size() == 0){
            Bundle bundle = getArguments();
            if (bundle != null) {
                mItem = bundle.getString(MainActivity.KEY_ITEM);
                if(!(mItem == null)){
                    doSomethingAsyncOperaion(Product.getParamsUrl(mItem), Сonstants.url_details_product,  TypeRequest.GET);
                }
            }
        }
        return  rootView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.in_box:
                showCustomAlertDialogEnterNumber();
                break;
            default:
                break;
        }
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker<JSONArray>(this, paramsUrl, url, typeRequest) {
        }.execute();
    }
    //Создаем открываем диалог (добавляем запись дату)
    private void  showCustomAlertDialogEnterNumber() {

        //получаем Inflater
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        //каст макет
        final View numberView = layoutInflater.inflate(R.layout.dialog_number, null);

        //поля из макета
        final Button numberMinus =  (Button) numberView.findViewById(R.id.dialog_number_minus);
        final Button numberPlus =  (Button) numberView.findViewById(R.id.dialog_number_plus);
        final TextView price =  (TextView) numberView.findViewById(R.id.dialog_number_price);
        number =  (TextView) numberView.findViewById(R.id.dialog_number_number);
        sum =  (TextView) numberView.findViewById(R.id.dialog_number_sum);

        number.setText("1");
        price.setText(String.valueOf(mProduct.getPrice()));
        sum.setText(String.valueOf(mProduct.getPrice()));

        final double[] numberD = {1.0};

        numberPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberD[0]++;
                number.setText(String.valueOf(numberD[0]));
                sum.setText(String.valueOf(mProduct.getPrice()* numberD[0]));
            }
        });

        numberMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( numberD[0] > 1){
                    numberD[0]--;
                    number.setText(String.valueOf(numberD[0]));
                    sum.setText(String.valueOf(mProduct.getPrice()* numberD[0]));
                }
            }
        });

        //открываем диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Добавить в корзину");
        builder.setIcon(R.drawable.logo);
        builder.setView(numberView);
        //кнопки
        builder .setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int id) {
                        double numberInDialog = Double.parseDouble(String.valueOf(number.getText()));
                        //заказ
                        Cart cart = new Cart(mProduct,  String.valueOf(getDate()), mProduct.getPrice(), numberInDialog, mProduct.getPrice() * numberInDialog);
                        Cart.setmCart(cart);
                        dialog.cancel();
                        mUpDataCartListener = (onUpDataCartListener) getActivity();
                        mUpDataCartListener.UpDataCart();


                    }
                })
                .setNegativeButton("Отмена", null);

        final AlertDialog alert = builder.create();
        alert.show();
    }
    // Получаем текущее дату системы
    // Возвращаем дату "текущюю дату"
    private Date getDate() {
        //текущая дата
        long curTime = System.currentTimeMillis();
        Date date = new Date(curTime);
        return date;
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {
            for (int i = 0; i < mPJSONArray.length(); i++) {
                mProduct =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), Product.class);
            }
            mProducts.add(mProduct);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(final Throwable t) {

    }

    @Override
    public void onEnd() {
        mItemAdapter.notifyDataSetChanged();
    }

    public interface onUpDataCartListener {
        public  void UpDataCart();
    }
}
