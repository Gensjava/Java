package ua.smartshop.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import ua.smartshop.R;

/**
 * Created by Gens on 19.03.2015.
 */
public class ErrorInfo {

    //диалог для ошибок
    public static void showErrorAlertDialog(String errMessage, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error:")
                .setMessage(errMessage)
                .setIcon(R.drawable.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    //Проверка полей Edit
    public static boolean fieldValidationRegistration(final EditText[] mArrEditm){
        boolean check = false;

        for (byte i = 0; i < mArrEditm.length;i++){
            EditText edit = mArrEditm[i];

            if( edit.getText().toString().length() == 0 ){
                edit.setError( "Поле не должно быть пустым!" );
                check = true;
            }else{
                edit.setError(null);
            }
        }
        return  check;
    }
}
