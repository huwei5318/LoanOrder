package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivityTaoqdBinding;
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

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-07-24.
 */

public class TaoQdActivity extends BaseActivity<ActivityTaoqdBinding> {
    private static final String TAG = "TaoQdActivity";

    private Integer userid, orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taoqd);

        showContentView();
        setTitle("订单详情");

        setListener();


        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        Bundle bundle = getIntent().getExtras();
        orderid = bundle.getInt("orderid");
        loanorder(orderid, userid);
    }

    private void setListener() {
        bindingView.btnQd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //抢单
                BtTxQdReg btTxQdReg = new BtTxQdReg();
                btTxQdReg.setId(orderid);
                btTxQdReg.setJlId(userid);
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.tdqdqdurl)
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
                                    startActivity(TaoQdDetailsActivity.class, bundle);
                                    TaoQdActivity.this.finish();
                                } else {
                                    ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                    TaoQdActivity.this.finish();
                                }
                            }

                        });
            }
        });
    }

    private void loanorder(Integer orderid, Integer userid) {
        FirstOrderDetails firstOrderDetails = new FirstOrderDetails();
        firstOrderDetails.setOrderId(orderid);
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.tdqddetailsurl)
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
                        bindingView.orderusername.setText(firstDetailsBean.getOrder().getName());
                        //年龄
                        if (firstDetailsBean.getOrder().getAge() == null) {
                            bindingView.orderuserage.setText("详情咨询客户");
                        } else {
                            bindingView.orderuserage.setText(firstDetailsBean.getOrder().getAge());
                        }
                        //性别
                        if (firstDetailsBean.getOrder().getSex() != null && firstDetailsBean.getOrder().getSex().equals("0")) {
                            bindingView.orderusersex.setText("男");
                        } else if (firstDetailsBean.getOrder().getSex() != null && firstDetailsBean.getOrder().getSex().equals("1")) {
                            bindingView.orderusersex.setText("女");
                        } else if (firstDetailsBean.getOrder().getSex() == null) {
                            bindingView.orderusersex.setText("男");
                        }
                        if (firstDetailsBean.getOrder().getJAmount() != 0) {
                            bindingView.change.setText(firstDetailsBean.getOrder().getJfAmount() + "积分");
                        } else {
                            bindingView.change.setText("0积分");
                        }
                        bindingView.orderusertime.setText(stampToDate(String.valueOf(firstDetailsBean.getOrder().getCreateTime())));
                        bindingView.orderusercity.setText(firstDetailsBean.getOrder().getCity());
                        bindingView.orderusertimemoney.setText(firstDetailsBean.getOrder().getAmount() + "元");
                        //期限
                        if (firstDetailsBean.getOrder().getCreditRecord() == null) {
                            bindingView.orderusertimemonth.setText("详情咨询客户");
                        } else {
                            bindingView.orderusertimemonth.setText(firstDetailsBean.getOrder().getCreditRecord() + "月");
                        }
                        //职业
                        if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("0")) {
                            bindingView.orderuserwork.setText("企业主");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("1")) {
                            bindingView.orderuserwork.setText("上班族");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("2")) {
                            bindingView.orderuserwork.setText("个体户");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("3")) {
                            bindingView.orderuserwork.setText("学生");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("4")) {
                            bindingView.orderuserwork.setText("公务员/事业编");
                        } else if (firstDetailsBean.getOrder().getWorkType() != null && firstDetailsBean.getOrder().getWorkType().equals("5")) {
                            bindingView.orderuserwork.setText("自由职业");
                        } else if (firstDetailsBean.getOrder().getWorkType() == null) {
                            bindingView.orderuserwork.setText("无");
                        }
                        //社保
                        if (firstDetailsBean.getOrder().getSocialSecurity() != null && firstDetailsBean.getOrder().getSocialSecurity().equals("0")) {
                            bindingView.orderuserSecurity.setText("有");
                        } else if (firstDetailsBean.getOrder().getSocialSecurity() != null && firstDetailsBean.getOrder().getSocialSecurity().equals("1")) {
                            bindingView.orderuserSecurity.setText("无");
                        } else if (firstDetailsBean.getOrder().getSocialSecurity() == null) {
                            bindingView.orderuserSecurity.setText("详情咨询客户");
                        }
                        //信用
                        if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("0")) {
                            bindingView.orderuserCreditRecord.setText("无纪录");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("1")) {
                            bindingView.orderuserCreditRecord.setText("良好");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("2")) {
                            bindingView.orderuserCreditRecord.setText("少数逾期");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() != null && firstDetailsBean.getOrder().getCreditRecord().equals("3")) {
                            bindingView.orderuserCreditRecord.setText("多数逾期");
                        } else if (firstDetailsBean.getOrder().getCreditRecord() == null) {
                            bindingView.orderuserCreditRecord.setText("详情咨询客户");
                        }
                        //资产
                        if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("0")) {
                            bindingView.orderuserhouse.setText("无");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("1")) {
                            bindingView.orderuserhouse.setText("房产");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("2")) {
                            bindingView.orderuserhouse.setText("车");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() != null && firstDetailsBean.getOrder().getHouseholdAssets().equals("3")) {
                            bindingView.orderuserhouse.setText("其他");
                        } else if (firstDetailsBean.getOrder().getHouseholdAssets() == null) {
                            bindingView.orderuserhouse.setText("无");
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

}
