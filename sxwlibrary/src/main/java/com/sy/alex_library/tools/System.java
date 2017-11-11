package com.sy.alex_library.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

/**
 * Created by Administrator on 2015-04-30.
 */
public class System {
    private static final String TAG = "System Class";
    /**
     * 网络是否可用
     *
     * @param context
     * @return 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取SDCard的目录路径功能
     *
     * @return 返回"",SDCard不存在;否则，返回目录路径
     */
    public static String getSDCardPath() {
        String sdcardDir = "";
        // 判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return sdcardDir;
    }
}
