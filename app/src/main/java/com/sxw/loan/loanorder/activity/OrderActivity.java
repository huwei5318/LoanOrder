package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.MainActivity;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.BtTxQdReg;
import com.sxw.loan.loanorder.moudle.FirstDetailsBean;
import com.sxw.loan.loanorder.moudle.FirstOrderDetails;
import com.sxw.loan.loanorder.moudle.OrderBean;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-08-01.
 */

public class OrderActivity extends BaseActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.orderusername)
    TextView orderusername;
    @BindView(R.id.orderuserage)
    TextView orderuserage;
    @BindView(R.id.orderusersex)
    TextView orderusersex;
    @BindView(R.id.orderusertime)
    TextView orderusertime;
    @BindView(R.id.orderusercity)
    TextView orderusercity;
    @BindView(R.id.orderusertimemoney)
    TextView orderusertimemoney;
    @BindView(R.id.orderusertimemonth)
    TextView orderusertimemonth;
    @BindView(R.id.orderuserwork)
    TextView orderuserwork;
    @BindView(R.id.orderuserSecurity)
    TextView orderuserSecurity;
    @BindView(R.id.orderuserCreditRecord)
    TextView orderuserCreditRecord;
    @BindView(R.id.orderuserhouse)
    TextView orderuserhouse;
    @BindView(R.id.btn_qd)
    Button btnQd;
    @BindView(R.id.change)
    TextView change;
    private String TAG = "OrderActivity";
    private Integer userid, orderid;
    private String qdflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdetails);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        Bundle bundle = getIntent().getExtras();
        orderid = bundle.getInt("orderid");
        qdflag = bundle.getString("qdflag");
        if (qdflag.equals("1")) {
            btnQd.setEnabled(false);
            btnQd.setBackground(getResources().getDrawable(R.drawable.bg_btnnor));
            btnQd.setText("已抢单");
        } else {
            btnQd.setEnabled(true);
        }
        Log.e(TAG, "onCreate: " + orderid);
        loanorder(orderid, userid);
    }

    private void loanorder(Integer orderid, Integer userid) {
        FirstOrderDetails firstOrderDetails = new FirstOrderDetails();
        firstOrderDetails.setOrderId(orderid);
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.Firsturlorderdetails)
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
                        FirstDetailsBean firstDetailsBean = new Gson().fromJson(response, FirstDetailsBean.class);
                        orderusername.setText(firstDetailsBean.getOrder().getName());
                        //年龄
                        if (firstDetailsBean.getOrder().getAge() == null) {
                            orderuserage.setText("详情咨询客户");
                        } else {
                            orderuserage.setText(firstDetailsBean.getOrder().getAge());
                        }
                        //性别
                        if (firstDetailsBean.getOrder().getJAmount() != 0) {
                            change.setText(firstDetailsBean.getOrder().getJAmount() + "J豆");
                        } else {
                            change.setText("0J豆");
                        }
                        if (firstDetailsBean.getOrder().getSex() != null && firstDetailsBean.getOrder().getSex().equals("0")) {
                            orderusersex.setText("男");
                        } else if (firstDetailsBean.getOrder().getSex() != null && firstDetailsBean.getOrder().getSex().equals("1")) {
                            orderusersex.setText("女");
                        } else if (firstDetailsBean.getOrder().getSex() == null) {
                            orderusersex.setText("男");
                        }
                        orderusertime.setText(stampToDate(String.valueOf(firstDetailsBean.getOrder().getCreateTime())));
                        orderusercity.setText(firstDetailsBean.getOrder().getCity());
                        orderusertimemoney.setText(firstDetailsBean.getOrder().getAmount() + "元");
                        //期限
                        if (firstDetailsBean.getOrder().getCreditRecord() == null) {
                            orderusertimemonth.setText("详情咨询客户");
                        } else {
                            orderusertimemonth.setText(firstDetailsBean.getOrder().getCreditRecord() + "月");
                        }
                        //职业
                        if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("0")) {
                            orderuserwork.setText("企业主");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("1")) {
                            orderuserwork.setText("上班族");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("2")) {
                            orderuserwork.setText("个体户");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("3")) {
                            orderuserwork.setText("学生");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("4")) {
                            orderuserwork.setText("公务员/事业编");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("5")) {
                            orderuserwork.setText("自由职业");
                        } else if (firstDetailsBean.getOrder().getWorkType() == null) {
                            orderuserwork.setText("无");
                        }
                        //社保
                        if (firstDetailsBean.getOrder().getSocialSecurity() != null && firstDetailsBean.getOrder().getSocialSecurity().equals("0")) {
                            orderuserSecurity.setText("有");
                        } else if (firstDetailsBean.getOrder().getSocialSecurity() != null && firstDetailsBean.getOrder().getSocialSecurity().equals("1")) {
                            orderuserSecurity.setText("无");
                        } else if (firstDetailsBean.getOrder().getSocialSecurity() == null) {
                            orderuserSecurity.setText("详情咨询客户");
                        }
                        //信用
                        if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("0")) {
                            orderuserCreditRecord.setText("无纪录");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("1")) {
                            orderuserCreditRecord.setText("良好");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("2")) {
                            orderuserCreditRecord.setText("少数逾期");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("3")) {
                            orderuserCreditRecord.setText("多数逾期");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() == null) {
                            orderuserCreditRecord.setText("详情咨询客户");
                        }
                        //资产
                        if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("0")) {
                            orderuserhouse.setText("无");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("1")) {
                            orderuserhouse.setText("房产");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("2")) {
                            orderuserhouse.setText("车");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("3")) {
                            orderuserhouse.setText("其他");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() == null) {
                            orderuserhouse.setText("无");
                        }
                    }

                });
    }

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    @OnClick({R.id.image, R.id.btn_qd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                //推出
                startActivity(MainActivity.class);
                this.finish();
                break;
            case R.id.btn_qd:
                //抢单
                BtTxQdReg btTxQdReg = new BtTxQdReg();
                btTxQdReg.setId(orderid);
                btTxQdReg.setJlId(userid);
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.Firstqdurl)
                        .content(new Gson().toJson(btTxQdReg))
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
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("orderid", orderid);
                                    startActivity(ListDetailsQdActivity.class, bundle);
                                } else {
                                    ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                    OrderActivity.this.finish();
                                    startActivity(MainActivity.class);
                                }
                            }

                        });
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            startActivity(MainActivity.class);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
