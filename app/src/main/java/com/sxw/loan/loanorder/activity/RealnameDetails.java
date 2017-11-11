package com.sxw.loan.loanorder.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.RealReg;
import com.sxw.loan.loanorder.moudle.RealnameBean;
import com.sy.alex_library.base.BaseActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lljjcoder.citypickerview.widget.CityPicker;
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
import com.sxw.loan.loanorder.moudle.OrderBean;
import com.sxw.loan.loanorder.moudle.RealNameData;
import com.sxw.loan.loanorder.moudle.TokenQi;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.tools.EditTools;
import com.sy.alex_library.tools.ToastUtils;
import com.sy.alex_library.ui.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-08-03.
 */

public class RealnameDetails extends BaseActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.citys)
    TextView citys;
    @BindView(R.id.company)
    EditText company;
    @BindView(R.id.companytel)
    EditText companytel;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.idcode)
    EditText idcode;
    @BindView(R.id.imagecode1)
    SimpleDraweeView imagecode1;
    @BindView(R.id.imagecode2)
    SimpleDraweeView imagecode2;
    @BindView(R.id.tijiao)
    Button tijiao;
    private String City;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    List<LocalMedia> selectList;
    private UploadManager uploadManager;
    private String token;
    private String idcodezs = "", idcodess = "";
    private int userid;
    private boolean iscity = true;
    private String statetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realnamede);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
                .connectTimeout(10) // 链接超时。默认10秒
                .responseTimeout(60) // 服务器响应超时。默认60秒
                .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        loadreal(userid);
    }

    private void loadreal(int userid) {
        RealReg phonecode = new RealReg();
        phonecode.setId(userid);
        OkHttpUtils
                .postString()
                .url(ConstantUrl.realnamesearch)
                .content(new Gson().toJson(phonecode))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("code", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("code", response.toString());
                        RealnameBean realnameBean = new Gson().fromJson(response, RealnameBean.class);
                        if (realnameBean.getUserDetails() != null) {
                            if (realnameBean.getUserDetails().getState().equals("0")) {
                                tijiao.setText("正在审核");
                                tijiao.setEnabled(false);
                                tijiao.setBackgroundResource(R.drawable.bg_btnnor);
                            } else if (realnameBean.getUserDetails().getState().equals("1")) {
                                tijiao.setText("修改");
                            } else if (realnameBean.getUserDetails().getState().equals("2")) {
                                statetxt = realnameBean.getUserDetails().getRemarks();
                                new Task().execute();
                                tijiao.setText("修改");
                            }
                            username.setText(realnameBean.getUserDetails().getName());
                            idcode.setText(realnameBean.getUserDetails().getIdCode());
                            if (realnameBean.getUserDetails().getHouseholdAssets() != null) {
                                citys.setText(realnameBean.getUserDetails().getHouseholdAssets());
                                City = realnameBean.getUserDetails().getHouseholdAssets();
                            }
                            company.setText(realnameBean.getUserDetails().getIdOpposite());
                            if (realnameBean.getUserDetails().getQq() != null) {
                                companytel.setText(realnameBean.getUserDetails().getQq());
                            }
                            idcodess = realnameBean.getUserDetails().getIdHand();
                            idcodezs = realnameBean.getUserDetails().getIdFront();
                            imagecode1.setImageURI(realnameBean.getUserDetails().getIdHand());
                            imagecode2.setImageURI(realnameBean.getUserDetails().getIdFront());
                        } else {

                        }
                    }
                });
    }

    private class Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            initPopuWindow(statetxt);

        }

    }

    @OnClick({R.id.image, R.id.citys, R.id.imagecode1, R.id.imagecode2, R.id.tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                this.finish();
                break;
            case R.id.citys:
                CityPicker cityPicker = new CityPicker.Builder(RealnameDetails.this)
                        .textSize(20)
                        .title("地址选择")
                        .backgroundPop(0xa0000000)
                        .titleBackgroundColor("#c7c7c7")
                        .titleTextColor("#000000")
                        .backgroundPop(0xa0000000)
                        .confirTextColor("#ffffff")
                        .cancelTextColor("#000000")
                        .province("北京市")
                        .city("北京市")
                        .district("东城区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(false)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .onlyShowProvinceAndCity(false)
                        .build();
                cityPicker.show();

                //监听方法，获取选择结果
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                        String province = citySelected[0];
                        //城市
                        String city = citySelected[1];
                        //区县（如果设定了两级联动，那么该项返回空）
                        String district = citySelected[2];
                        //邮编
                        String code = citySelected[3];
                        citys.setText(city);
                        City = city;
                        iscity = false;
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.imagecode1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        return;
                    }
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        return;
                    }
                }
//
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .compress(true)
                        .forResult(1);
                break;
            case R.id.imagecode2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        return;
                    }
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        return;
                    }
                }
//
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .compress(true)
                        .forResult(2);
                break;
            case R.id.tijiao:
                if (EditTools.checkEmpty(this, company, "请输入公司信息")) return;
                if (EditTools.checkEmpty(this, username, "请输入姓名")) return;
                if (EditTools.checkEmpty(this, idcode, "请输入身份证号码")) return;
                RealNameData realNameData = new RealNameData();
                realNameData.setName(username.getText().toString());
                realNameData.setIdCode(idcode.getText().toString());
                realNameData.setState("0");
                realNameData.setUserId(userid);
                realNameData.setIdFront(idcodezs);
                realNameData.setIdHand(idcodess);
                realNameData.setHouseholdAssets(City);
                realNameData.setQq(companytel.getText().toString());
                realNameData.setIdOpposite(company.getText().toString());
                final Gson gson = new Gson();
                final String json = gson.toJson(realNameData);
                Log.e("idcodezs", new Gson().toJson(realNameData));
                OkHttpUtils
                        .postString()
                        .url(ConstantUrl.realnameurl)
                        .content(json)
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("userinsert", e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("userinsert", response.toString());
                                OrderBean orderBean = gson.fromJson(response.toString(), OrderBean.class);
                                Log.e("orderBean", orderBean.getCode() + orderBean.getMsg());
                                if (orderBean.getCode().equals("0")) {
                                    ToastUtils.showToastGravityCenter(orderBean.getMsg());
                                    RealnameDetails.this.finish();
                                    SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                                            Activity.MODE_PRIVATE);
                                    //实例化SharedPreferences.Editor对象
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    //用putString的方法保存数据
                                    editor.putString("isinsert", "1");
                                    editor.putString("name", username.getText().toString());
                                    editor.putString("idcode", idcode.getText().toString());

                                    //提交当前数据
                                    editor.apply();
                                } else {
                                    ToastUtils.showToastGravityCenter("提交申请失败,请稍候再试");
                                    RealnameDetails.this.finish();
                                }
                            }

                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //用putString的方法保存数据
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
                                                        idcodess = key;
                                                        imagecode1.setImageURI("file://" + localMedia.getPath());
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
            if (requestCode == 2 && resultCode == RESULT_OK) {
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
                                                        imagecode2.setImageURI("file://" + localMedia.getPath());
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

    private void initPopuWindow(String txt) {

        View view = LayoutInflater.from(RealnameDetails.this).inflate(R.layout.pop_orderone, null);
        final PopupWindow popWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, false);
        Button button = (Button) view.findViewById(R.id.btn_know);
        TextView txtstate = (TextView) view.findViewById(R.id.txtstate);
        txtstate.setText(txt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });

        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        popWindow.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //在底部显示
        //     popWindow.showAsDropDown(view);
        popWindow.showAtLocation(findViewById(R.id.linear), Gravity.BOTTOM, 0, 0);

    }
}