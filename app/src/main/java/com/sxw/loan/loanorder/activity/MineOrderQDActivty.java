package com.sxw.loan.loanorder.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.adorkable.iosdialog.ActionSheetDialog;
import com.eminayar.panter.DialogType;
import com.eminayar.panter.PanterDialog;
import com.eminayar.panter.interfaces.OnTextInputConfirmListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.adapter.LiuChenAdapter;
import com.sxw.loan.loanorder.databinding.ActivityMineorderqdBinding;
import com.sxw.loan.loanorder.moudle.FirstDetailsBean;
import com.sxw.loan.loanorder.moudle.FirstOrderDetails;
import com.sxw.loan.loanorder.moudle.FirstQdDetailsBean;
import com.sxw.loan.loanorder.moudle.OrderBean;
import com.sxw.loan.loanorder.moudle.TokenQi;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.ToastUtils;
import com.sy.alex_library.ui.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

import static com.sxw.loan.loanorder.R.id.orderusertel;

/**
 * Created by Administrator on 2017/7/28.
 */

public class MineOrderQDActivty extends BaseActivity<ActivityMineorderqdBinding> {
    private static final String TAG = "MineOrderQDActivty";

    private int userid, orderid;
    private String phone;
    List<LocalMedia> selectList;
    private UploadManager uploadManager;
    private String token;
    private String idcodezs = "";
    private SimpleDraweeView simpleDraweeView;
    private TabLayout tabLayout;
    private List<String> list = new ArrayList<>();
    private LiuChenAdapter liuChenAdapter;
    private String IDuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineorderqd);

        showContentView();
        setTitle("订单详情");
        setListener();


        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        IDuser = String.valueOf(userid);
        Bundle bundle = getIntent().getExtras();
        orderid = bundle.getInt("orderid");
        loanorder(orderid, userid);
        loanorderliucheng(orderid, userid);
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
                .connectTimeout(10) // 链接超时。默认10秒
                .responseTimeout(60) // 服务器响应超时。默认60秒
                .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                tabLayout.addTab(tabLayout.newTab().setText("订单详情"), 0);
            }
            if (i == 1) {
                tabLayout.addTab(tabLayout.newTab().setText("订单跟进"), 1);
            }
        }
        //设置tab的点击监听器
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    bindingView.tabdetailds.setVisibility(View.VISIBLE);
                    bindingView.tabliucheng.setVisibility(View.GONE);
                }
                if (tab.getPosition() == 1) {
                    list.clear();
                    loanorderliucheng(orderid, userid);
                    bindingView.tabdetailds.setVisibility(View.GONE);
                    bindingView.tabliucheng.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void loanorder(Integer orderid, final Integer userid) {
        FirstOrderDetails firstOrderDetails = new FirstOrderDetails();
        firstOrderDetails.setOrderId(orderid);
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.mineorderdetailsurl)
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
                        bindingView.orderusertel.setText(firstDetailsBean.getOrder().getPhoneNumber());
                        phone = firstDetailsBean.getOrder().getPhoneNumber();
                        if (IDuser.equals(firstDetailsBean.getOrder().getJlId())) {
                            if (firstDetailsBean.getOrder().getJAmount() != 0) {
                                bindingView.change.setText(firstDetailsBean.getOrder().getJAmount() + "J豆");
                            } else {
                                bindingView.change.setText("0J豆");
                            }
                        } else {
                            if (firstDetailsBean.getOrder().getJfAmount() != 0) {
                                bindingView.change.setText(firstDetailsBean.getOrder().getJAmount() + "积分");
                            } else {
                                bindingView.change.setText("0积分");
                            }
                        }
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

    private void loanorderliucheng(Integer orderid, Integer userid) {
        FirstOrderDetails firstOrderDetails = new FirstOrderDetails();
        firstOrderDetails.setOrderId(orderid);
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.mineorderdetailsurl)
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
                        list.add(firstDetailsBean.getProcess().getQdMsg());
                        if (firstDetailsBean.getProcess().getOrderStatus() == 4) {
                            list.add("Hold住订单(已经联系客户，达成初步的合作意向)");
                            bindingView.linehold.setVisibility(View.GONE);
                            bindingView.linesuccess.setVisibility(View.VISIBLE);
                        }
                        if (firstDetailsBean.getProcess().getOrderStatus() == 1) {
                            list.add("订单已取消" + "(" + firstDetailsBean.getProcess().getCancelReason() + ")");
                            list.add("订单已完成");
                            bindingView.txtholdfalis.setVisibility(View.VISIBLE);
                            bindingView.linesuccess.setVisibility(View.GONE);
                            bindingView.linehold.setVisibility(View.GONE);
                        }
                        if (firstDetailsBean.getProcess().getOrderStatus() == 2) {
                            list.add("Hold住订单(已经联系客户，达成初步的合作意向)");
                            list.add("放款失败" + "(" + firstDetailsBean.getProcess().getDkFailReason() + ")");
                            list.add("订单已完成");

                            bindingView.txtholdfalis.setVisibility(View.VISIBLE);
                            bindingView.linesuccess.setVisibility(View.GONE);
                            bindingView.linehold.setVisibility(View.GONE);
                        }
                        if (firstDetailsBean.getProcess().getOrderStatus() == 3) {
                            list.add("Hold住订单(已经联系客户，达成初步的合作意向)");
                            list.add("放款成功");
                            list.add("订单已完成");
                            bindingView.txtholdfalis.setVisibility(View.VISIBLE);
                            bindingView.linesuccess.setVisibility(View.GONE);
                            bindingView.linehold.setVisibility(View.GONE);
                        }
                        Message message = new Message();
                        message.what = 1;
                        myHandler.sendMessage(message);
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

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    liuChenAdapter = new LiuChenAdapter(MineOrderQDActivty.this, list);
                    bindingView.liuchengListView.setAdapter(liuChenAdapter);
                    liuChenAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void setListener() {
        bindingView.orderusertel.setOnClickListener(this);
        bindingView.btnTel.setOnClickListener(this);
        bindingView.btnBtqd.setOnClickListener(this);
        bindingView.btnHoildfild.setOnClickListener(this);
        bindingView.btnBtqdsuccess.setOnClickListener(this);
        bindingView.btnFaild.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case orderusertel:
                Intent mIntent1 = new Intent(Intent.ACTION_CALL);
                mIntent1.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(mIntent1);
                break;
            case R.id.btn_hoildfild:
                showInputnegative();
                break;
            case R.id.btn_btqdsuccess:
                new ActionSheetDialog(MineOrderQDActivty.this)
                        .builder()
                        .setTitle("请选择操作")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("贷款成功凭据", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        showInputimage();
                                    }
                                }).show();
                break;
            case R.id.btn_faild:
                new ActionSheetDialog(MineOrderQDActivty.this)
                        .builder()
                        .setTitle("请选择操作")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("放款失败原因", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        showInputfails();
                                    }
                                }).show();

                break;
            case R.id.btn_tel:
                Intent mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(mIntent);
                break;
            case R.id.btn_btqd:
                //hold住
                FirstQdDetailsBean firstQdDetailsBean = new FirstQdDetailsBean();
                firstQdDetailsBean.setOrderId(orderid);
                firstQdDetailsBean.setJlId(userid);
                firstQdDetailsBean.setOrderStatus(4);
                Log.e(TAG, "onClick: " + new Gson().toJson(firstQdDetailsBean));
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.mineorderqdurl)
                        .content(new Gson().toJson(firstQdDetailsBean))
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
                                    bindingView.linehold.setVisibility(View.GONE);
                                    bindingView.linesuccess.setVisibility(View.VISIBLE);
                                    ToastUtils.showToastGravityCenter(orderBean.getMsg() + ",可以继续操作");
                                } else {
                                    ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                }
                            }

                        });
                break;
        }
    }

    //holdbuzhu
    private void showInputnegative() {
        new PanterDialog(this)
                .setHeaderBackground(R.mipmap.bg_rela)
                .setDialogType(DialogType.INPUT)
                .setTitle("订单取消原因")
                .isCancelable(true)
                .setPositive("提交")
                .setNegative("取消")
                .input("请输入订单取消原因",
                        "请输入订单取消原因", new
                                OnTextInputConfirmListener() {
                                    @Override
                                    public void onTextInputConfirmed(String text) {
                                        FirstQdDetailsBean firstQdDetailsBean = new FirstQdDetailsBean();
                                        firstQdDetailsBean.setOrderId(orderid);
                                        firstQdDetailsBean.setOrderStatus(1);
                                        firstQdDetailsBean.setJlId(userid);
                                        firstQdDetailsBean.setCancelReason(text);
                                        Log.e(TAG, "onClick: " + new Gson().toJson(firstQdDetailsBean));
                                        OkHttpUtils
                                                .postString()
                                                .url(ConstantUrl.mineorderqdurl)
                                                .content(new Gson().toJson(firstQdDetailsBean))
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
                                                        ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                                        bindingView.txtholdfalis.setVisibility(View.VISIBLE);
                                                        bindingView.linesuccess.setVisibility(View.GONE);
                                                        bindingView.linehold.setVisibility(View.GONE);
                                                    }

                                                });
                                    }
                                })
                .show();

    }

    //放款失败原因
    private void showInputfails() {
        new PanterDialog(this)
                .setHeaderBackground(R.mipmap.bg_rela)
                .setDialogType(DialogType.INPUT)
                .setTitle("放款失败原因")
                .isCancelable(true)
                .setPositive("提交")
                .setNegative("取消")
                .input("请输入放款失败原因",
                        "请输入放款失败原因", new
                                OnTextInputConfirmListener() {
                                    @Override
                                    public void onTextInputConfirmed(String text) {
                                        FirstQdDetailsBean firstQdDetailsBean = new FirstQdDetailsBean();
                                        firstQdDetailsBean.setOrderId(orderid);
                                        firstQdDetailsBean.setJlId(userid);
                                        firstQdDetailsBean.setOrderStatus(2);
                                        firstQdDetailsBean.setDkFailReason(text);
                                        Log.e(TAG, "onClick: " + new Gson().toJson(firstQdDetailsBean));
                                        OkHttpUtils
                                                .postString()
                                                .url(ConstantUrl.mineorderqdurl)
                                                .content(new Gson().toJson(firstQdDetailsBean))
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
                                                        ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                                        MineOrderQDActivty.this.finish();
                                                    }

                                                });
                                    }
                                })
                .show();

    }

    //chenggong
    private void showInputimage() {
        View contentView = LayoutInflater.from(MineOrderQDActivty.this).inflate(R.layout.pop_window, null);
        simpleDraweeView = (SimpleDraweeView) contentView.findViewById(R.id.successimage);
        Button buttonsure = (Button) contentView.findViewById(R.id.sure);
        Button buttoncancle = (Button) contentView.findViewById(R.id.cancle);
        final PopupWindow popWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, false);
        popWindow.setTouchable(true);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(MineOrderQDActivty.this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .compress(true)
                        .forResult(1);
            }
        });
        buttoncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popWindow.dismiss();
            }
        });
        buttonsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idcodezs.length() > 2) {
                    FirstQdDetailsBean firstQdDetailsBean = new FirstQdDetailsBean();
                    firstQdDetailsBean.setOrderId(orderid);
                    firstQdDetailsBean.setOrderStatus(3);
                    firstQdDetailsBean.setJlId(userid);
                    firstQdDetailsBean.setDkSuccCredential(idcodezs);
                    Log.e(TAG, "onClick: " + new Gson().toJson(firstQdDetailsBean));
                    OkHttpUtils
                            .postString()
                            .url(ConstantUrl.mineorderqdurl)
                            .content(new Gson().toJson(firstQdDetailsBean))
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
                                    ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                    popWindow.dismiss();
                                    bindingView.txtholdfalis.setVisibility(View.VISIBLE);
                                    bindingView.linesuccess.setVisibility(View.GONE);
                                    bindingView.linehold.setVisibility(View.GONE);
                                }

                            });
                } else {
                    ToastUtils.showToastGravityCenter("请上传成功凭证");
                }
            }
        });
        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        popWindow.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //在底部显示
        popWindow.showAtLocation(MineOrderQDActivty.this.findViewById(R.id.rela), Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                final HashMap<String, String> map = new HashMap<String, String>();
                map.put("x:phone", "12345678");

                Log.d("qiniu", "click upload");
                selectList = PictureSelector.obtainMultipleResult(data);
                final LocalMedia localMedia = selectList.get(0);
                Log.e("localMedia", localMedia.getPath());
                if (localMedia.getPath().length() > 0) {
                    final LoadingDialog loadingDialog = new LoadingDialog(this, "上传中...");
                    loadingDialog.show();
                    OkHttpUtils
                            .get()
                            .url(ConstantUrl.qiniuurl)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("token", e.toString());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("token", response.toString());
                                    Gson gson = new Gson();
                                    TokenQi tokenQi = gson.fromJson(response, TokenQi.class);
                                    token = tokenQi.getToken();
                                    Date date = new Date();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                                    final String key = sdf.format(date);
                                    File file = new File(localMedia.getPath());
//
                                    uploadManager.put(file, key, token, new UpCompletionHandler() {
                                        @Override
                                        public void complete(String s, ResponseInfo responseInfo,
                                                             JSONObject jsonObject) {
                                            if (responseInfo.isOK()) {
                                                Log.e("success1", "complete: " + s + "\n" + jsonObject + "\n" + responseInfo);
                                            } else {
                                                Log.e("fail", s + responseInfo + jsonObject);
                                            }
                                            Log.e("qiniu", "complete: ");

                                        }
                                    }, new UploadOptions(map, null, false,
                                            new UpProgressHandler() {
                                                public void progress(String key, double percent) {
                                                    Log.i("qiniu", key + ": " + percent);

                                                    int progress = (int) (percent * 1000);
//                                          Log.d("qiniu", progress+"");

                                                    if (progress == 1000) {
                                                        loadingDialog.close();
                                                        idcodezs = key;
                                                        simpleDraweeView.setImageURI("file://" + localMedia.getPath());
                                                    }
                                                }

                                            }, new UpCancellationSignal() {
                                        @Override
                                        public boolean isCancelled() {
                                            return false;
                                        }
                                    }));
                                }
                            });
                } else {
                    showToast("图片不存在");
                }

            }
        }
    }
}
