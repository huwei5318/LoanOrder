package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-06-23.
 */

public class ForgotPassActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.userpass)
    EditText userpass;
    @BindView(R.id.userpassAgain)
    EditText userpassAgain;
    @BindView(R.id.phoneCodes)
    EditText phoneCodes;
    @BindView(R.id.showCode)
    AppCompatButton showCode;
    @BindView(R.id.xiugai)
    Button xiugai;
    private String phonecode;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotactivity);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);

    }

    @OnClick({R.id.back, R.id.showCode, R.id.xiugai})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.showCode:
                //发送验证码
                if (EditTools.checkEmpty(this, username, "请输入手机号码")) return;

                Map<String, String> map = new HashMap<>();
                map.put("phone", username.getText().toString());
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
                                //  RegAvtivity.this.finish();
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
                if (EditTools.checkEmpty(this, userpass, "请输入新密码")) return;
                if (EditTools.checkEmpty(this, userpassAgain, "请再次输入新密码")) return;
                if (userpass.getText().length() < 6 && userpassAgain.getText().length() < 6) {
                    ToastUtils.showToastGravityCenter("密码必须6位以上");
                    return;
                }

                if (userpass.getText().toString().equals(userpassAgain.getText().toString())) {
                    OkHttpUtils
                            .postString()
                            .url(ConstantUrl.forgotpassurl)
                            .content(new Gson().toJson(new UserREG(username.getText().toString(), userpass.getText().toString(), phoneCodes.getText().toString())))
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
            showCode.setText((int) (l / 1000) + "s");
            showCode.setClickable(false);
        }

        @Override
        public void onFinish() {
            showCode.setClickable(true);
            showCode.setText("发送验证码");
        }
    };
}
