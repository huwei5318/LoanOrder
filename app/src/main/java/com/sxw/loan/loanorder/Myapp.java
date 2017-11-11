package com.sxw.loan.loanorder;


import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sy.alex_library.base.BaseApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Sxw on 2017-06-11.
 */

public class Myapp extends BaseApplication {

    @Override
    protected void initLibrary() {
        Fresco.initialize(this);
        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
