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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.sxw.loan.loanorder.moudle.BtTxQdDetailsBean;
import com.sxw.loan.loanorder.moudle.FirstOrderDetails;
import com.sxw.loan.loanorder.moudle.FirstQdDetailsBean;
import com.sxw.loan.loanorder.moudle.OrderBean;
import com.sxw.loan.loanorder.moudle.TokenQi;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.ToastUtils;
import com.sy.alex_library.ui.LoadingDialog;
import com.sy.alex_library.ui.ScrollListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/7/28.
 */

public class MineWhiteQDactivity extends BaseActivity {
    private static final String TAG = "MineWhiteQDactivity";
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.liuchengListView)
    ScrollListView liuchengListView;
    @BindView(R.id.tabliucheng)
    LinearLayout tabliucheng;
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
    @BindView(R.id.btqdusermoney)
    TextView btqdusermoney;
    @BindView(R.id.btqdusermonth)
    TextView btqdusermonth;
    @BindView(R.id.change)
    TextView change;
    @BindView(R.id.tabdetailds)
    LinearLayout tabdetailds;
    @BindView(R.id.txtholdfalis)
    TextView txtholdfalis;
    @BindView(R.id.btn_tel)
    Button btnTel;
    @BindView(R.id.btn_hoildfild)
    Button btnHoildfild;
    @BindView(R.id.btn_btqd)
    Button btnBtqd;
    @BindView(R.id.linehold)
    LinearLayout linehold;
    @BindView(R.id.btn_faild)
    Button btnFaild;
    @BindView(R.id.btn_btqdsuccess)
    Button btnBtqdsuccess;
    @BindView(R.id.linesuccess)
    LinearLayout linesuccess;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minewhitrqd);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
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
                    tabdetailds.setVisibility(View.VISIBLE);
                    tabliucheng.setVisibility(View.GONE);
                }
                if (tab.getPosition() == 1) {
                    list.clear();
                    loanorderliucheng(orderid, userid);
                    tabdetailds.setVisibility(View.GONE);
                    tabliucheng.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        Bundle bundle = getIntent().getExtras();
        orderid = bundle.getInt("orderid");
        loanorder(orderid, userid);
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
                .connectTimeout(10) // 链接超时。默认10秒
                .responseTimeout(60) // 服务器响应超时。默认60秒
                .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
    }

    private void loanorder(Integer orderid, Integer userid) {
        FirstOrderDetails firstOrderDetails = new FirstOrderDetails();
        firstOrderDetails.setOrderId(orderid);
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.minewhiteqdurl)
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
                        BtTxQdDetailsBean btTxQdDetailsBean = new Gson().fromJson(response, BtTxQdDetailsBean.class);

                        btqdusername.setText(btTxQdDetailsBean.getOrder().getName());
                        if (btTxQdDetailsBean.getOrder().getAmount() >= 0) {
                            btqdusermoney.setText(btTxQdDetailsBean.getOrder().getAmount() + "元");
                        } else {
                            btqdusermoney.setText("联系客户咨询");
                        }
                        if (btTxQdDetailsBean.getOrder().getCreditPeriod() >= 0) {
                            btqdusermonth.setText(btTxQdDetailsBean.getOrder().getCreditPeriod() + "月");
                        } else {
                            btqdusermonth.setText("联系客户咨询");
                        }
                        if (btTxQdDetailsBean.getOrder().getJfaMount() != 0) {
                            change.setText(btTxQdDetailsBean.getOrder().getJfaMount() + "积分");
                        } else {
                            change.setText("0积分");
                        }
                        if (btTxQdDetailsBean.getOrder().getSexFlag() != null && btTxQdDetailsBean.getOrder().getSexFlag().equals("0")) {
                            btqdusersex.setText("男");
                        } else if (btTxQdDetailsBean.getOrder().getSexFlag() != null && btTxQdDetailsBean.getOrder().getSexFlag().equals("1")) {
                            btqdusersex.setText("女");
                        } else {
                            btqdusersex.setText("男");
                        }
                        btqdusercity.setText(btTxQdDetailsBean.getOrder().getCity() + btTxQdDetailsBean.getOrder().getAddress());
                        btqdusertimemoney.setText(btTxQdDetailsBean.getOrder().getBtName());
                        btqdusertel.setText(btTxQdDetailsBean.getOrder().getPhone());
                        phone = btTxQdDetailsBean.getOrder().getPhone();
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 4) {
                            linehold.setVisibility(View.GONE);
                            linesuccess.setVisibility(View.VISIBLE);
                        }
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 1) {
                            txtholdfalis.setVisibility(View.VISIBLE);
                            linesuccess.setVisibility(View.GONE);
                            linehold.setVisibility(View.GONE);
                        }
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 2) {
                            txtholdfalis.setVisibility(View.VISIBLE);
                            linesuccess.setVisibility(View.GONE);
                            linehold.setVisibility(View.GONE);
                        }
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 3) {
                            txtholdfalis.setVisibility(View.VISIBLE);
                            linesuccess.setVisibility(View.GONE);
                            linehold.setVisibility(View.GONE);
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
                .url(ConstantUrl.minewhiteqdurl)
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
                        BtTxQdDetailsBean btTxQdDetailsBean = new Gson().fromJson(response, BtTxQdDetailsBean.class);

                        list.add(btTxQdDetailsBean.getProcess().getQdMsg());
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 4) {
                            list.add("Hold住订单(已经联系客户，达成初步的合作意向)");

                            linehold.setVisibility(View.GONE);
                            linesuccess.setVisibility(View.VISIBLE);
                        }
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 1) {
                            list.add("订单已取消" + "(" + btTxQdDetailsBean.getProcess().getCancelReason() + ")");
                            list.add("订单已完成");
                            txtholdfalis.setVisibility(View.VISIBLE);
                            linesuccess.setVisibility(View.GONE);
                            linehold.setVisibility(View.GONE);
                        }
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 2) {
                            list.add("Hold住订单(已经联系客户，达成初步的合作意向)");
                            list.add("放款失败" + "(" + btTxQdDetailsBean.getProcess().getDkFailReason() + ")");
                            list.add("订单已完成");
                            txtholdfalis.setVisibility(View.VISIBLE);
                            linesuccess.setVisibility(View.GONE);
                            linehold.setVisibility(View.GONE);
                        }
                        if (btTxQdDetailsBean.getProcess().getOrderStatus() == 3) {
                            list.add("Hold住订单(已经联系客户，达成初步的合作意向)");
                            list.add("放款成功");
                            list.add("订单已完成");
                            txtholdfalis.setVisibility(View.VISIBLE);
                            linesuccess.setVisibility(View.GONE);
                            linehold.setVisibility(View.GONE);
                        }
                        Message message = new Message();
                        message.what = 1;
                        myHandler.sendMessage(message);
                    }

                });
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    liuChenAdapter = new LiuChenAdapter(MineWhiteQDactivity.this, list);
                    liuchengListView.setAdapter(liuChenAdapter);
                    liuChenAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @OnClick({R.id.btqdusertel,R.id.btn_back, R.id.btn_tel, R.id.btn_btqd, R.id.btn_hoildfild, R.id.btn_faild, R.id.btn_btqdsuccess})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btqdusertel:
                //打电话
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
            case R.id.btn_back:
                this.finish();
                break;
            case R.id.btn_hoildfild:
                showInputnegative();
                break;
            case R.id.btn_btqdsuccess:
                new ActionSheetDialog(MineWhiteQDactivity.this)
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
                new ActionSheetDialog(MineWhiteQDactivity.this)
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
                //打电话
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
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.bttxqdupurl)
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
                                    linehold.setVisibility(View.GONE);
                                    linesuccess.setVisibility(View.VISIBLE);
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
                                        OkHttpUtils
                                                .postString()
                                                .url(ConstantUrl.bttxqdupurl)
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
                                                        txtholdfalis.setVisibility(View.VISIBLE);
                                                        linesuccess.setVisibility(View.GONE);
                                                        linehold.setVisibility(View.GONE);
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
                                        firstQdDetailsBean.setOrderStatus(2);
                                        firstQdDetailsBean.setJlId(userid);
                                        firstQdDetailsBean.setDkFailReason(text);
                                        OkHttpUtils
                                                .postString()
                                                .url(ConstantUrl.bttxqdupurl)
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
                                                        txtholdfalis.setVisibility(View.VISIBLE);
                                                        linesuccess.setVisibility(View.GONE);
                                                        linehold.setVisibility(View.GONE);
                                                    }

                                                });
                                    }
                                })
                .show();

    }

    private void showInputimage() {
        View contentView = LayoutInflater.from(MineWhiteQDactivity.this).inflate(R.layout.pop_window, null);
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
                PictureSelector.create(MineWhiteQDactivity.this)
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
                    OkHttpUtils
                            .postString()
                            .url(ConstantUrl.bttxqdupurl)
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
                                    txtholdfalis.setVisibility(View.VISIBLE);
                                    linesuccess.setVisibility(View.GONE);
                                    linehold.setVisibility(View.GONE);
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
        popWindow.showAtLocation(MineWhiteQDactivity.this.findViewById(R.id.rela), Gravity.BOTTOM, 0, 0);
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
