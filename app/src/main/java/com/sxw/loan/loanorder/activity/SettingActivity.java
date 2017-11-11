package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.ui.IPDialog;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.ToastUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-05-08.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.btn_repass)
    Button btnRepass;
    @BindView(R.id.btn_back)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);

    }

    @OnClick({R.id.image, R.id.btn_repass, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                SettingActivity.this.finish();
                break;
            case R.id.btn_repass:
                startActivity(ForgotPassActivity.class);
                this.finish();
                break;
            case R.id.btn_back:
                IPDialog.Builder builder = new IPDialog.Builder(this);
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
                break;
        }
    }
}
