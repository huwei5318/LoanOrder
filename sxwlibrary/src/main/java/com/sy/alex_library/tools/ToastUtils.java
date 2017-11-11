package com.sy.alex_library.tools;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.widget.Toast;

import com.sy.alex_library.base.BaseAppManager;


public class ToastUtils {

    private static Toast sToast = null;

    public static void showToast(String context) {
        sToast = Toast.makeText(BaseAppManager.getInstance().getTopActivity().getApplicationContext(), context, Toast.LENGTH_SHORT);
        sToast.show();
    }
    public static void showToastGravityCenter(String content) {
        setToastGravity(content, Toast.LENGTH_SHORT, Gravity.CENTER, 0, 0).show();
    }

    @SuppressLint("ShowToast")
    private static Toast setToastGravity(String content, int duration, int position, int xOffset, int yOffset) {
        if (sToast == null) {
            sToast = Toast.makeText(BaseAppManager.getInstance().getTopActivity().getApplicationContext(), content, duration);
        } else {
            sToast.setText(content);
            sToast.setDuration(duration);
        }
        sToast.setGravity(position, xOffset, yOffset);
        return sToast;
    }
}
