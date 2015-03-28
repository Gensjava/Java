package com.example.smartshop.smartshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gens on 03.03.2015.
 */
public class ProducttItemFragmen extends android.support.v4.app.Fragment implements View.OnClickListener  {

    private ArrayList<Product> mMroducts = new ArrayList<Product>();
    private List<HashMap> mArrayValues;
    private Product product;
    TextView number,sum;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.product_list, container,
                false);
        
        if (mMroducts.size() == 0){
            Bundle bundle = getArguments();
            if (bundle != null) {
                mArrayValues = (List<HashMap>) bundle.getSerializable("imageView_mValues");
                if(!(mArrayValues == null)){
                    GetProductDetailsTask();  
                }             
            }
        }
        //
        ProductItemAdapter itemAdapter = new ProductItemAdapter(getActivity(), mMroducts);
        // настраиваем список
        ListView lvMain = (ListView) rootView.findViewById(R.id.listItem);
        lvMain.setAdapter(itemAdapter);

        Button cartButton = (Button) rootView.findViewById(R.id.in_box);
        cartButton.setOnClickListener(this);

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

    // получения информации о товаре
    void GetProductDetailsTask () {

         HashMap<String, String> mValues;

        for (int i = 0; i < mArrayValues.size(); i++) {

            mValues = mArrayValues.get(i);

            product = new Product( mValues.get("name"),
                    mValues.get("description") ,
                    mValues.get("idItem"),
                    mValues.get("kod"),
                    Double.parseDouble(mValues.get("price")),
                    Сonstants.url_main_way_image + mValues.get("wayimage"));
            mMroducts.add(product);
        }
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
        price.setText(String.valueOf(product.getPrice()));
        sum.setText(String.valueOf(product.getPrice()));

        final double[] numberD = {1.0};
        
        numberPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberD[0]++;
                number.setText(String.valueOf(numberD[0]));
                sum.setText(String.valueOf(product.getPrice()* numberD[0]));
            }
        });

        numberMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( numberD[0] > 1){
                    numberD[0]--;
                    number.setText(String.valueOf(numberD[0]));
                    sum.setText(String.valueOf(product.getPrice()* numberD[0]));
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
                        Cart cart = new Cart(product, Сonstants.profile, String.valueOf(getDate()), product.getPrice(), numberInDialog, product.getPrice() * numberInDialog);
                        Cart.setmCart(cart);
                        dialog.cancel();
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

}
