package com.sy.alex_library.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by alexFugui on 2017/1/3.
 * button 防抖
 * 用法:
 * 在switch前加    if (AntiShake.check(view.getId())) return;
 */

public class AntiShake {
    private static List<OneClickUtil> utils = new ArrayList<>();

    public static boolean check(Object o) {
        String flag = null;
        if (o == null)
            flag = Thread.currentThread().getStackTrace()[2].getMethodName();
        else
            flag = o.toString();
        for (OneClickUtil util : utils) {
            if (util.getMethodName().equals(flag)) {
                return util.check();
            }
        }
        OneClickUtil clickUtil = new OneClickUtil(flag);
        utils.add(clickUtil);
        return clickUtil.check();
    }

    public boolean check() {
        return check(null);
    }

    private static class OneClickUtil {
        private String methodName;
        //点击间隔时间
        static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime = 0;

        OneClickUtil(String methodName) {
            this.methodName = methodName;
        }

        String getMethodName() {
            return methodName;
        }

        boolean check() {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                return false;
            } else {
                return true;
            }
        }
    }
}
