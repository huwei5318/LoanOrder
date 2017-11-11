package com.sy.alex_library.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/12/23.
 */

public class TimeUnitl {
    public static String getTimeDifference(String regTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            Date d1 = df.parse(regTime);
            Date d2 = new Date(java.lang.System.currentTimeMillis());//你也可以获取当前时间
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            return days + "天";
        } catch (Exception e) {
            Log.e("getTimeDifference", e.getLocalizedMessage());
            return "0天";
        }
    }
}
