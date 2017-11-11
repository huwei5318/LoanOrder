package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.OrderBean;
import com.sxw.loan.loanorder.moudle.PushReg;
import com.sxw.loan.loanorder.moudle.PushRet;
import com.sxw.loan.loanorder.moudle.PushUpdata;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-07-29.
 */

public class PushSettingActivity extends BaseActivity {
    private static final String TAG = "PushSettingActivity";
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.txtcity)
    TextView txtcity;
    @BindView(R.id.pushcity)
    RelativeLayout pushcity;
    @BindView(R.id.pushimage)
    ImageView pushimage;
    @BindView(R.id.ispush)
    RelativeLayout ispush;
    @BindView(R.id.pushseting)
    Button pushseting;
    private int userid;
    //城市选择返回码
    private static final int REQUEST_CODE_PICK_CITY = 233;
    private Integer pushfals = 1, pushid = -1;
    private String flag = "0";
    private String city = "push";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushsetting);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        loanpush(userid);
    }

    private void loanpush(Integer userid) {
        PushReg firstOrderDetails = new PushReg();
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.pushurl)
                .content(new Gson().toJson(firstOrderDetails))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: " + response);
                        PushRet pushRet = new Gson().fromJson(response, PushRet.class);
                        if (pushRet.getPush() != null) {
                            pushid = pushRet.getPush().getId();
                            city = pushRet.getPush().getCity();
                            flag = pushRet.getPush().getFlag();
                            txtcity.setText(city);
                            if (pushRet.getPush().getFlag().equals("0")) {
                                pushfals = 0;
                                pushimage.setBackgroundResource(R.mipmap.icon_on);
                            } else if (pushRet.getPush().getFlag().equals("1")) {
                                pushfals = 1;
                                pushimage.setBackgroundResource(R.mipmap.icon_off);
                            }
                        } else {
                            pushid = -1;
                            ToastUtils.showToastGravityCenter("还没有设置推送城市");
                        }

                    }

                });
    }

    @OnClick({R.id.image, R.id.pushcity, R.id.ispush, R.id.pushseting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                this.finish();
                break;
            case R.id.pushcity:
                startActivityForResult(new Intent(this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
                break;
            case R.id.ispush:
                if (pushfals == 1) {
                    flag = "0";
                    pushfals = 0;
                    pushimage.setBackgroundResource(R.mipmap.icon_on);

                } else if (pushfals == 0) {
                    flag = "1";
                    pushfals = 1;
                    pushimage.setBackgroundResource(R.mipmap.icon_off);
                }
                pushseting.setEnabled(true);
                pushseting.setBackgroundResource(R.drawable.buttonbg);
                pushseting.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.pushseting:
                loanpushupdate(city, userid, flag, pushid);
                break;
        }
    }

    private void loanpushupdate(final String city, Integer userid, final String flag, Integer pushid) {
        PushUpdata pushUpdata = new PushUpdata();
        if (!city.equals("push")) {
            pushUpdata.setCity(city);
        }

        pushUpdata.setUserId(userid);
        pushUpdata.setFlag(flag);
        if (pushid == -1) {

        } else {
            pushUpdata.setId(pushid);
        }
        Log.e(TAG, "loanorder: " + new Gson().toJson(pushUpdata));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.updatapushurl)
                .content(new Gson().toJson(pushUpdata))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: " + response);
                        OrderBean orderBean = new Gson().fromJson(response, OrderBean.class);
                        if (orderBean.getCode().equals("0")) {
                            ToastUtils.showToastGravityCenter(orderBean.getMsg());
                            PushSettingActivity.this.finish();
                            //极光设置标签
                            pushseting.setEnabled(false);
                            pushseting.setBackgroundResource(R.drawable.btnbg);
                            pushseting.setTextColor(getResources().getColor(R.color.black));
                            if (flag.equals("1")) {
                                Set<String> set = new HashSet<>();
                                set.add("不推送");//名字任意，可多添加几个
                                JPushInterface.setTags(PushSettingActivity.this, set, new TagAliasCallback() {
                                    @Override
                                    public void gotResult(int i, String s, Set<String> set) {

                                    }
                                });//设置标签
                            } else {
                                Set<String> set = new HashSet<>();
                                set.add(city);//名字任意，可多添加几个
                                JPushInterface.setTags(PushSettingActivity.this, set, new TagAliasCallback() {
                                    @Override
                                    public void gotResult(int i, String s, Set<String> set) {

                                    }
                                });//设置标签
                            }
                        } else {
                            ToastUtils.showToastGravityCenter(orderBean.getMsg());
                        }
                    }

                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String citys = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                txtcity.setText(citys);
                city = citys + "市";
                pushseting.setEnabled(true);
                pushseting.setBackgroundResource(R.drawable.buttonbg);
                pushseting.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }
}
