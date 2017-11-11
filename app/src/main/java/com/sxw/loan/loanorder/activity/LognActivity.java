package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.MainActivity;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.LoginRen;
import com.sxw.loan.loanorder.moudle.User;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.EditTools;
import com.sy.alex_library.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017-04-12.
 */

public class LognActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.mine_head)
    SimpleDraweeView mineHead;
    @BindView(R.id.mine_logn)
    LinearLayout mineLogn;
    @BindView(R.id.reg_username)
    EditText regUsername;
    @BindView(R.id.reg_userpass)
    EditText regUserpass;
    @BindView(R.id.but_forgetpass_toSetCodes)
    Button butForgetpassToSetCodes;
    @BindView(R.id.reg)
    Button reg;
    public static final String TAG ="logon";
    @BindView(R.id.forget)
    Button forget;
    private String isinsert = "0";//判断实名条件
    private String jpushid;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.lognactivity);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }


    @OnClick({R.id.image, R.id.but_forgetpass_toSetCodes, R.id.reg, R.id.forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                this.finish();
                break;
            case R.id.forget:
                startActivity(ForgotPassActivity.class);
                break;
            case R.id.but_forgetpass_toSetCodes:
                if (EditTools.checkEmpty(this, regUsername, "请输入手机号码")) return;
                if (EditTools.checkEmpty(this, regUserpass, "请输入密码")) return;
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.login)
                        .content(new Gson().toJson(new User(regUsername.getText().toString(), regUserpass.getText().toString())))
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
                                        editor.putString("pass", regUserpass.getText().toString());
                                        editor.putInt("amount", regReturn.getUser().getAmount());
                                        Log.e("sdasdasd", "没有实名");
                                        Log.e(TAG, "onResponse: " + regReturn.getUser().getFlag());
                                        //提交当前数据
                                        editor.apply();
                                        userid = regReturn.getUser().getId();
                                        ToastUtils.showToastGravityCenter("登录成功");
                                        startActivity(MainActivity.class);
                                        LognActivity.this.finish();
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
                                        editor.putString("pass", regUserpass.getText().toString());
                                        editor.putInt("amount", regReturn.getUser().getAmount());
                                        //提交当前数据
                                        editor.apply();
                                        Log.e(TAG, "onResponse: " + regReturn.getUser().getFlag());
                                        userid = regReturn.getUser().getId();
                                        Log.e("asdasdasdasdasd", regReturn.getUser().getId() + "");
                                        ToastUtils.showToastGravityCenter("登录成功");
                                        startActivity(MainActivity.class);
                                        LognActivity.this.finish();
                                    }
                                } else {
                                    ToastUtils.showToastGravityCenter("手机号或密码错误");
                                }
                            }
                        });
                break;
            case R.id.reg:
                this.finish();
                startActivity(RegAvtivity.class);
                break;
        }
    }
}
