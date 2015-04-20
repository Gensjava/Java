package ua.smartshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import ua.smartshop.R;


/**
 * Created by Gens on 04.04.2015.
 */
public class MainContactFragment extends android.support.v4.app.Fragment  {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_contact, container,
                false);

        WebView webDescription = (WebView) rootView.findViewById(R.id.contact_web_view);
        WebSettings settings = webDescription.getSettings();
        // включаем поддержку JavaScript
        settings.setJavaScriptEnabled (true);
        settings.setDefaultTextEncodingName("UTF-8");
        webDescription.loadDataWithBaseURL(null, "<tr>\n" +
                "<td valign=\"top\">\n" +
                "<p><span style=\"font-size: 12pt;\" data-mce-mark=\"1\"><strong>&nbsp;Уважаемые клиенты, пожалуйста, обратите внимание, что весь представленный на сайте товар НЕ находится в нашем магазине по адресу Пушкинская 64<br /><br /><br /></strong></span></p>\n" +
                "<p>\n" +
                "<table style=\"width: 100%;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td width=\"50%\"><span style=\"font-size: 10pt;\" data-mce-mark=\"1\"><strong>Телефоны</strong></span></td>\n" +
                "<td><span style=\"font-size: 10pt;\" data-mce-mark=\"1\"><strong>График работы</strong></span></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td valign=\"top\">(048) 737-63-73<br /> (048) 794-11-77<br />(097) 388-21-21<br /> (063) 85-44-888<br /> (066) 60-888-68</td>\n" +
                "<td valign=\"top\"><span style=\"width: 40px; display: inline-block;\">Пн-Пт</span> - 10-19&nbsp; (выдача заказов до 19-30)<br /> <span style=\"width: 40px; display: inline-block;\">Cб</span> - 10-17 <br /><span style=\"width: 40px; display: inline-block;\">Вс</span> - Выходной</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td valign=\"top\"><br />\n" +
                "<p><img src=\"/images/online1.gif\" alt=\"\" align=\"left\" border=\"0\" />&nbsp;<span data-mce-mark=\"1\">50349489</span><br id=\"clr\" /><img src=\"/images/online1.gif\" alt=\"\" align=\"left\" border=\"0\" />&nbsp;<span data-mce-mark=\"1\">37300369</span></p>\n" +
                "<p><img src=\"/templates/smart/images/favicon_skype.png\" alt=\"\" align=\"left\" border=\"0\" />&nbsp;<a href=\"skype:smart-shop.com.ua?call\">smart-shop.com.ua</a></p>\n" +
                "<p><img src=\"/images/favicon/email.png\" alt=\"\" align=\"left\" border=\"0\" />&nbsp;\n" +
                " <script language='JavaScript' type='text/javascript'>\n" +
                " <!--\n" +
                " var prefix = 'm&#97;&#105;lt&#111;:';\n" +
                " var suffix = '';\n" +
                " var attribs = '';\n" +
                " var path = 'hr' + 'ef' + '=';\n" +
                " var addy41211 = '&#105;nf&#111;' + '&#64;';\n" +
                " addy41211 = addy41211 + 'sm&#97;rt-sh&#111;p' + '&#46;' + '&#117;&#97;';\n" +
                " document.write( '<a ' + path + '\\'' + prefix + addy41211 + suffix + '\\'' + attribs + '>' );\n" +
                " document.write( addy41211 );\n" +
                " document.write( '<\\/a>' );\n" +
                " //-->\n" +
                " </script><script language='JavaScript' type='text/javascript'>\n" +
                " <!--\n" +
                " document.write( '<span style=\\'display: none;\\'>' );\n" +
                " //-->\n" +
                " </script>Этот e-mail адрес защищен от спам-ботов, для его просмотра у Вас должен быть включен Javascript\n" +
                " <script language='JavaScript' type='text/javascript'>\n" +
                " <!--\n" +
                " document.write( '</' );\n" +
                " document.write( 'span>' );\n" +
                " //-->\n" +
                " </script></p>\n" +
                "</td>\n" +
                "<td valign=\"top\">\n" +
                "<div xmlns:v=\"http://rdf.data-vocabulary.org/#\" typeof=\"v:Organization\" id=\"rdf_cont\"><span style=\"display: none;\" data-mce-mark=\"1\">Наш магазин <span property=\"v:name\" data-mce-mark=\"1\">SMART-Shop</span> находится по адресу:</span>\n" +
                "<div rel=\"v:address\">\n" +
                "<div typeof=\"v:Address\"><b>Адрес:</b><br /> <span property=\"v:locality\" data-mce-mark=\"1\">г. Одесса</span>, <span property=\"v:street-address\" data-mce-mark=\"1\">ул. Пушкинская 64</span>. <br /> <span style=\"font-size: 11px;\" data-mce-mark=\"1\"><i>(между Малой и Большой Арнаутской, по левую сторону)</i></span></div>\n" +
                "</div>\n" +
                "<br /><br />\n" +
                "<div rel=\"v:geo\"><span typeof=\"v:Geo\" data-mce-mark=\"1\"><b>GPS координаты:</b><br /> <span property=\"v:latitude\" content=\"46.471946\" data-mce-mark=\"1\">N46.471946</span> : <span property=\"v:longitude\" content=\"30.741029\" data-mce-mark=\"1\">E30.741029</span> </span></div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</p>\n" +
                "<p style=\"text-align: justify;\" align=\"center\">&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<div class=\"hideWr\"><a class=\"hideBt\" href=\"#\">Как добраться на общественном транспорте?</a>\n" +
                "<div class=\"hideCon\">Возле нашего магазина проходят:<br /> Маршрутка: 105, 124, 145, 148, 150, 175, 200, 201, 208, 210, 221, 233<br /> Троллейбус: 1, 3, 10<br /> Автобус: 91, 128, 200а</div>\n" +
                "</div>\n" +
                "<p><br /><br /></p>\n" +
                "<p align=\"center\"><b>Схема проезда</b></p>\n" +
                "<p><b>\n" +
                "<table style=\"width: 100%;\" align=\"center\" border=\"0\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td width=\"50%\"></td>\n" +
                "<td></td>\n" +
                "<td width=\"50%\"></td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</b></p>\n" +
                "<p><b>&nbsp;</b></p>\n" +
                "<p align=\"center\"><b><b>Будем рады видеть Вас клиентами нашего магазина.</b></b></p>\n" +
                "<p><b>&nbsp;</b></p>\n" +
                "<div><b>&nbsp;</b></div>\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "</td>\n" +
                "</tr>\n", "text/html", "en_US", null);

        return rootView;
    }
}
