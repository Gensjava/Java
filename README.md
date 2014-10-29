package com.example.gens.myapplication_calc_dynamic;

        import android.app.Activity;
        import android.media.Rating;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.GridLayout;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.Switch;
        import android.widget.TextView;

        import java.util.ArrayList;


public class MyActivity extends Activity  {

    private static final int COLUMNS_TO_SPAN = 4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Создаем параметры макета LinearLayout
        LinearLayout.LayoutParams ParamsLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Создаем  макет LinearLayout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //Добавляем макет linearLayout
        this.addContentView(linearLayout, ParamsLayout);

        //Создаем параметры макета GridLayout
        final GridLayout.Spec rowSpec = GridLayout.spec(0);
        final GridLayout.Spec colSpec = GridLayout.spec(0, COLUMNS_TO_SPAN);
        final GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);

        //Создаем  макет GridLayout
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(4);
        gridLayout.setRowCount(5);

        //Добавляем макет gridLayout
        linearLayout.addView(gridLayout,layoutParams);

        //Создаем  макет editText
        EditText editText = new EditText(this);
        editText.setText("123456789");
        editText.setHeight(50);
        editText.setWidth(400);

        //Добавляем editText в gridLayout
        gridLayout.addView(editText,layoutParams);

        String btText = null;
        //Создаем кнопки для калькулятора
        for (int i = 1; i <= 20;i++){

         Button button = new Button(this);
            //размеры
            button.setHeight(30);
            button.setWidth(30);
            //установка текста

            switch (i){
                case 4:
                    btText = "<-";
                    break;
                case 8:
                    btText = "+";
                    break;
                case 12:
                    btText = "-";
                    break;
                case 16:
                    btText = "*";
                    break;
                case 20:
                    btText = "/";
                    break;
                case 15:
                    btText = "%";
                    break;
                case 19:
                    btText = "=";
                    break;
                case 17:
                    btText = "C";
                    break;
                case 18:
                    btText = "CE";
                    break;
                case 5:
                    btText = "4";
                    break;
                case 6:
                    btText = "5";
                    break;
                case 7:
                    btText = "6";
                    break;
                case 9:
                    btText = "7";
                    break;
                case 10:
                    btText = "8";
                    break;
                case 11:
                    btText = "9";
                    break;
                case 13:
                    btText = "0";
                    break;
                case 14:
                    btText = ".";
                    break;
                default:
                    btText = ""+i;
                    break;
            }
            //прописываем текст кнопки
            button.setText(""+btText);

            //добавляем на макет  gridLayout
            gridLayout.addView(button);
        }
    }

}
