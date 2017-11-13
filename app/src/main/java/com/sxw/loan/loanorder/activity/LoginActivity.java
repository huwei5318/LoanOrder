package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.MainActivity;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivityLoginBinding;
import com.sxw.loan.loanorder.moudle.LoginRen;
import com.sxw.loan.loanorder.moudle.User;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.EditTools;
import com.sy.alex_library.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017-04-12.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {


    public static final String TAG ="LoginActivity";
    private String isinsert = "0";//判断实名条件
    private String jpushid;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_login);

        showContentView();

        setTitle("登录");


        setListener();
    }

    private void setListener() {
        bindingView.forget.setOnClickListener(this);
        bindingView.butForgetpassToSetCodes.setOnClickListener(this);
        bindingView.reg.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.forget:
                startActivity(ForgotPassActivity.class);
                break;
            case R.id.but_forgetpass_toSetCodes:
                if (EditTools.checkEmpty(this, bindingView.regUsername, "请输入手机号码")) return;
                if (EditTools.checkEmpty(this, bindingView.regUserpass, "请输入密码")) return;
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.login)
                        .content(new Gson().toJson(new User(bindingView.regUsername.getText().toString(), bindingView.regUserpass.getText().toString())))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("aaaaa", e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaaaa", response.toString());
                                Gson gson1 = new Gson();
                                LoginRen regReturn = gson1.fromJson(response.toString(), LoginRen.class);
                                if (regReturn.getCode().equals("0")) {
                                    Log.e("aaaaa", String.valueOf(regReturn.getUser().getFlag()));
                                    if (isinsert.equals(regReturn.getUser().getFlag())) {
                                        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                                                Activity.MODE_PRIVATE);
                                        //实例化SharedPreferences.Editor对象
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        //用putString的方法保存数据
                                        editor.putString("isinsert", String.valueOf(regReturn.getUser().getFlag()));
                                        editor.putInt("userid", regReturn.getUser().getId());
                                        editor.putString("name", regReturn.getUser().getPhone());
                                        editor.putString("phone", regReturn.getUser().getPhone());
                                        editor.putString("pass", bindingView.regUserpass.getText().toString());
                                        editor.putInt("amount", regReturn.getUser().getAmount());
                                        Log.e("sdasdasd", "没有实名");
                                        Log.e(TAG, "onResponse: " + regReturn.getUser().getFlag());
                                        //提交当前数据
                                        editor.apply();
                                        userid = regReturn.getUser().getId();
                                        ToastUtils.showToastGravityCenter("登录成功");
                                        startActivity(MainActivity.class);
                                        LoginActivity.this.finish();
                                    } else {
                                        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                                                Activity.MODE_PRIVATE);
                                        //实例化SharedPreferences.Editor对象
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        //用putString的方法保存数据
                                        editor.putString("isinsert", String.valueOf(regReturn.getUser().getFlag()));
                                        editor.putInt("userid", regReturn.getUser().getId());
                                        editor.putString("name", regReturn.getUser().getName());
                                        editor.putString("phone", regReturn.getUser().getPhone());
                                        editor.putInt("total", regReturn.getUser().getIntegralSum());
                                        editor.putString("pass", bindingView.regUserpass.getText().toString());
                                        editor.putInt("amount", regReturn.getUser().getAmount());
                                        //提交当前数据
                                        editor.apply();
                                        Log.e(TAG, "onResponse: " + regReturn.getUser().getFlag());
                                        userid = regReturn.getUser().getId();
                                        Log.e("asdasdasdasdasd", regReturn.getUser().getId() + "");
                                        ToastUtils.showToastGravityCenter("登录成功");
                                        startActivity(MainActivity.class);
                                        LoginActivity.this.finish();
                                    }
                                } else {
                                    ToastUtils.showToastGravityCenter("手机号或密码错误");
                                }
                            }
                        });
                break;
            case R.id.reg:
                this.finish();
                startActivity(RegisterActivity.class);
                break;
        }

    }


}
