package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import ua.smartshop.Adapters.OrderAdapter;
import ua.smartshop.Models.Cart;
import ua.smartshop.R;


/**
 * Created by Gens on 10.03.2015.
 */
public class OrderMakeFragment extends android.support.v4.app.Fragment {
    View rootView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       
        rootView = inflater.inflate(R.layout.order, container,
                false);
        ListView lvCart = (ListView) rootView.findViewById(R.id.order_listView);

        OrderAdapter adapterOrder = new OrderAdapter(getActivity(), R.layout.order,  (java.util.ArrayList<Cart>) Cart.getmCart());
        lvCart.setAdapter(adapterOrder);

        ((TextView) rootView.findViewById(R.id.order_item_total)).setText(String.valueOf(Cart.getTotalSum()));

        Delivery();
        
        return rootView;
    }
    void Delivery(){
        
        //доставка
        RadioGroup radiogroupDelivery = (RadioGroup) rootView.findViewById(R.id.order_delivery_radioGroup);

        radiogroupDelivery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioPickup = (RadioButton) rootView.findViewById(R.id.order_pickup);
                RadioButton radioDelivery = (RadioButton) rootView.findViewById(R.id.order_delivery_city);
                TextView editAddress = (TextView) rootView.findViewById(R.id.order_delivery_address);

                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.order_odessa_radio:
                        radioPickup.setText("Самовывоз");
                        radioDelivery.setText("Доставка по адресу");
                        break;
                    case R.id.order_ukruine_radio:
                        radioPickup.setText("Самовывоз с курьерской службы");
                        radioDelivery.setText("Доставка по адресу");
                        break;
                    default:
                        break;
                }
            }
        });
        
        //вид доставка
        RadioGroup radiogroupType = (RadioGroup) rootView.findViewById(R.id.order_type_delivery_radioGroup);

        radiogroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                TextView editAddress = (TextView) rootView.findViewById(R.id.order_delivery_address);
                TextView editAddressText = (TextView) rootView.findViewById(R.id.order_delivery_address_text);

                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.order_pickup:
                        editAddress.setVisibility(View.INVISIBLE);
                        editAddressText.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.order_delivery_city:
                        editAddress.setVisibility(View.VISIBLE);
                        editAddressText.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });
        
    }
}
