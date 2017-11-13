package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivityForgotpassBinding;
import com.sxw.loan.loanorder.moudle.LoginRen;
import com.sxw.loan.loanorder.moudle.PhoneCode;
import com.sxw.loan.loanorder.moudle.UserREG;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.EditTools;
import com.sy.alex_library.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

import static com.sxw.loan.loanorder.R.id.showCode;

/**
 * Created by Sxw on 2017-06-23.
 */

public class ForgotPassActivity extends BaseActivity<ActivityForgotpassBinding> {

    private String phonecode;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        showContentView();
        setTitle("修改密码");

        setListener();

        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);

    }

    private void setListener() {
        bindingView.showCode.setOnClickListener(this);
        bindingView.xiugai.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case showCode:
                //发送验证码
                if (EditTools.checkEmpty(this, bindingView.username, "请输入手机号码")) return;

                Map<String, String> map = new HashMap<>();
                map.put("phone", bindingView.username.getText().toString());
                final JSONObject json = new JSONObject(map);
                final Gson gson = new Gson();
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.code)
                        .content(String.valueOf(json))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("code", e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                //  RegisterActivity.this.finish();
                                Log.e("code", response.toString());
                                PhoneCode phoneCode = gson.fromJson(response.toString(), PhoneCode.class);
                                phonecode = phoneCode.getCode();
                                Log.e("asdasdasd", phonecode);
                                if (phoneCode.getCode().equals("0")) {

                                    timer.start();
                                } else if (phonecode.equals("2")) {
                                    ToastUtils.showToastGravityCenter("请重新获取!");
                                } else if (phonecode.equals("1")) {
                                    ToastUtils.showToastGravityCenter("10分钟后再试!");
                                }

                            }
                        });
                break;
            case R.id.xiugai:
                if (EditTools.checkEmpty(this, bindingView.userpass, "请输入新密码")) return;
                if (EditTools.checkEmpty(this, bindingView.userpassAgain, "请再次输入新密码")) return;
                if (bindingView.userpass.getText().length() < 6 && bindingView.userpassAgain.getText().length() < 6) {
                    ToastUtils.showToastGravityCenter("密码必须6位以上");
                    return;
                }

                if (bindingView.userpass.getText().toString().equals(bindingView.userpassAgain.getText().toString())) {
                    OkHttpUtils
                            .postString()
                            .url(ConstantUrl.forgotpassurl)
                            .content(new Gson().toJson(new UserREG(bindingView.username.getText().toString(), bindingView.userpass.getText().toString(), bindingView.phoneCodes.getText().toString())))
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("repassword", e.toString());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("repassword", response.toString());
                                    Gson gson1 = new Gson();
                                    LoginRen regReturn = gson1.fromJson(response.toString(), LoginRen.class);
                                    if (regReturn.getCode().equals("0")) {
                                        //实例化SharedPreferences.Editor对象
                                        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                                                Activity.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.clear();
                                        editor.commit();
                                        ToastUtils.showToastGravityCenter("修改密码成功,请重新登录");
                                        ForgotPassActivity.this.finish();
                                    }
                                }
                            });
                } else {
                    ToastUtils.showToastGravityCenter("两次密码输入不一致");

                }
                //提交修改
                break;
        }
    }



    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            bindingView.showCode.setText((int) (l / 1000) + "s");
            bindingView.showCode.setClickable(false);
        }

        @Override
        public void onFinish() {
            bindingView.showCode.setClickable(true);
            bindingView.showCode.setText("发送验证码");
        }
    };
}
