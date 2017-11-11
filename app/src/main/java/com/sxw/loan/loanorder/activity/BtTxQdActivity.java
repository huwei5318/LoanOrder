package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.BtQdDetailsBean;
import com.sxw.loan.loanorder.moudle.BtTxQdDetailsBean;
import com.sxw.loan.loanorder.moudle.BtTxQdReg;
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
 * Created by Sxw on 2017-07-23.
 */

public class BtTxQdActivity extends BaseActivity {
    private static final String TAG = "bttxqd";
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btqdusername)
    TextView btqdusername;
    @BindView(R.id.btqdusersex)
    TextView btqdusersex;
    @BindView(R.id.btqdusertel)
    TextView btqdusertel;
    @BindView(R.id.btqdusertime)
    TextView btqdusertime;
    @BindView(R.id.btqdusercity)
    TextView btqdusercity;
    @BindView(R.id.btqdusertimemoney)
    TextView btqdusertimemoney;
    @BindView(R.id.btn_btqd)
    Button btnBtqd;
    @BindView(R.id.change)
    TextView change;
    @BindView(R.id.btqdusermoney)
    TextView btqdusermoney;
    @BindView(R.id.btqdusermonth)
    TextView btqdusermonth;
    private int userid, orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bttxqd);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        Bundle bundle = getIntent().getExtras();
        orderid = bundle.getInt("orderid");
        loanorder(orderid, userid);
    }

    private void loanorder(Integer orderid, Integer userid) {
        FirstOrderDetails firstOrderDetails = new FirstOrderDetails();
        firstOrderDetails.setOrderId(orderid);
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.bttxurl)
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
                        BtTxQdDetailsBean btQdDetailsBean = new Gson().fromJson(response, BtTxQdDetailsBean.class);
                        if (btQdDetailsBean.getOrder().getName() != null) {
                            btqdusername.setText(btQdDetailsBean.getOrder().getName());
                        }
                        //金额
                        if (btQdDetailsBean.getOrder().getAmount() >= 0) {
                            btqdusermoney.setText(btQdDetailsBean.getOrder().getAmount() + "元");
                        } else {
                            btqdusermoney.setText("联系客户咨询");
                        }
                        if (btQdDetailsBean.getOrder().getCreditPeriod() >= 0) {
                            btqdusermonth.setText(btQdDetailsBean.getOrder().getCreditPeriod() + "月");
                        } else {
                            btqdusermonth.setText("联系客户咨询");
                        }
                        if (btQdDetailsBean.getOrder().getJfaMount() != 0) {
                            change.setText(btQdDetailsBean.getOrder().getJfaMount() + "积分");
                        } else {
                            change.setText("0积分");
                        }
                        if (btQdDetailsBean.getOrder().getSexFlag() != null && btQdDetailsBean.getOrder().getSexFlag().equals("0")) {
                            btqdusersex.setText("男");
                        } else if (btQdDetailsBean.getOrder().getSexFlag() != null && btQdDetailsBean.getOrder().getSexFlag().equals("1")) {
                            btqdusersex.setText("女");
                        } else {
                            btqdusersex.setText("男");
                        }
                        btqdusertime.setText(stampToDate(String.valueOf(btQdDetailsBean.getOrder().getTime())));

                        btqdusercity.setText(btQdDetailsBean.getOrder().getCity() + btQdDetailsBean.getOrder().getAddress());
                        btqdusertimemoney.setText(btQdDetailsBean.getOrder().getBtName());

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
    @OnClick({R.id.btn_back, R.id.btn_btqd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                this.finish();
                break;
            case R.id.btn_btqd:
                BtTxQdReg btTxQdReg = new BtTxQdReg();
                btTxQdReg.setId(orderid);
                btTxQdReg.setJlId(userid);
                Log.e(TAG, "onClick: " + new Gson().toJson(btTxQdReg));
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.bttxqdurl)
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
                                    startActivity(BtTxQdDetailsActivity.class, bundle);
                                    BtTxQdActivity.this.finish();
                                } else {
                                    ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                    BtTxQdActivity.this.finish();
                                }
                            }

                        });
                break;
        }
    }
}
