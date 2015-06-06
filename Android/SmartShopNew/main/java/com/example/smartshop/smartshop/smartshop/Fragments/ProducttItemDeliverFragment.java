package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import ua.smartshop.R;

public class ProducttItemDeliverFragment extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_contact, container,
                false);

        WebView webDescription = (WebView) rootView.findViewById(R.id.contact_web_view);
        WebSettings settings = webDescription.getSettings();
        // включаем поддержку JavaScript
        settings.setJavaScriptEnabled (true);
        settings.setDefaultTextEncodingName("UTF-8");
        webDescription.loadUrl("file:///android_res/raw/delivery.html");
        return rootView;
    }
}
