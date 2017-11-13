package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.MainActivity;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivityRegisterBinding;
import com.sxw.loan.loanorder.moudle.IsPhone;
import com.sxw.loan.loanorder.moudle.LoginRen;
import com.sxw.loan.loanorder.moudle.PhoneCode;
import com.sxw.loan.loanorder.moudle.User;
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
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017-05-31.
 */

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {
    private String phonecode;

    private String isinsert = "0";//判断实名条件
    private String jpushid;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        showContentView();
        setTitle("注册");

        setListener();
    }

    private void setListener() {
        bindingView.ivShowCode.setOnClickListener(this);
        bindingView.regReg.setOnClickListener(this);
        bindingView.regAgreement.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.reg_agreement:
//                startActivity(AgreementActivity.class);
                break;
            case R.id.iv_showCode:
                if (EditTools.checkEmpty(this, bindingView.regUsername, "请输入手机号码")) return;
                Map<String, String> map = new HashMap<>();
                map.put("phone", bindingView.regUsername.getText().toString());
                final JSONObject json = new JSONObject(map);
                final Gson gson = new Gson();
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.isphone)
                        .content(String.valueOf(json))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("isphone", e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                //  RegisterActivity.this.finish();
                                Log.e("isphone", response.toString());
                                IsPhone isPhone = gson.fromJson(response.toString(), IsPhone.class);
                                Log.e("code", isPhone.getCode());
                                if (isPhone.getCode().equals("0")) {
                                    ToastUtils.showToastGravityCenter("该号码已经注册,请更换!");
                                }
                                if (isPhone.getCode().equals("1")) {
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
                                                        ToastUtils.showToastGravityCenter("请重新获取");
                                                    } else if (phonecode.equals("1")) {
                                                        ToastUtils.showToastGravityCenter("10分钟后再试!");
                                                    }

                                                }
                                            });
                                }
                            }
                        });
                break;
            case R.id.reg_reg:
                if (EditTools.checkEmpty(this, bindingView.regUsername, "请输入手机号码")) return;
                if (EditTools.checkEmpty(this, bindingView.regUserpass, "请输入密码")) return;
                if (EditTools.checkEmpty(this, bindingView.regUserpassAgain, "请再次输入密码")) return;
                if (bindingView.regUserpass.getText().length() < 6 && bindingView.regUserpassAgain.getText().length() < 6) {
                    ToastUtils.showToastGravityCenter("密码必须6位以上");
                    return;
                }

                if (bindingView.regUserpass.getText().toString().equals(bindingView.regUserpassAgain.getText().toString())) {
                    OkHttpUtils
                            .postString()
                            .url(ConstantUrl.reg)
                            .content(new Gson().toJson(new UserREG(bindingView.regUsername.getText().toString(), bindingView.regUserpass.getText().toString(), bindingView.etPhoneCodes.getText().toString())))
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("register", e.toString());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("register", response.toString());
                                    Gson gson1 = new Gson();
                                    LoginRen regReturn = gson1.fromJson(response.toString(), LoginRen.class);
                                    if (regReturn.getCode().equals("0")) {
                                        OkHttpUtils
                                                .postString()
                                                .url(ConstantUrl.login)
                                                .content(new Gson().toJson(new User(bindingView.regUsername.getText().toString(), bindingView.regUserpass.getText().toString())))
                                                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                                .build()
                                                .execute(new StringCallback() {
                                                    @Override
                                                    public void onError(Call call, Exception e, int id) {
                                                        Log.e("reg", e.toString());
                                                    }

                                                    @Override
                                                    public void onResponse(String response, int id) {
                                                        Log.e("reg", response.toString());
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
                                                                Log.e("sdasdasd", "没有实名");
                                                                JPushInterface.setAlias(RegisterActivity.this, String.valueOf(regReturn.getUser().getId()), new TagAliasCallback() {
                                                                    @Override
                                                                    public void gotResult(int i, String s, Set<String> set) {
                                                                        Log.e("tag", i + "");
                                                                    }
                                                                });//设置标签
                                                                //提交当前数据
                                                                editor.apply();
                                                                userid = regReturn.getUser().getId();
                                                                ToastUtils.showToastGravityCenter("注册且登录成功");
                                                                startActivity(MainActivity.class);
                                                                RegisterActivity.this.finish();
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
                                                                //提交当前数据
                                                                editor.apply();
                                                                //设置标签
                                                                userid = regReturn.getUser().getId();
                                                                Log.e("asdasdasdasdasd", regReturn.getUser().getId() + "");
                                                                ToastUtils.showToastGravityCenter("注册且登录成功");
                                                                JPushInterface.setAlias(RegisterActivity.this, String.valueOf(regReturn.getUser().getId()), new TagAliasCallback() {
                                                                    @Override
                                                                    public void gotResult(int i, String s, Set<String> set) {
                                                                        Log.e("tag", i + "");
                                                                    }
                                                                });//设置标签
                                                                startActivity(MainActivity.class);
                                                                RegisterActivity.this.finish();
                                                            }
                                                        } else {
                                                            ToastUtils.showToastGravityCenter("手机号或密码错误");
                                                        }
                                                    }
                                                });
                                    } else {
                                        ToastUtils.showToastGravityCenter("注册失败");
                                    }
                                }
                            });
                } else {
                    ToastUtils.showToastGravityCenter("两次密码输入不一致");
                }
                break;
        }
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            bindingView.ivShowCode.setText((int) (l / 1000) + "s");
            bindingView.ivShowCode.setClickable(false);
        }

        @Override
        public void onFinish() {
            bindingView.ivShowCode.setClickable(true);
            bindingView.ivShowCode.setText("发送验证码");
        }
    };

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
