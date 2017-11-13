package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.adapter.BuyJbadapter;
import com.sxw.loan.loanorder.databinding.ActivityMinemoneyBinding;
import com.sxw.loan.loanorder.moudle.JBData;
import com.sxw.loan.loanorder.moudle.SendInfo;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.aliPayUtils.AuthResult;
import com.sy.alex_library.aliPayUtils.PayResult;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.ToastUtils;
import com.sy.alex_library.ui.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-07-14.
 */

public class MineMoneyActivity extends BaseActivity<ActivityMinemoneyBinding> {

    private BuyJbadapter buyJbadapter;
    private List<JBData> jbDatas = new ArrayList<>();
    private String flag = "1", goodsid;
    private int userid;
    private double price;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private LoadingDialog paySuccse;
    private int amount;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minemoney);

        showContentView();
        setTitle("我的J豆");




        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        amount = sharedPreferences.getInt("amount", 0);
        bindingView.numbers.setText(amount + "");
        loadjb();
    }

    private void loadjb() {
        OkHttpUtils
                .get()
                .url(ConstantUrl.jburl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("minemoneyactivity", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("minemoneyactivity", response);
                        try {
                            JSONArray myJsonArray = new JSONArray(response);
                            goodsid = String.valueOf(myJsonArray.getJSONObject(0).getInt("id"));
                            price = myJsonArray.getJSONObject(0).getDouble("originalPrice");
                            for (int i = 0; i < myJsonArray.length(); i++) {
                                JSONObject myjObject = myJsonArray.getJSONObject(i);
                                JBData jbData = new JBData();
                                jbData.setFlag(myjObject.getString("flag"));
                                jbData.setId(myjObject.getInt("id"));
                                jbData.setOriginalPrice(myjObject.getDouble("originalPrice"));
                                jbData.setPresentPrice(myjObject.getDouble("presentPrice"));
                                jbDatas.add(jbData);
                            }
                            buyJbadapter = new BuyJbadapter(getApplicationContext(), jbDatas);
                            bindingView.gridJb.setAdapter(buyJbadapter);
                            buyJbadapter.notifyDataSetChanged();
                            bindingView.gridJb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    buyJbadapter.setSeclection(position);
                                    buyJbadapter.notifyDataSetChanged();
                                    goodsid = String.valueOf(jbDatas.get(position).getId());
                                    price = jbDatas.get(position).getOriginalPrice();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @OnClick({R.id.btn_back, R.id.btn_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                this.finish();
                break;
            case R.id.btn_buy:
                if (goodsid.isEmpty()) {
                    ToastUtils.showToastGravityCenter("请选择充值金额");
                    return;
                }
                if (price <= 0) {
                    ToastUtils.showToastGravityCenter("请选择充值金额");
                    return;
                }
                SendInfo sendInfo = new SendInfo();
                sendInfo.setFlag(flag);
                sendInfo.setUserId(userid);
                sendInfo.setGoodsId(goodsid);
                sendInfo.setPrice(price);
                Log.e("asdasdasdasd", new Gson().toJson(sendInfo));
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.aliurl)
                        .content(new Gson().toJson(sendInfo))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("Vip", e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("Vip", response.toString());
                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    Log.e("Vip", jsonObject.getString("Result"));
                                    aliPay(jsonObject.getString("Result"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                        });

                break;
        }
    }

    private void aliPay(String result) {
        final String orderInfo = result;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(MineMoneyActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")

                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    Log.e("asdasdasd", payResult.toString() + "1");
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    Log.e("aaaaa11111111111", resultInfo + "\n" + resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        paySuccse = new LoadingDialog(MineMoneyActivity.this, "支付成功");
                        paySuccse.show();
                        timer.start();
                        //    Toast.makeText(VipActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                        AlipayBean alipayTradeAppPayResponseBean = gson.fromJson(resultInfo, AlipayBean.class);
//                        aliordernum = alipayTradeAppPayResponseBean.getAlipay_trade_app_pay_response().getTrade_no();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MineMoneyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(MineMoneyActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(MineMoneyActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
    private CountDownTimer timer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            paySuccse.close();
            MineMoneyActivity.this.finish();
        }
    };
}
