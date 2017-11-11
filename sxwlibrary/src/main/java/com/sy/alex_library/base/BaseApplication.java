package com.sy.alex_library.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by Administrator on 2017/3/2.
 */

public abstract class BaseApplication extends Application {
    public Context appContext;

    public Context getAppContext(){
        appContext = getApplicationContext();
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        AutoLayoutConifg.getInstance().useDeviceSize();
        Fresco.initialize(this);
        initLibrary();
    }
    /**
     * Library初始化
     */
    protected abstract void initLibrary();
}
