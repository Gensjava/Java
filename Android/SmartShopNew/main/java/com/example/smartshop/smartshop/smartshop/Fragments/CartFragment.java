package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import ua.smartshop.Adapters.CartAdapter;
import ua.smartshop.Models.Cart;
import ua.smartshop.R;

public class CartFragment extends android.support.v4.app.Fragment implements
        View.OnClickListener
       {

    private onSomeEventListener someEventListener ;
    public static final String TEG_GART_TOTAL_FRAGMENT = "TEG_GART_TOTAL_FRAGMENT";
    public static final String ACTION_GART_FRAGMENT = "ACTION_GART_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        ListView lvCart = (ListView) rootView.findViewById(R.id.lvMain);

        if (Cart.getmCart().size() == 0 ){
            ((TextView)  rootView.findViewById(R.id.lvMain_text)).setText(getString(R.string.cart_in_empty));
        }

        CartAdapter adapterCart = new CartAdapter(getActivity(), R.layout.main_list, (java.util.ArrayList<Cart>) Cart.getmCart());
        lvCart.setAdapter(adapterCart);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cart_make_order:
                someEventListener = (onSomeEventListener) getActivity();
                someEventListener.someEvent(TEG_GART_TOTAL_FRAGMENT, null);
                break;
            default:
                break;
        }
    }
    public interface onSomeEventListener {
        public void someEvent(String view_id, String item_id);
    }
}
