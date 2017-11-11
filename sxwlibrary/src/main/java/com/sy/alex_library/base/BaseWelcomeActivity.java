package com.sy.alex_library.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static com.sy.alex_library.tools.ToastUtils.showToast;

/**
 * Created by AlexFugui on 2017/3/22.
 */

public abstract class BaseWelcomeActivity extends BaseAutoLayoutActivity {
    private static final String[] permissionsArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE};
    //还需申请的权限列表
    private List<String> permissionsList = new ArrayList<String>();
    //申请权限后的返回码
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        setContentView(getContentViewLayoutID());
        initViewAndDatas();
        BaseAppManager.getInstance().addActivity(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        Collections.addAll(permissionsList, permissionsArray);
        checkRequiredPermission(this, getPermissionsArray());
    }

    /**
     * add Permissions
     * <p>
     * 日历   android.permission.READ_CALENDAR
     * 相机   android.permission.CAMERA
     * 位置   android.permission.GET_ACCOUNTS
     * 麦克风 android.permission.RECORD_AUDIO
     * 储存   android.permission.READ_EXTERNAL_STORAGE
     * 信息   android.permission.READ_SMS
     * 手机状态及电话
     * android.permission.CALL_PHONE
     */
    protected abstract List<String> getPermissionsArray();

    /**
     * 初始化ui控件和跳转
     */
    protected abstract void initViewAndDatas();

    /**
     * setContentView(R.layout...);
     */
    protected abstract int getContentViewLayoutID();

    private void checkRequiredPermission(final Activity activity, List<String> permissionsArray) {
        for (String permission : permissionsArray) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_PERMISSIONS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, "做一些申请成功的权限对应的事！" + permissions[i], Toast.LENGTH_SHORT).show();
                        showToast("申请权限成功");
                    } else {
//                        Toast.makeText(this, "权限被拒绝： " + permissions[i], Toast.LENGTH_SHORT).show();
                        showToast("权限被拒绝");
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void autoStartActivity(int time, final Context context, final Class<?> clazz) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, clazz);
                startActivity(intent);
                finish();
            }
        }, time);
    }

}
