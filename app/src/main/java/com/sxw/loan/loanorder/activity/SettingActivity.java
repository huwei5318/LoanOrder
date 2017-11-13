package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivitySettingBinding;
import com.sxw.loan.loanorder.ui.IPDialog;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.ToastUtils;

/**
 * Created by Administrator on 2017-05-08.
 */

public class SettingActivity extends BaseActivity<ActivitySettingBinding> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        showContentView();
        setTitle("设置");
        setListener();


    }

    private void setListener() {
        bindingView.btnRepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ForgotPassActivity.class);
                finish();
            }
        });

        bindingView.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPDialog.Builder builder = new IPDialog.Builder(SettingActivity.this);
                builder.setTitle("确定退出?")
                        .setConfirmButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        }).setCancelButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                                Activity.MODE_PRIVATE);
                        //实例化SharedPreferences.Editor对象
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        SettingActivity.this.finish();
                        ToastUtils.showToastGravityCenter("退出登录成功!");
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });
    }


}
