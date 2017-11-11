package com.sy.alex_library.tools;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AlexFugui on 2016/11/28.
 */

public class EditTools {
    public static boolean checkEmpty(Context context, EditText et, String str) {
        if (et.getText().toString().equals("")) {
            et.clearFocus();
            et.requestFocus();
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean checktextEmpty(Context context, TextView txt, String str) {
        if (txt.getText().toString().equals("")) {
            txt.clearFocus();
            txt.requestFocus();
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static Number toNumber(EditText et) {
        return Integer.parseInt(et.getText().toString());
    }
    //限制edit输入小数点后两位
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }

}
