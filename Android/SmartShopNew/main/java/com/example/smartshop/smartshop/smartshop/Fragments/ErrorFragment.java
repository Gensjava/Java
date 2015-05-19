package ua.smartshop.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ua.smartshop.Activitys.MainActivity;
import ua.smartshop.R;

public class ErrorFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.no_conect, container,
                false);
        MainActivity.ui_bar.setVisibility(View.INVISIBLE);

        View errorUpdata = (View) rootView.findViewById(R.id.error_updata);
        errorUpdata.setOnClickListener(this);
        View errorSetings = (View) rootView.findViewById(R.id.error_setings);
        errorSetings.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.error_updata:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.error_setings:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            default:
                break;
        }
    }

}
