package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ua.smartshop.R;

/**
 * Created by Gens on 27.03.2015.
 */
public class OrderFinishFragment  extends android.support.v4.app.Fragment  {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.order_finish, container,
                false);


        View button = rootView.findViewById(R.id.pay_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onOrderSave();
            }
        });

        return rootView;
    }
    private boolean onOrderSave(){
        boolean mOrder = false;

        Toast.makeText(getActivity(), "Ваши заказы приняты!"
                , Toast.LENGTH_LONG).show();
        return mOrder;

    }
}
